package com.shivang.search.github.model;

/**
 * @author Shivang Shah
 */
public class GithubRepository {

    private String name;
    private String description;

    public GithubRepository(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
