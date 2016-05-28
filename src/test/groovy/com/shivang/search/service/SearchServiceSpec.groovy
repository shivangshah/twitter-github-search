package com.shivang.search.service

import com.shivang.search.github.GithubClient
import com.shivang.search.github.model.GithubRepository
import com.shivang.search.model.GithubTwitter
import com.shivang.search.twitter.TwitterClient
import org.springframework.social.twitter.api.Tweet
import rx.Observable
import spock.lang.Specification

class SearchServiceSpec extends Specification {

    TwitterClient twitterClient
    GithubClient githubClient
    SearchService searchService

    def setup() {
        twitterClient = Mock(TwitterClient)
        githubClient = Mock(GithubClient)
        searchService = new SearchService(githubClient, twitterClient)
    }

    def "search github repositories and related tweets"() {
        int page
        int pageSize
        String searchTerm
        List<GithubTwitter> results;
        List<GithubRepository> repos = new ArrayList<>()
        List<Tweet> tweets = new ArrayList<>()
        given: "A search term with pageSize and page number for github"
            page = 1
            pageSize = 10
            searchTerm = "test"
        when: "search service is invoked with the required params"
            for (int i = 0; i < pageSize; i++) {
                repos.add(new GithubRepository("test" + i, "description" + i))
            }
            for (int i = 0; i < 5; i++) {
                Tweet tweet = new Tweet(i, "" + i, "tweet text" + i,
                        new Date(), "fromUser", "imageUrl", i, i, "en-US", "source")
                tweets.add(tweet)
            }
            1 * githubClient.searchGithubRepositories(_, _, _) >> Observable.from(repos)
            pageSize * twitterClient.searchTweets(_, _) >> Observable.just(tweets)
            results = searchService.getSearchResponse(searchTerm, page, pageSize).toBlocking().single()
        then: "verify the expected values are returned"
            assert results.size() == pageSize
            for (int i = 0; i < pageSize; i++) {
                assert results.get(i).getRepository().getName() == "test" + i
                assert results.get(i).getRepository().getDescription() == "description" + i
                for (int j = 0; j < results.get(i).getTweets().size(); j++) {
                    assert results.get(i).getTweets().get(j).getId() == j
                }
            }
    }
}