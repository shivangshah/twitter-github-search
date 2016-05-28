package com.shivang.search.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shivang.search.github.GithubClient;
import com.shivang.search.github.model.GithubRepository;
import com.shivang.search.model.GithubTwitter;
import com.shivang.search.twitter.TwitterClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.List;

/**
 * @author Shivang Shah
 */
@Component
public class SearchService {

    private final GithubClient githubClient;
    private final TwitterClient twitterClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    public SearchService(GithubClient githubClient, TwitterClient twitterClient) {
        this.githubClient = githubClient;
        this.twitterClient = twitterClient;
        LOGGER.debug("SearchService initialized ..");
    }

    @HystrixCommand
    public Observable<List<GithubTwitter>> getSearchResponse(String searchTerm,
                                                             int githubPage, int githubPageSize) {
        return githubClient.searchGithubRepositories(searchTerm,
                githubPage, githubPageSize)
                .map((GithubRepository repo) -> {
                    GithubTwitter githubTwitter = new GithubTwitter();
                    githubTwitter.setRepository(repo);
                    githubTwitter.setTweets(twitterClient.searchTweets(githubTwitter
                            .getRepository().getName(), 5).toBlocking().single());
                    return githubTwitter;
                }).toList();
    }
}
