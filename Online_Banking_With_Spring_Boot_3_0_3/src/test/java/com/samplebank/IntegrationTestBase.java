package com.samplebank;

import com.samplebank.auth.dto.UserDto;
import com.samplebank.auth.service.UserService;
import com.samplebank.client.dto.ClientDto;
import com.samplebank.client.service.ClientService;
import com.samplebank.security.JwtTokenUtil;
import com.samplebank.utilities.GeneralConstants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class IntegrationTestBase {

    @LocalServerPort
    protected Integer port;
    @Autowired
    protected UserService userService;

    @Autowired
    protected ClientService clientService;

    protected HttpHeaders headers;
    @Autowired
    protected TestRestTemplate testRestTemplate;
    protected  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.1-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl); // db name = test
        registry.add("spring.datasource.username", postgres::getUsername); // test
        registry.add("spring.datasource.password", postgres::getPassword); // test
    }

    /**
     * Authentication for test classes with user passed in param
     */
    protected void setUpUser(String role){
        final var adminUsername = "test_admin_user@test.com";
        final var clientUsername = "test_client@test.com";
        userService.createUser(new UserDto(adminUsername, "admin_password"), null);
        clientService.createClient(new ClientDto("test", "test", "FEMALE", "6453645", "55555555", clientUsername));
        headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        var userName = GeneralConstants.ROLE_ADMIN.equals(role) ? adminUsername : clientUsername;
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+ JwtTokenUtil.createToken(userService.findUserByUserName(userName)));
    }
}
