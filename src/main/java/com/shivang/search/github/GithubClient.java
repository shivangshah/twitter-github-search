package com.shivang.search.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivang.search.github.model.GithubRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import rx.Observable;
import rx.functions.Func1;

/**
 *
 * @author Shivang Shah
 */
@Component
public class GithubClient {

    private final AsyncRestTemplate asyncRestTemplate;
    private static final String GITHUB_SEARCH_API_BASE_URL = "https://api.github.com/search";
    private static final String GITHUB_REPOSITORY_SEARCH_URL
            = GITHUB_SEARCH_API_BASE_URL + "/repositories?q={searchTerm}&page={page}&per_page={pageSize}";

    @Autowired
    public GithubClient(AsyncRestTemplate asyncRestTemplate) {
        this.asyncRestTemplate = asyncRestTemplate;
    }

    public Observable<GithubRepository> searchGithubRepositories(String searchTerm,
            int page, int pageSize) {
        return Observable.from(asyncRestTemplate
                .exchange(GITHUB_REPOSITORY_SEARCH_URL, HttpMethod.GET,
                        HttpEntity.EMPTY, String.class, searchTerm,
                        page, pageSize))
                .flatMap((ResponseEntity<String> response) -> {
                    List<GithubRepository> repos = new ArrayList<>();
                    try {
                        String jsonResponse = response.getBody();
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode listOfRepos = mapper.readTree(jsonResponse).get("items");
                        for (final JsonNode repo : listOfRepos) {
                            repos.add(new GithubRepository(repo.get("name").asText(),
                                    repo.get("description").asText()));
                        }
                        return Observable.from(repos);
                    } catch (IOException ex) {
                        return Observable.error(ex);
                    }
        });

    }
}
