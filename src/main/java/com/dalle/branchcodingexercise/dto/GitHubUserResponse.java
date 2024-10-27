package com.dalle.branchcodingexercise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Response object composed of the two GitHub API response objects
 */
@Getter
public class GitHubUserResponse {
    private final String avatar;
    private final String email;
    private final String url;
    private final List<GitHubUserRepoInfo> repos;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("geo_location")
    private String geoLocation;
    @SerializedName("created_at")
    private String createdAt;

    /**
     * Builds the response object from the two GitHub API objects
     *
     * @param userInfo response from /users/{user}
     * @param repos    response from /users/{user}/repos
     */
    public GitHubUserResponse(GitHubUserInfo userInfo, List<GitHubUserRepoInfo> repos) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.userName = userInfo.login();
        this.displayName = userInfo.name();
        this.avatar = userInfo.avatarUrl();
        this.geoLocation = userInfo.location();
        this.email = userInfo.email();
        this.url = userInfo.url();
        this.createdAt = targetFormat.format(dateFormat.parse(userInfo.createdAt()));
        this.repos = repos;
    }
}
