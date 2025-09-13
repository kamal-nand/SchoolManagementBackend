package com.kamal.school.web;

import com.kamal.school.domain.Role;
import com.kamal.school.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuthControllerIT {

    @LocalServerPort
    int port;

    @Autowired
    private UserRepository userRepository;

    private String baseUrl;
    private final RestTemplate rest = new RestTemplate();

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/api/auth";
    }

    @Test
    void registerAndLogin() {
        // Register
        var regUrl = baseUrl + "/register?username=tester&password=secret&role=" + Role.ADMIN.name();
        var regResp = rest.postForEntity(regUrl, null, Map.class);
        assertThat(regResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userRepository.findByUsername("tester")).isPresent();

        // Login
        var loginUrl = baseUrl + "/login";
        var payload = Map.of("username", "tester", "password", "secret");
        var loginResp = rest.postForEntity(loginUrl, payload, Map.class);
        assertThat(loginResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResp.getBody()).containsKey("token");
    }
}
