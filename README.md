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
- [ ] Swagger Documentation (Bonus!)

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
      {
        "extraData": {},
        "id": 736374126135410688,
        "idStr": "736374126135410688",
        "text": "@neo4j how about I go build the web service and do a write up?",
        "createdAt": 1464400247000,
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
        "favoriteCount": 1,
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
        "unmodifiedText": "@neo4j how about I go build the web service and do a write up?",
        "retweet": false
      },
      {
        "extraData": {},
        "id": 736365215504293888,
        "idStr": "736365215504293888",
        "text": "RT @ikwattro: #neo4j php ogm - Object Graph Mapper for Neo4j - Beta1 released - https://t.co/bwA9P6ytAT #nosql #graphs",
        "createdAt": 1464398123000,
        "fromUser": "luannem",
        "profileImageUrl": "http://pbs.twimg.com/profile_images/602715633344598016/0FJdAo2F_normal.jpg",
        "toUserId": 0,
        "inReplyToStatusId": null,
        "inReplyToUserId": null,
        "inReplyToScreenName": "null",
        "fromUserId": 13235082,
        "languageCode": "en",
        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        "retweetCount": 6,
        "retweeted": false,
        "retweetedStatus": {
          "extraData": {},
          "id": 736323390072492033,
          "idStr": "736323390072492033",
          "text": "#neo4j php ogm - Object Graph Mapper for Neo4j - Beta1 released - https://t.co/bwA9P6ytAT #nosql #graphs",
          "createdAt": 1464388151000,
          "fromUser": "ikwattro",
          "profileImageUrl": "http://pbs.twimg.com/profile_images/712782956205588480/SH-FHeKq_normal.jpg",
          "toUserId": 0,
          "inReplyToStatusId": null,
          "inReplyToUserId": null,
          "inReplyToScreenName": "null",
          "fromUserId": 278486993,
          "languageCode": "en",
          "source": "<a href=\"https://about.twitter.com/products/tweetdeck\" rel=\"nofollow\">TweetDeck</a>",
          "retweetCount": 6,
          "retweeted": false,
          "retweetedStatus": null,
          "favorited": false,
          "favoriteCount": 6,
          "entities": {
            "extraData": {
              "symbols": []
            },
            "urls": [
              {
                "extraData": {},
                "url": "https://t.co/bwA9P6ytAT",
                "indices": [
                  66,
                  89
                ],
                "displayUrl": "github.com/graphaware/neo…",
                "expandedUrl": "https://github.com/graphaware/neo4j-php-ogm"
              }
            ],
            "mentions": [],
            "media": [],
            "tickerSymbols": [],
            "hashTags": [
              {
                "extraData": {},
                "text": "neo4j",
                "indices": [
                  0,
                  6
                ]
              },
              {
                "extraData": {},
                "text": "nosql",
                "indices": [
                  90,
                  96
                ]
              },
              {
                "extraData": {},
                "text": "graphs",
                "indices": [
                  97,
                  104
                ]
              }
            ]
          },
          "user": {
            "extraData": {
              "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme9/bg.gif",
              "profile_image_url_https": "https://pbs.twimg.com/profile_images/712782956205588480/SH-FHeKq_normal.jpg",
              "entities": {
                "url": {
                  "urls": [
                    {
                      "url": "https://t.co/5Qa27N6W0y",
                      "expanded_url": "http://www.graphaware.com",
                      "display_url": "graphaware.com",
                      "indices": [
                        0,
                        23
                      ]
                    }
                  ]
                },
                "description": {
                  "urls": []
                }
              },
              "default_profile_image": false,
              "id_str": "278486993",
              "default_profile": false,
              "is_translation_enabled": false,
              "has_extended_profile": true
            },
            "id": 278486993,
            "screenName": "ikwattro",
            "name": "Christophe Willemsen",
            "url": "https://t.co/5Qa27N6W0y",
            "profileImageUrl": "http://pbs.twimg.com/profile_images/712782956205588480/SH-FHeKq_normal.jpg",
            "description": "Neo4j Senior Consultant @graph_aware - All #Graphs and #Search - #neo4j #elasticsearch #PHP #Symfony #Java",
            "location": "Belgium",
            "createdDate": 1302175154000,
            "language": "fr",
            "statusesCount": 6370,
            "friendsCount": 961,
            "followersCount": 874,
            "favoritesCount": 7954,
            "listedCount": 228,
            "following": false,
            "followRequestSent": false,
            "notificationsEnabled": false,
            "verified": false,
            "geoEnabled": true,
            "contributorsEnabled": false,
            "translator": false,
            "timeZone": "Brussels",
            "utcOffset": 7200,
            "sidebarBorderColor": "FFFFFF",
            "sidebarFillColor": "252429",
            "backgroundColor": "1A1B1F",
            "backgroundImageUrl": "http://abs.twimg.com/images/themes/theme9/bg.gif",
            "backgroundImageTiled": false,
            "textColor": "666666",
            "linkColor": "ABB8C2",
            "profileBannerUrl": "https://pbs.twimg.com/profile_banners/278486993/1454697952",
            "protected": false,
            "profileUrl": "http://twitter.com/ikwattro"
          },
          "unmodifiedText": "#neo4j php ogm - Object Graph Mapper for Neo4j - Beta1 released - https://t.co/bwA9P6ytAT #nosql #graphs",
          "retweet": false
        },
        "favorited": false,
        "favoriteCount": 0,
        "entities": {
          "extraData": {
            "symbols": []
          },
          "urls": [
            {
              "extraData": {},
              "url": "https://t.co/bwA9P6ytAT",
              "indices": [
                80,
                103
              ],
              "displayUrl": "github.com/graphaware/neo…",
              "expandedUrl": "https://github.com/graphaware/neo4j-php-ogm"
            }
          ],
          "mentions": [
            {
              "extraData": {
                "id_str": "278486993"
              },
              "id": 278486993,
              "screen_name": "ikwattro",
              "name": "Christophe Willemsen",
              "indices": [
                3,
                12
              ]
            }
          ],
          "media": [],
          "tickerSymbols": [],
          "hashTags": [
            {
              "extraData": {},
              "text": "neo4j",
              "indices": [
                14,
                20
              ]
            },
            {
              "extraData": {},
              "text": "nosql",
              "indices": [
                104,
                110
              ]
            },
            {
              "extraData": {},
              "text": "graphs",
              "indices": [
                111,
                118
              ]
            }
          ]
        },
        "user": {
          "extraData": {
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme15/bg.png",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/602715633344598016/0FJdAo2F_normal.jpg",
            "entities": {
              "url": {
                "urls": [
                  {
                    "url": "http://t.co/mwuct2fnO7",
                    "expanded_url": "http://thought-bytes.blogspot.com/",
                    "display_url": "thought-bytes.blogspot.com",
                    "indices": [
                      0,
                      22
                    ]
                  }
                ]
              },
              "description": {
                "urls": [
                  {
                    "url": "http://t.co/v3IazqS6mr",
                    "expanded_url": "http://anotherbrickintheroad.org",
                    "display_url": "anotherbrickintheroad.org",
                    "indices": [
                      37,
                      59
                    ]
                  }
                ]
              }
            },
            "default_profile_image": false,
            "id_str": "13235082",
            "default_profile": false,
            "is_translation_enabled": false,
            "has_extended_profile": false
          },
          "id": 13235082,
          "screenName": "luannem",
          "name": "Luanne Misquitta",
          "url": "http://t.co/mwuct2fnO7",
          "profileImageUrl": "http://pbs.twimg.com/profile_images/602715633344598016/0FJdAo2F_normal.jpg",
          "description": "Consultant at GraphAware. Founder at http://t.co/v3IazqS6mr\r\nFoodie, Techie, Chocoholic, Neo4j fan.",
          "location": "Bombay",
          "createdDate": 1202444953000,
          "language": "en",
          "statusesCount": 3368,
          "friendsCount": 290,
          "followersCount": 523,
          "favoritesCount": 1158,
          "listedCount": 41,
          "following": false,
          "followRequestSent": false,
          "notificationsEnabled": false,
          "verified": false,
          "geoEnabled": true,
          "contributorsEnabled": false,
          "translator": false,
          "timeZone": "Mumbai",
          "utcOffset": 19800,
          "sidebarBorderColor": "A8C7F7",
          "sidebarFillColor": "C0DFEC",
          "backgroundColor": "022330",
          "backgroundImageUrl": "http://abs.twimg.com/images/themes/theme15/bg.png",
          "backgroundImageTiled": false,
          "textColor": "333333",
          "linkColor": "0084B4",
          "profileBannerUrl": "https://pbs.twimg.com/profile_banners/13235082/1402544215",
          "protected": false,
          "profileUrl": "http://twitter.com/luannem"
        },
        "unmodifiedText": "#neo4j php ogm - Object Graph Mapper for Neo4j - Beta1 released - https://t.co/bwA9P6ytAT #nosql #graphs",
        "retweet": true
      },
      {
        "extraData": {},
        "id": 736358320135503872,
        "idStr": "736358320135503872",
        "text": "RT @neo4j: The 5-Minute Interview: \n\n@JavaFXpert at @Pivotal discusses #Spring, @CloudFoundry &amp; #Neo4j\n\nhttps://t.co/56ZrypwizS https://t.c…",
        "createdAt": 1464396479000,
        "fromUser": "tomo_taka01",
        "profileImageUrl": "http://pbs.twimg.com/profile_images/1382313609/image_normal.jpg",
        "toUserId": 0,
        "inReplyToStatusId": null,
        "inReplyToUserId": null,
        "inReplyToScreenName": "null",
        "fromUserId": 301336706,
        "languageCode": "en",
        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
        "retweetCount": 4,
        "retweeted": false,
        "retweetedStatus": {
          "extraData": {},
          "id": 736165422073057281,
          "idStr": "736165422073057281",
          "text": "The 5-Minute Interview: \n\n@JavaFXpert at @Pivotal discusses #Spring, @CloudFoundry &amp; #Neo4j\n\nhttps://t.co/56ZrypwizS https://t.co/PK6jGYOh48",
          "createdAt": 1464350488000,
          "fromUser": "neo4j",
          "profileImageUrl": "http://pbs.twimg.com/profile_images/580798347734171649/FbkjM42a_normal.png",
          "toUserId": 0,
          "inReplyToStatusId": null,
          "inReplyToUserId": null,
          "inReplyToScreenName": "null",
          "fromUserId": 22467617,
          "languageCode": "en",
          "source": "<a href=\"http://bufferapp.com\" rel=\"nofollow\">Buffer</a>",
          "retweetCount": 4,
          "retweeted": false,
          "retweetedStatus": null,
          "favorited": false,
          "favoriteCount": 5,
          "entities": {
            "extraData": {
              "symbols": []
            },
            "urls": [
              {
                "extraData": {},
                "url": "https://t.co/56ZrypwizS",
                "indices": [
                  97,
                  120
                ],
                "displayUrl": "buff.ly/1UgDcYe",
                "expandedUrl": "http://buff.ly/1UgDcYe"
              }
            ],
            "mentions": [
              {
                "extraData": {
                  "id_str": "16562139"
                },
                "id": 16562139,
                "screen_name": "JavaFXpert",
                "name": "JavaFXpert",
                "indices": [
                  26,
                  37
                ]
              },
              {
                "extraData": {
                  "id_str": "1132876218"
                },
                "id": 1132876218,
                "screen_name": "pivotal",
                "name": "Pivotal",
                "indices": [
                  41,
                  49
                ]
              },
              {
                "extraData": {
                  "id_str": "18697326"
                },
                "id": 18697326,
                "screen_name": "cloudfoundry",
                "name": "Cloud Foundry",
                "indices": [
                  69,
                  82
                ]
              }
            ],
            "media": [
              {
                "extraData": {
                  "sizes": {
                    "small": {
                      "w": 340,
                      "h": 227,
                      "resize": "fit"
                    },
                    "medium": {
                      "w": 600,
                      "h": 400,
                      "resize": "fit"
                    },
                    "large": {
                      "w": 1024,
                      "h": 683,
                      "resize": "fit"
                    },
                    "thumb": {
                      "w": 150,
                      "h": 150,
                      "resize": "crop"
                    }
                  },
                  "id_str": "736165421561348096"
                },
                "id": 736165421561348096,
                "url": "https://t.co/PK6jGYOh48",
                "type": "photo",
                "indices": [
                  121,
                  144
                ],
                "mediaUrl": "http://pbs.twimg.com/media/CjdijPAXAAAb0qA.jpg",
                "mediaSecureUrl": "https://pbs.twimg.com/media/CjdijPAXAAAb0qA.jpg",
                "displayUrl": "pic.twitter.com/PK6jGYOh48",
                "expandedUrl": "http://twitter.com/neo4j/status/736165422073057281/photo/1"
              }
            ],
            "tickerSymbols": [],
            "hashTags": [
              {
                "extraData": {},
                "text": "Spring",
                "indices": [
                  60,
                  67
                ]
              },
              {
                "extraData": {},
                "text": "Neo4j",
                "indices": [
                  89,
                  95
                ]
              }
            ]
          },
          "user": {
            "extraData": {
              "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
              "profile_image_url_https": "https://pbs.twimg.com/profile_images/580798347734171649/FbkjM42a_normal.png",
              "entities": {
                "url": {
                  "urls": [
                    {
                      "url": "http://t.co/qjWEJlVLsk",
                      "expanded_url": "http://neo4j.com",
                      "display_url": "neo4j.com",
                      "indices": [
                        0,
                        22
                      ]
                    }
                  ]
                },
                "description": {
                  "urls": [
                    {
                      "url": "https://t.co/Ss3pcJTFlE",
                      "expanded_url": "http://neo4j.com/product/",
                      "display_url": "neo4j.com/product/",
                      "indices": [
                        59,
                        82
                      ]
                    },
                    {
                      "url": "https://t.co/qEz8gRCmPI",
                      "expanded_url": "http://neo4j.com/developer",
                      "display_url": "neo4j.com/developer",
                      "indices": [
                        95,
                        118
                      ]
                    },
                    {
                      "url": "https://t.co/kmJYDOxrUo",
                      "expanded_url": "https://www.youtube.com/watch?v=V7f2tGsNSck",
                      "display_url": "youtube.com/watch?v=V7f2tG…",
                      "indices": [
                        133,
                        156
                      ]
                    }
                  ]
                }
              },
              "default_profile_image": false,
              "id_str": "22467617",
              "default_profile": true,
              "is_translation_enabled": false,
              "has_extended_profile": false
            },
            "id": 22467617,
            "screenName": "neo4j",
            "name": "Neo4j",
            "url": "http://t.co/qjWEJlVLsk",
            "profileImageUrl": "http://pbs.twimg.com/profile_images/580798347734171649/FbkjM42a_normal.png",
            "description": "The world's leading graph database. // Connect everything: https://t.co/Ss3pcJTFlE // Develop: https://t.co/qEz8gRCmPI // 60s Intro: https://t.co/kmJYDOxrUo",
            "location": "San Mateo, California",
            "createdDate": 1235984355000,
            "language": "en",
            "statusesCount": 22663,
            "friendsCount": 2447,
            "followersCount": 19918,
            "favoritesCount": 4642,
            "listedCount": 1111,
            "following": true,
            "followRequestSent": false,
            "notificationsEnabled": false,
            "verified": false,
            "geoEnabled": true,
            "contributorsEnabled": false,
            "translator": false,
            "timeZone": "Pacific Time (US & Canada)",
            "utcOffset": -25200,
            "sidebarBorderColor": "C0DEED",
            "sidebarFillColor": "DDEEF6",
            "backgroundColor": "C0DEED",
            "backgroundImageUrl": "http://abs.twimg.com/images/themes/theme1/bg.png",
            "backgroundImageTiled": false,
            "textColor": "333333",
            "linkColor": "0084B4",
            "profileBannerUrl": "https://pbs.twimg.com/profile_banners/22467617/1464125135",
            "protected": false,
            "profileUrl": "http://twitter.com/neo4j"
          },
          "unmodifiedText": "The 5-Minute Interview: \n\n@JavaFXpert at @Pivotal discusses #Spring, @CloudFoundry &amp; #Neo4j\n\nhttps://t.co/56ZrypwizS https://t.co/PK6jGYOh48",
          "retweet": false
        },
        "favorited": false,
        "favoriteCount": 0,
        "entities": {
          "extraData": {
            "symbols": []
          },
          "urls": [
            {
              "extraData": {},
              "url": "https://t.co/56ZrypwizS",
              "indices": [
                108,
                131
              ],
              "displayUrl": "buff.ly/1UgDcYe",
              "expandedUrl": "http://buff.ly/1UgDcYe"
            }
          ],
          "mentions": [
            {
              "extraData": {
                "id_str": "22467617"
              },
              "id": 22467617,
              "screen_name": "neo4j",
              "name": "Neo4j",
              "indices": [
                3,
                9
              ]
            },
            {
              "extraData": {
                "id_str": "16562139"
              },
              "id": 16562139,
              "screen_name": "JavaFXpert",
              "name": "JavaFXpert",
              "indices": [
                37,
                48
              ]
            },
            {
              "extraData": {
                "id_str": "1132876218"
              },
              "id": 1132876218,
              "screen_name": "pivotal",
              "name": "Pivotal",
              "indices": [
                52,
                60
              ]
            },
            {
              "extraData": {
                "id_str": "18697326"
              },
              "id": 18697326,
              "screen_name": "cloudfoundry",
              "name": "Cloud Foundry",
              "indices": [
                80,
                93
              ]
            }
          ],
          "media": [
            {
              "extraData": {
                "source_user_id": 22467617,
                "sizes": {
                  "small": {
                    "w": 340,
                    "h": 227,
                    "resize": "fit"
                  },
                  "medium": {
                    "w": 600,
                    "h": 400,
                    "resize": "fit"
                  },
                  "large": {
                    "w": 1024,
                    "h": 683,
                    "resize": "fit"
                  },
                  "thumb": {
                    "w": 150,
                    "h": 150,
                    "resize": "crop"
                  }
                },
                "id_str": "736165421561348096",
                "source_status_id_str": "736165422073057281",
                "source_user_id_str": "22467617",
                "source_status_id": 736165422073057281
              },
              "id": 736165421561348096,
              "url": "https://t.co/PK6jGYOh48",
              "type": "photo",
              "indices": [
                143,
                144
              ],
              "mediaUrl": "http://pbs.twimg.com/media/CjdijPAXAAAb0qA.jpg",
              "mediaSecureUrl": "https://pbs.twimg.com/media/CjdijPAXAAAb0qA.jpg",
              "displayUrl": "pic.twitter.com/PK6jGYOh48",
              "expandedUrl": "http://twitter.com/neo4j/status/736165422073057281/photo/1"
            }
          ],
          "tickerSymbols": [],
          "hashTags": [
            {
              "extraData": {},
              "text": "Spring",
              "indices": [
                71,
                78
              ]
            },
            {
              "extraData": {},
              "text": "Neo4j",
              "indices": [
                100,
                106
              ]
            }
          ]
        },
        "user": {
          "extraData": {
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/1382313609/image_normal.jpg",
            "entities": {
              "url": {
                "urls": [
                  {
                    "url": "http://t.co/Sx4OOb9UnR",
                    "expanded_url": "http://d.hatena.ne.jp/tomoTaka/",
                    "display_url": "d.hatena.ne.jp/tomoTaka/",
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
            "id_str": "301336706",
            "default_profile": true,
            "is_translation_enabled": false,
            "has_extended_profile": false
          },
          "id": 301336706,
          "screenName": "tomo_taka01",
          "name": "tomo_taka",
          "url": "http://t.co/Sx4OOb9UnR",
          "profileImageUrl": "http://pbs.twimg.com/profile_images/1382313609/image_normal.jpg",
          "description": "Java programmer living in Ｏsaka Ｊapan.\n派遣社員として働いています。javaのスキル、英語のスキルアップに少しずつですが、頑張っています。（さぼってばかりでなかなか進みませんが涙、、、",
          "location": "",
          "createdDate": 1305796204000,
          "language": "ja",
          "statusesCount": 1925,
          "friendsCount": 648,
          "followersCount": 352,
          "favoritesCount": 280,
          "listedCount": 15,
          "following": false,
          "followRequestSent": false,
          "notificationsEnabled": false,
          "verified": false,
          "geoEnabled": false,
          "contributorsEnabled": false,
          "translator": false,
          "timeZone": null,
          "utcOffset": 0,
          "sidebarBorderColor": "C0DEED",
          "sidebarFillColor": "DDEEF6",
          "backgroundColor": "C0DEED",
          "backgroundImageUrl": "http://abs.twimg.com/images/themes/theme1/bg.png",
          "backgroundImageTiled": false,
          "textColor": "333333",
          "linkColor": "0084B4",
          "profileBannerUrl": null,
          "protected": false,
          "profileUrl": "http://twitter.com/tomo_taka01"
        },
        "unmodifiedText": "The 5-Minute Interview: \n\n@JavaFXpert at @Pivotal discusses #Spring, @CloudFoundry &amp; #Neo4j\n\nhttps://t.co/56ZrypwizS https://t.co/PK6jGYOh48",
        "retweet": true
      },
      {
        "extraData": {},
        "id": 736338730722557952,
        "idStr": "736338730722557952",
        "text": "[ニュース・市場動向] Neo4j 3.0がリリース，バイナリ通信プロトコルと標準ドライバを装備 https://t.co/74DrOKpv2N #OSS_INFO #OpenSource #OSS",
        "createdAt": 1464391808000,
        "fromUser": "oss_info",
        "profileImageUrl": "http://pbs.twimg.com/profile_images/2167131356/openstandia_logomark_normal.png",
        "toUserId": 0,
        "inReplyToStatusId": null,
        "inReplyToUserId": null,
        "inReplyToScreenName": "null",
        "fromUserId": 529104579,
        "languageCode": "ja",
        "source": "<a href=\"http://openstandia.jp/\" rel=\"nofollow\">OpenStandia</a>",
        "retweetCount": 0,
        "retweeted": false,
        "retweetedStatus": null,
        "favorited": false,
        "favoriteCount": 1,
        "entities": {
          "extraData": {
            "symbols": []
          },
          "urls": [
            {
              "extraData": {},
              "url": "https://t.co/74DrOKpv2N",
              "indices": [
                49,
                72
              ],
              "displayUrl": "openstandia.jp/oss_info/topic…",
              "expandedUrl": "http://openstandia.jp/oss_info/topic/topic-detail/20160524-50.html"
            }
          ],
          "mentions": [],
          "media": [],
          "tickerSymbols": [],
          "hashTags": [
            {
              "extraData": {},
              "text": "OSS_INFO",
              "indices": [
                73,
                82
              ]
            },
            {
              "extraData": {},
              "text": "OpenSource",
              "indices": [
                83,
                94
              ]
            },
            {
              "extraData": {},
              "text": "OSS",
              "indices": [
                95,
                99
              ]
            }
          ]
        },
        "user": {
          "extraData": {
            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
            "profile_image_url_https": "https://pbs.twimg.com/profile_images/2167131356/openstandia_logomark_normal.png",
            "entities": {
              "url": {
                "urls": [
                  {
                    "url": "http://t.co/sG40t4UkvF",
                    "expanded_url": "http://openstandia.jp/",
                    "display_url": "openstandia.jp",
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
            "id_str": "529104579",
            "default_profile": true,
            "is_translation_enabled": false,
            "has_extended_profile": false
          },
          "id": 529104579,
          "screenName": "oss_info",
          "name": "oss_info",
          "url": "http://t.co/sG40t4UkvF",
          "profileImageUrl": "http://pbs.twimg.com/profile_images/2167131356/openstandia_logomark_normal.png",
          "description": "(株)野村総合研究所オープンソースサポートサービス「OpenStandia（オープンスタンディア）」が提供する、OSS関連情報サイト。旬なOSSの最新トピックや、セミナー情報、バージョン情報を毎週更新。",
          "location": "",
          "createdDate": 1332132081000,
          "language": "ja",
          "statusesCount": 13189,
          "friendsCount": 7,
          "followersCount": 544,
          "favoritesCount": 0,
          "listedCount": 64,
          "following": false,
          "followRequestSent": false,
          "notificationsEnabled": false,
          "verified": false,
          "geoEnabled": false,
          "contributorsEnabled": false,
          "translator": false,
          "timeZone": "Irkutsk",
          "utcOffset": 28800,
          "sidebarBorderColor": "C0DEED",
          "sidebarFillColor": "DDEEF6",
          "backgroundColor": "C0DEED",
          "backgroundImageUrl": "http://abs.twimg.com/images/themes/theme1/bg.png",
          "backgroundImageTiled": false,
          "textColor": "333333",
          "linkColor": "0084B4",
          "profileBannerUrl": null,
          "protected": false,
          "profileUrl": "http://twitter.com/oss_info"
        },
        "unmodifiedText": "[ニュース・市場動向] Neo4j 3.0がリリース，バイナリ通信プロトコルと標準ドライバを装備 https://t.co/74DrOKpv2N #OSS_INFO #OpenSource #OSS",
        "retweet": false
      }
    ]
  }
]
```
