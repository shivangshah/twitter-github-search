package com.shivang.search.config

import com.shivang.search.config.TwitterConfig.TwitterClientInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.social.twitter.api.Twitter
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS

@ContextConfiguration(classes = [TwitterClientTestConfig, TwitterConfig])
@DirtiesContext(classMode = AFTER_CLASS)
class TwitterConfigSpec extends Specification {

    @Autowired
    Twitter twitter

    def "Get Twitter configured correctly"() {
        given: "That the autoconfiguration is enabled for the application (which it is by default for Spring Boot App)"
        when: "The required Twitter bean is autowired"
        then: "Verify that the autowired bean is not null"
            twitter != null
    }

    @Configuration
    static class TwitterClientTestConfig {

        @Bean
        @Primary
        public TwitterClientInfo twitterClientInfo() {
            return new TwitterClientInfo("consumerKey", "consumerSecret", "accessToken", "accessSecret")
        }

    }

}