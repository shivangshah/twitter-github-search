package com.shivang.search.controller;

import com.shivang.search.github.model.CustomException;
import com.shivang.search.model.GithubTwitter;
import com.shivang.search.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * @author Shivang Shah
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    private final SearchService searchService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public DeferredResult<ResponseEntity<List<GithubTwitter>>> searchGithubAndTwitter(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("page") Integer page,
            @RequestParam("searchTerm") String searchTerm) {
        LOGGER.debug("request params: pageSize: {}, page: {}, searchTerm: {}", pageSize, page, searchTerm);
        // Using DeferredResult to provide non-blocking rest capabilities
        DeferredResult<ResponseEntity<List<GithubTwitter>>> result = new DeferredResult<>();
        if (pageSize > 10) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "pageSize cannot be greater than 10");
        }
        Observable<List<GithubTwitter>> obsResult
                = searchService.getSearchResponse(searchTerm, page, pageSize);
        // Setting the result and errorResult in case of any Exceptions wrapper by RxJava
        obsResult.subscribeOn(Schedulers.io()).subscribe(searchResults -> {
            ResponseEntity<List<GithubTwitter>> entity = new ResponseEntity<>(searchResults, HttpStatus.OK);
            result.setResult(entity);
        }, result::setErrorResult);
        return result;
    }

}
