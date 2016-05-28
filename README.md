# Github Twitter Search Integration
A project to search tweets based on different github projects returned for a specific search string

# Project health
[![Build Status](https://travis-ci.org/shivangshah/twitter-github-search.svg?branch=master)](https://travis-ci.org/shivangshah/twitter-github-search)
[![codecov](https://codecov.io/gh/shivangshah/twitter-github-search/branch/master/graph/badge.svg)](https://codecov.io/gh/shivangshah/twitter-github-search)
# Getting started
Provide your twitter secrets in the `application.properties` file under `src/resources`

# Building the application
Gradle wrapper is already included so all needs to be done is: 

`./gradlew clean build`

# Running the application
Once built, being a spring boot application, you can easily run the application as: 

`java -jar build/libs/twitter-github-search-0.0.1-SNAPSHOT.jar`

You should see no errors starting up the twitter credentials are provided correctly. Github api is open and does not need any credentials.
Once the server is up, you can navigate to the following REST api using any of your favorite client (or browser): 

`http://localhost:8080/search?searchTerm=neo4j&page=1&pageSize=1`

The `pageSize` and the `page` parameters are for searching the project in github. The current number of tweets per github project is limited to `5` (will eventually make this configurable)

# Libraries used
- Spring Actuator - Get built-in capabilities for debug endpoints (such as `/health`, `/info` etc)
- RxJava & Hystrix - For non-blocking, resilient, application flow
- Spring Web - All MVC related
- Gradle - For building purposes
- Spring Social Twitter - For Twitter api capabilities

# List of TODOS: 

- [x] Integrate with Github
- [x] Integrate with Twitter (using Spring Twitter library)
- [x] Basic Implementation and workflow working
- [ ] Add unit test cases
- [ ] Add integration test cases
- [x] Update Logging
- [x] Exception Handling (handled by spring boot magic !)
- [x] Update Documentation

# Sample Response

A sample response for the above query looks as below: 

```
[
  {
    "repository": {
      "description": "Graphs for Everyone",
      "fullName": "neo4j"
    },
    "tweets": [
      {
        "extraData": {},
        "id": 736374217940275201,
        "idStr": "736374217940275201",
        "text": "@neo4j will you share my idea after I tweet a link to the MVP?",
        "createdAt": 1464400269000,
        "fromUser": "starjamz",
        "profileImageUrl": "http://pbs.twimg.com/profile_images/378800000135400151/9234eaaff9ae5f8fe656d6c04d12ff8b_normal.png",
        "toUserId": 22467617,
        "inReplyToStatusId": 736270871283781632,
        "inReplyToUserId": 22467617,
        "inReplyToScreenName": "neo4j",
        "fromUserId": 15362679,
        "languageCode": "en",
        "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
        "retweetCount": 0,
        "retweeted": false,
        "retweetedStatus": null,
        "favorited": false,
        "favoriteCount": 0,
        "entities": {
          "extraData": {
            "symbols": []
          },
          "urls": [],
          "mentions": [
            {
              "extraData": {
                "id_str": "22467617"
              },
              "id": 22467617,
              "screen_name": "neo4j",
              "name": "Neo4j",
              "indices": [
                0,
                6
              ]
            }
          ],
          "media": [],
          "tickerSymbols": [],
          "hashTags": []
        },
        "user": {
          "extraData": {
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme10/bg.gif",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/378800000135400151/9234eaaff9ae5f8fe656d6c04d12ff8b_normal.png",
            "entities": {
              "url": {
                "urls": [
                  {
                    "url": "http://t.co/PsKCHaRNXH",
                    "expanded_url": "http://starjamz.com",
                    "display_url": "starjamz.com",
                    "indices": [
                      0,
                      22
                    ]
                  }
                ]
              },
              "description": {
                "urls": []
              }
            },
            "default_profile_image": false,
            "id_str": "15362679",
            "default_profile": false,
            "is_translation_enabled": false,
            "has_extended_profile": false
          },
          "id": 15362679,
          "screenName": "starjamz",
          "name": "starjamz.com",
          "url": "http://t.co/PsKCHaRNXH",
          "profileImageUrl": "http://pbs.twimg.com/profile_images/378800000135400151/9234eaaff9ae5f8fe656d6c04d12ff8b_normal.png",
          "description": "Powered by Rackspace Cloud.",
          "location": "Lexington, MA",
          "createdDate": 1215581863000,
          "language": "en",
          "statusesCount": 3101,
          "friendsCount": 798,
          "followersCount": 644,
          "favoritesCount": 51,
          "listedCount": 2,
          "following": false,
          "followRequestSent": false,
          "notificationsEnabled": false,
          "verified": false,
          "geoEnabled": false,
          "contributorsEnabled": false,
          "translator": false,
          "timeZone": "Quito",
          "utcOffset": -18000,
          "sidebarBorderColor": "65B0DA",
          "sidebarFillColor": "7AC3EE",
          "backgroundColor": "FFFFFF",
          "backgroundImageUrl": "http://abs.twimg.com/images/themes/theme10/bg.gif",
          "backgroundImageTiled": true,
          "textColor": "3D1957",
          "linkColor": "FF0000",
          "profileBannerUrl": "https://pbs.twimg.com/profile_banners/15362679/1356572066",
          "protected": false,
          "profileUrl": "http://twitter.com/starjamz"
        },
        "unmodifiedText": "@neo4j will you share my idea after I tweet a link to the MVP?",
        "retweet": false
      },
      ...
    ]
  }
]
```
