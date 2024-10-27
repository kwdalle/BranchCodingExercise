package com.dalle.branchcodingexercise.controller;

import com.dalle.branchcodingexercise.dto.GitHubUserInfo;
import com.dalle.branchcodingexercise.dto.GitHubUserRepoInfo;
import com.dalle.branchcodingexercise.dto.GitHubUserResponse;
import com.dalle.branchcodingexercise.retrofit.GitHubUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import retrofit2.Response;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Rest controller that uses {@link GitHubUserService} to make HTTP calls to GitHub to build the {@link GitHubUserResponse}
 * api response.
 */
@RestController
public class GitHubController {

    GitHubUserService gitHubUserService;

    public GitHubController(GitHubUserService gitHubUserService) {
        this.gitHubUserService = gitHubUserService;
    }

    /**
     * @param user the GitHub username used to call the GitHub APIs for example 'octocat'
     * @return {@link GitHubUserResponse}
     */
    @GetMapping("/api/user/{user}")
    @Cacheable("getUser")
    public GitHubUserResponse getUser(@PathVariable("user") String user) {
        try {
            return getGitHubUserResponse(user);
        } catch (ResponseStatusException r) {
            throw (r);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Calls the two GitHub APIs /users/{user} and users/{user}/repo to compose the response
     *
     * @param user user the GitHub username used to call the GitHub APIs for example 'octocat'
     * @return {@link GitHubUserResponse}
     */
    @NotNull
    private GitHubUserResponse getGitHubUserResponse(String user) throws IOException, ParseException {
        Response<GitHubUserInfo> gitHubUserInfoResponse = gitHubUserService.getUserInfo(user).execute();
        Response<List<GitHubUserRepoInfo>> repoResponse = gitHubUserService.getUserRepos(user).execute();

        if (gitHubUserInfoResponse.isSuccessful() && repoResponse.isSuccessful()) {
            return new GitHubUserResponse(gitHubUserInfoResponse.body(), repoResponse.body());
        } else {
            throw new ResponseStatusException(HttpStatus.valueOf(repoResponse.code()));
        }
    }
}
