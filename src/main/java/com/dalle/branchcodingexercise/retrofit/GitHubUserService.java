package com.dalle.branchcodingexercise.retrofit;

import com.dalle.branchcodingexercise.dto.GitHubUserInfo;
import com.dalle.branchcodingexercise.dto.GitHubUserRepoInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.List;

/**
 * Retrofit Service for GitHub to make HTTP API calls
 */
public interface GitHubUserService {

    /**
     * Retrieves the user info from GitHub
     *
     * @param user username on GitHub
     * @return {@link GitHubUserInfo} DTO of the response from GitHub
     */
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: kwdalle-Branch-Exercise-App"
    })
    @GET("users/{user}")
    Call<GitHubUserInfo> getUserInfo(@Path("user") String user);

    /**
     * Retrieves teh users' repository information from GitHub
     *
     * @param user username on GitHub
     * @return {@link GitHubUserRepoInfo} DTO of the response from GitHub
     */
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: kwdalle-Branch-Exercise-App"
    })
    @GET("users/{user}/repos")
    Call<List<GitHubUserRepoInfo>> getUserRepos(@Path("user") String user);

}
