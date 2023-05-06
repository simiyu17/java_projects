package com.samplebank.client.service.integration;

import com.samplebank.IntegrationTestBase;
import com.samplebank.client.dto.ClientDto;
import com.samplebank.utilities.GeneralConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

@Sql({"/sql/insertClientData.sql", "/sql/insertUserData.sql"})
class ClientServiceTest extends IntegrationTestBase {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setupUser(){
        setUpUser(GeneralConstants.ROLE_ADMIN);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void given_client_id_for_an_existing_client_then_return_client(){
        var getClientIdUrl = GeneralConstants.ADMIN_ENDPOINT+"client/"+10001;
        ResponseEntity<ClientDto> response = this.testRestTemplate.exchange(getClientIdUrl, HttpMethod.GET, new HttpEntity<>(headers), ClientDto.class);
        Assertions.assertThat(response).isNotNull();
        var clientDto = response.getBody();
        Assertions.assertThat(clientDto).isNotNull();
        Assertions.assertThat(clientDto.emailAddress()).isEqualTo("john.doe2@gmail.com");
        Assertions.assertThat(clientDto.firstName()).isEqualTo("John");
    }

    @Test
    void given_request_to_get_all_existing_clients_then_return_all_available_clients() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.create(GeneralConstants.ADMIN_ENDPOINT + "clients")).headers(headers).contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONAssert.assertEquals("[{id: 1},{id: 10001}]", result.getResponse().getContentAsString(), false);
        Assertions.assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    }
}
