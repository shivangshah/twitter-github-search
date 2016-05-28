package com.shivang.search.github

import com.shivang.search.github.model.CustomException
import com.shivang.search.github.model.GithubRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.web.client.AsyncRestTemplate
import rx.Observable
import spock.lang.Specification

import static com.shivang.search.github.GithubClient.GITHUB_REPOSITORY_SEARCH_URL


class GithubClientSpec extends Specification {

    AsyncRestTemplate asyncRestTemplate
    GithubClient githubClient

    def setup() {
        asyncRestTemplate = Mock(AsyncRestTemplate)
        githubClient = new GithubClient(asyncRestTemplate)
    }

    def "search for github repositories"() {
        int page
        int pageSize
        String searchTerm
        String responseJson
        Observable<GithubRepository> reposObs
        given: "A searchTerm with page number and page size"
            page = 1
            pageSize = 10
            searchTerm = "test"
            responseJson = "{\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"name\": \"test1\",\n" +
                    "      \"description\": \"some description1\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"name\": \"test2\",\n" +
                    "      \"description\": \"some description2\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"

        when: "a search for github repositories is made"
            1 * asyncRestTemplate.exchange(GITHUB_REPOSITORY_SEARCH_URL, HttpMethod.GET,
                    HttpEntity.EMPTY, String.class, searchTerm,
                    page, pageSize) >>
                    new AsyncResult<ResponseEntity<String>>(new ResponseEntity<String>(responseJson, HttpStatus.OK))
            reposObs = githubClient.searchGithubRepositories(searchTerm, page, pageSize)
        then: "verify that github repositories are returned based on the search/query term"
            List<GithubRepository> repos = reposObs.toList().toBlocking().single()
            assert repos.size() == 2
            for (int i = 0; i < repos.size(); i++) {
                assert repos.get(i).name == "test" + (i + 1)
                assert repos.get(i).description == "some description" + (i + 1)
            }
    }

    def "github search request not successful"() {

        int page
        int pageSize
        String searchTerm
        String response = "error"
        given: "A searchTerm with page number and page size"
            page = 1
            pageSize = 10
            searchTerm = "test"

        when: "a failed search for github repositories is made"
            1 * asyncRestTemplate.exchange(GITHUB_REPOSITORY_SEARCH_URL, HttpMethod.GET,
                    HttpEntity.EMPTY, String.class, searchTerm,
                    page, pageSize) >>
                    new AsyncResult<ResponseEntity<String>>(new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST))
            githubClient.searchGithubRepositories(searchTerm, page, pageSize).toBlocking().single()
        then: "verify that an exception is thrown"
            CustomException ex = thrown(CustomException)
            assert ex.httpStatus == HttpStatus.BAD_REQUEST
    }

}