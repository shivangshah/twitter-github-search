package com.shivang.search.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivang.search.github.model.CustomException;
import com.shivang.search.github.model.GithubRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shivang Shah
 */
@Component
public class GithubClient {

    private final AsyncRestTemplate asyncRestTemplate;
    private static final String GITHUB_SEARCH_API_BASE_URL = "https://api.github.com/search";
    public static final String GITHUB_REPOSITORY_SEARCH_URL
            = GITHUB_SEARCH_API_BASE_URL + "/repositories?q={searchTerm}&page={page}&per_page={pageSize}";
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);

    @Autowired
    public GithubClient(AsyncRestTemplate asyncRestTemplate) {
        this.asyncRestTemplate = asyncRestTemplate;
        LOGGER.debug("Github client initialized ..");
    }

    public Observable<GithubRepository> searchGithubRepositories(String searchTerm,
                                                                 int page, int pageSize) {
        return Observable.from(asyncRestTemplate
                .exchange(GITHUB_REPOSITORY_SEARCH_URL, HttpMethod.GET,
                        HttpEntity.EMPTY, String.class, searchTerm,
                        page, pageSize))
                .flatMap((ResponseEntity<String> response) -> {
                    try {
                        if (!response.getStatusCode().is2xxSuccessful()) {
                            throw new CustomException(response.getStatusCode(), "Github request was not successful");
                        }
                        List<GithubRepository> repos = new ArrayList<>();
                        String jsonResponse = response.getBody();
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode listOfRepos = mapper.readTree(jsonResponse).get("items");
                        for (final JsonNode repo : listOfRepos) {
                            repos.add(new GithubRepository(repo.get("name").asText(),
                                    repo.get("description").asText()));
                        }
                        return Observable.from(repos);
                    } catch (Exception ex) {
                        return Observable.error(ex);
                    }
                });

    }
}
