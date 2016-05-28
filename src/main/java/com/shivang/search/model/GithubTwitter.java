package com.shivang.search.model;

import com.shivang.search.github.model.GithubRepository;
import java.util.List;
import org.springframework.social.twitter.api.Tweet;

/**
 *
 * @author Shivang Shah
 */
public class GithubTwitter {
    
    private GithubRepository repository;
    private List<Tweet> tweets;

    public GithubRepository getRepository() {
        return repository;
    }

    public void setRepository(GithubRepository repository) {
        this.repository = repository;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
