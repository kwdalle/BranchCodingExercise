package com.dalle.branchcodingexercise.config;

import com.dalle.branchcodingexercise.retrofit.GitHubUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Configuration for the {@link GitHubUserService} that provides a Retrofit implementation to inject.
 */
@Configuration
public class GitHubConfiguration {

    @Bean
    public GitHubUserService gitHubUserService() {
        return new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(GitHubUserService.class);
    }
}
