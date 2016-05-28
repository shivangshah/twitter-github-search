package com.shivang.search.twitter;

import java.util.List;
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

    @Autowired
    public TwitterClient(Twitter twitter) {
        this.twitter = twitter;
    }

    public Observable<List<Tweet>> searchTweets(String searchTerm, int pageSize) {
        return Observable.create((Subscriber<? super List<Tweet>> t) -> {
            try {
                SearchResults results = twitter.searchOperations().search(searchTerm, pageSize);
                t.onNext(results.getTweets());
                t.onCompleted();
            } catch (Exception ex) {
                t.onError(ex);
            }
        });
    }
}
