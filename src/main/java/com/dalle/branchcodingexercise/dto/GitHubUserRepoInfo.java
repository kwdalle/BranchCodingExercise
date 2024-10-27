package com.dalle.branchcodingexercise.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Record class to hold the info from GitHub /users/{user}/repos API
 *
 * @param name
 * @param url
 */
public record GitHubUserRepoInfo(
        String name,
        @SerializedName(value = "url", alternate = "svn_url")
        String url
) {
}
