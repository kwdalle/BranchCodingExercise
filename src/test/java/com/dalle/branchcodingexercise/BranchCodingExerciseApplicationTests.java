package com.dalle.branchcodingexercise;

import com.dalle.branchcodingexercise.dto.GitHubUserResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BranchCodingExerciseApplicationTests {

    @Autowired
    ResourceLoader resourceLoader;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private Gson gson;

    @Test
    void happyPath() throws IOException {
        String url = "http://localhost:" + port + "/api/user/octocat";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        GitHubUserResponse userResponse = gson.fromJson(response.getBody(), GitHubUserResponse.class);
        GitHubUserResponse expectedResponse = gson.fromJson(new String(
                        Files.readAllBytes(resourceLoader.getResource("classpath:json/happy_path.json").getFile().toPath())),
                GitHubUserResponse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTopLevelFields(expectedResponse, userResponse);
        assertValuesOfFirstRepo(expectedResponse, userResponse);
    }

    @Test
    void invalidInput() {
        String url = "http://localhost:" + port + "/api/user/octoadsfasdfcat";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private void assertValuesOfFirstRepo(GitHubUserResponse expectedResponse, GitHubUserResponse userResponse) {
        Assertions.assertEquals(expectedResponse.getRepos().getFirst().name(), userResponse.getRepos().getFirst().name());
        Assertions.assertEquals(expectedResponse.getRepos().getFirst().url(), userResponse.getRepos().getFirst().url());
    }

    private void assertTopLevelFields(GitHubUserResponse expectedResponse, GitHubUserResponse userResponse) {
        Assertions.assertEquals(expectedResponse.getUserName(), userResponse.getUserName());
        Assertions.assertEquals(expectedResponse.getDisplayName(), userResponse.getDisplayName());
        Assertions.assertEquals(expectedResponse.getAvatar(), userResponse.getAvatar());
        Assertions.assertEquals(expectedResponse.getGeoLocation(), userResponse.getGeoLocation());
        Assertions.assertEquals(expectedResponse.getEmail(), userResponse.getEmail());
        Assertions.assertEquals(expectedResponse.getUrl(), userResponse.getUrl().trim());
        Assertions.assertEquals(expectedResponse.getCreatedAt(), userResponse.getCreatedAt());
    }

}
