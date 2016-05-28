package com.shivang.search.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Shivang Shah
 */
@Configuration
public class TwitterConfig {

    @Bean
    public Twitter twitter(TwitterClientInfo twitterClientInfo) {
        return new TwitterTemplate(twitterClientInfo.getConsumerKey(),
                twitterClientInfo.getConsumerSecret(),
                twitterClientInfo.getAccessToken(),
                twitterClientInfo.getAccessSecret());
    }

    @ConfigurationProperties(prefix = "twitter")
    @Component
    public static class TwitterClientInfo {

        private String consumerKey;
        private String consumerSecret;
        private String accessToken;
        private String accessSecret;

        public TwitterClientInfo() {
        }

        public TwitterClientInfo(String consumerKey, String consumerSecret,
                                 String accessToken, String accessSecret) {
            this.consumerKey = consumerKey;
            this.consumerSecret = consumerSecret;
            this.accessSecret = accessSecret;
            this.accessToken = accessToken;
        }

        public String getConsumerKey() {
            return consumerKey;
        }

        public void setConsumerKey(String consumerKey) {
            this.consumerKey = consumerKey;
        }

        public void setConsumerSecret(String consumerSecret) {
            this.consumerSecret = consumerSecret;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public void setAccessSecret(String accessSecret) {
            this.accessSecret = accessSecret;
        }

        public String getConsumerSecret() {
            return consumerSecret;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getAccessSecret() {
            return accessSecret;
        }

    }

}
