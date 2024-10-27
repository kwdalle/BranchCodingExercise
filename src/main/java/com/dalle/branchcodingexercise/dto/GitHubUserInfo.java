package com.dalle.branchcodingexercise.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Record class to hold the info from GitHub /users/{user} API
 *
 * @param login
 * @param name
 * @param location
 * @param email
 * @param avatarUrl
 * @param url
 * @param createdAt
 */
public record GitHubUserInfo(
        String login,
        String name,
        String location,
        String email,
        @SerializedName("avatar_url") String avatarUrl,
        @SerializedName("html_url") String url,
        @SerializedName("created_at") String createdAt
) {
}
