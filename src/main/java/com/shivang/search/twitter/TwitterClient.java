package com.shivang.search.twitter;

import com.shivang.search.controller.SearchController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Shivang Shah
 */
@Component
public class TwitterClient {

    private final Twitter twitter;
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterClient.class);

    @Autowired
    public TwitterClient(Twitter twitter) {
        this.twitter = twitter;
        LOGGER.debug("Twitter client initialized ..");
        
    }

    public Observable<List<Tweet>> searchTweets(String searchTerm, int pageSize) {
        return Observable.create((Subscriber<? super List<Tweet>> t) -> {
            try {
                SearchResults results = twitter.searchOperations().search(searchTerm, pageSize);
                t.onNext(results.getTweets());
                t.onCompleted();
            } catch (Exception ex) {
                // Let RxJava wrap the exception and throw it.
                t.onError(ex);
            }
        });
    }
}
