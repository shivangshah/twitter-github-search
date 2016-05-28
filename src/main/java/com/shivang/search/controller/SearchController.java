package com.shivang.search.controller;

import com.google.common.collect.ImmutableMap;
import com.shivang.search.model.GithubTwitter;
import com.shivang.search.service.SearchService;
import java.util.List;
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

/**
 *
 * @author Shivang Shah
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public DeferredResult<ResponseEntity<List<GithubTwitter>>> searchGithubAndTwitter(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("page") Integer page,
            @RequestParam("searchTerm") String searchTerm) {
        DeferredResult<ResponseEntity<List<GithubTwitter>>> result = new DeferredResult<>();
        if (pageSize > 10) {

            result.setErrorResult(new ResponseEntity<>(ImmutableMap.of("error", 
                    "pageSize for searching github projects"
                    + " cannot be greater than 10"), HttpStatus.BAD_REQUEST));
        }
        Observable<List<GithubTwitter>> obsResult
                = searchService.getSearchResponse(searchTerm, page, pageSize);
        obsResult.subscribeOn(Schedulers.io()).subscribe(searchResults -> {
            ResponseEntity<List<GithubTwitter>> entity = new ResponseEntity<>(searchResults, HttpStatus.OK);
            result.setResult(entity);
        }, result::setErrorResult);
        return result;
    }

}
