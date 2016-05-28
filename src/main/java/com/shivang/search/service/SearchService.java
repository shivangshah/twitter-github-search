package com.shivang.search.service;

import com.shivang.search.github.GithubClient;
import com.shivang.search.github.model.GithubRepository;
import com.shivang.search.model.GithubTwitter;
import com.shivang.search.twitter.TwitterClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

/**
 *
 * @author Shivang Shah
 */
@Component
public class SearchService {

    @Autowired
    private GithubClient githubClient;
    @Autowired
    private TwitterClient twitterClient;

    public Observable<List<GithubTwitter>> getSearchResponse(String searchTerm,
            int githubPage, int githubPageSize) {
        return githubClient.searchGithubRepositories(searchTerm,
                githubPage, githubPageSize)
                .map((GithubRepository repo) -> {
                    GithubTwitter githubTwitter = new GithubTwitter();
                    githubTwitter.setRepository(repo);
                    githubTwitter.setTweets(twitterClient.searchTweets(githubTwitter
                            .getRepository().getFullName(), 5).toBlocking().single());
                    return githubTwitter;
        }).toList();
    }
}
