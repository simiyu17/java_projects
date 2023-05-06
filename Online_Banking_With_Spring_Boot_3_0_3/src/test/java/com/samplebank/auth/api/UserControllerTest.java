package com.samplebank.auth.api;

import com.samplebank.UnitTestBase;
import com.samplebank.auth.dto.UserDto;
import com.samplebank.auth.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
class UserControllerTest extends UnitTestBase {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAvailableUsers() throws Exception {
        List<UserDto> users = List.of(new UserDto("simiyu", "password"));
        when(userService.findAvailableUsers()).thenReturn(users);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();
        verify(userService, atLeastOnce()).findAvailableUsers();
        Assertions.assertEquals("[{\"id\":null,\"name\":null,\"username\":\"simiyu\",\"roles\":null,\"passwordHidden\":\"<[Protected]>\"}]", result.getResponse().getContentAsString());
    }
}