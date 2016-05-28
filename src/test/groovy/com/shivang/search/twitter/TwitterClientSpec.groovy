package com.shivang.search.twitter

import org.springframework.social.twitter.api.SearchOperations
import org.springframework.social.twitter.api.SearchResults
import org.springframework.social.twitter.api.Tweet
import org.springframework.social.twitter.api.Twitter
import spock.lang.Specification

class TwitterClientSpec extends Specification {

    Twitter twitter
    TwitterClient twitterClient
    SearchOperations searchOperations
    SearchResults searchResults

    def setup() {
        twitter = Mock(Twitter)
        searchOperations = Mock(SearchOperations)
        searchResults = Mock(SearchResults)
        twitterClient = new TwitterClient(twitter)
    }

    def "search for tweets"() {
        int pageSize
        String searchTerm
        List<Tweet> tweets
        List<Tweet> tweetsReturned
        given: "A searchTerm with page size"
            pageSize = 10
            searchTerm = "test"
            tweetsReturned = new ArrayList<>()
            for (int i = 0; i < pageSize; i++) {
                Tweet tweet = new Tweet(i, "" + i, "tweet text" + i,
                        new Date(), "fromUser", "imageUrl", i, i, "en-US", "source")
                tweetsReturned.add(tweet)
            }


        when: "a search for tweets is made"
            tweets = twitterClient.searchTweets(searchTerm, pageSize).toBlocking().single()

        then: "verify that tweets are returned based on the search/query term"
            1 * twitter.searchOperations() >> searchOperations
            1 * searchOperations.search(searchTerm, pageSize) >> searchResults
            1 * searchResults.getTweets() >> tweetsReturned
            assert tweets.size() == tweetsReturned.size()
            for (int i = 0; i < tweets.size(); i++) {
                assert tweets.get(i).getId() == i
            }
    }

    def "failed search for tweets"() {
        int pageSize
        String searchTerm
        given: "A searchTerm with page size"
            pageSize = 10
            searchTerm = "test"


        when: "a failed search for tweets is made"
            twitterClient.searchTweets(searchTerm, pageSize).toBlocking().single()

        then: "verify that an exception is thrown"
            1 * twitter.searchOperations() >> searchOperations
            1 * searchOperations.search(searchTerm, pageSize) >> searchResults
            1 * searchResults.getTweets() >>
                    { throw new RuntimeException("some error occurred") }
            RuntimeException ex = thrown(RuntimeException)
            assert ex.getMessage() == "some error occurred"
    }
}