package com.samplebank.auth.api.integration;

import com.samplebank.IntegrationTestBase;
import com.samplebank.utilities.GeneralConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

class UserControllerTest extends IntegrationTestBase {

    @BeforeEach
    void setUp(){
        setUpUser(GeneralConstants.ROLE_ADMIN);
    }

    @Test
    @Sql({"/sql/insertClientData.sql", "/sql/insertUserData.sql"})
    void getUsers() {
        ResponseEntity<List> users = this.testRestTemplate.exchange("/admin/users", HttpMethod.GET, new HttpEntity<>(headers), List.class);
        var result = users.getBody();
        Assertions.assertThat(result).isNotNull().hasSize(3);
    }
}
