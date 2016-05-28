package com.shivang.search.controller

import com.netflix.hystrix.exception.HystrixRuntimeException
import com.shivang.search.controller.advice.GlobalExceptionControllerAdvice
import com.shivang.search.github.model.CustomException
import com.shivang.search.github.model.GithubRepository
import com.shivang.search.model.GithubTwitter
import com.shivang.search.service.SearchService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.social.twitter.api.Tweet
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import rx.Observable
import spock.lang.Specification

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class SearchControllerSpec extends Specification {

    SearchService searchService
    SearchController searchController
    MockMvc mockMvc

    def setup() {
        searchService = Mock(SearchService)
        searchController = new SearchController(searchService)
        mockMvc = MockMvcBuilders.standaloneSetup(searchController)
                .setControllerAdvice(new GlobalExceptionControllerAdvice())
                .build()
    }

    def "search github and twitter with valid params"() {
        List<GithubTwitter> searchResponses
        int page
        int pageSize
        String searchTerm

        given: "a searchTerm with page # and pageSize to search github + twitter"
            page = 1
            pageSize = 10
            searchTerm = "test"
            searchResponses = new ArrayList<>()
            for (int i = 0; i < pageSize; i++) {
                GithubTwitter response = new GithubTwitter()
                response.setRepository(new GithubRepository("test" + i, "description" + i))
                response.setTweets(new ArrayList<Tweet>())
                searchResponses.add(response)
            }
        when: "a search is made with the provided parameters"
            MvcResult result = mockMvc.perform(get("/search")
                    .param("searchTerm", searchTerm)
                    .param("page", page.toString())
                    .param("pageSize", pageSize.toString()))
                    .andExpect(request().asyncStarted())
                    .andExpect(request().asyncResult(instanceOf(ResponseEntity.class)))
                    .andReturn();
        then: "Verify that expected responses are received"
            1 * searchService.getSearchResponse(searchTerm, page, pageSize) >> Observable.just(searchResponses)
            this.mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath('$', hasSize(pageSize)))
                    .andExpect(jsonPath('$[0].repository.name', is("test0")))
                    .andExpect(jsonPath('$[1].repository.name', is("test1")))
    }

    def "search github and twitter with invalid params"() {
        int page
        int pageSize
        String searchTerm

        given: "a searchTerm with page # and pageSize GREATER THAN 10 to search github + twitter"
            page = 1
            pageSize = 11
            searchTerm = "test"

        when: "a search is made with the provided parameters"
            ResultActions result = mockMvc.perform(get("/search")
                    .param("searchTerm", searchTerm)
                    .param("page", page.toString())
                    .param("pageSize", pageSize.toString()))

        then: "Verify that expected error response is received"
            result.andExpect(status().isBadRequest())
                    .andExpect(jsonPath("status").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("message").value("pageSize cannot be greater than 10"))
    }

    def "search github when circuit breaker is open"() {
        int page
        int pageSize
        String searchTerm

        given: "a request to search github"
            page = 1
            pageSize = 10
            searchTerm = "test"

        when: "the hystrix circuit breaker is open (system temporarily unavailable)"
            1 * searchService.getSearchResponse(searchTerm, page, pageSize) >>
                    Observable.error(new HystrixRuntimeException(
                            HystrixRuntimeException.FailureType.SHORTCIRCUIT,
                            null, "message", null, null))
            MvcResult result = mockMvc.perform(get("/search")
                    .param("searchTerm", searchTerm)
                    .param("page", page.toString())
                    .param("pageSize", pageSize.toString()))
                    .andExpect(request().asyncStarted())
                    .andExpect(request().asyncResult(instanceOf(HystrixRuntimeException.class)))
                    .andReturn();

        then: "Verify that search results are NOT retrieved and instead a service unavailable error is thrown"
            this.mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isServiceUnavailable())
                    .andExpect(jsonPath("status").value(HttpStatus.SERVICE_UNAVAILABLE.value()))
    }

    def "search github when search service (wrapped with hystrix) throws an error"() {
        int page
        int pageSize
        String searchTerm

        given: "a request to search github"
            page = 1
            pageSize = 10
            searchTerm = "test"

        when: "the hystrix backed service call throws an error"
            1 * searchService.getSearchResponse(searchTerm, page, pageSize) >>
                    Observable.error(new HystrixRuntimeException(
                            HystrixRuntimeException.FailureType.SHORTCIRCUIT,
                            null, "message", new CustomException(HttpStatus.BAD_REQUEST, "developerMessage"), null))
            MvcResult result = mockMvc.perform(get("/search")
                    .param("searchTerm", searchTerm)
                    .param("page", page.toString())
                    .param("pageSize", pageSize.toString()))
                    .andExpect(request().asyncStarted())
                    .andExpect(request().asyncResult(instanceOf(HystrixRuntimeException.class)))
                    .andReturn();

        then: "Verify that search results are NOT retrieved and instead corresponding error is thrown"
            this.mockMvc.perform(asyncDispatch(result))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("status").value(HttpStatus.BAD_REQUEST.value()))

    }
}