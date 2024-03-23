package swe.spec.scenarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swe.rest.controllers.UsersController;
import swe.rest.models.CreateUserRequest;
import swe.rest.models.UserResource;
import swe.spec.IntegrationTest;

public class CreateUserTest extends IntegrationTest {
    @Test
    public void create_user() throws Exception {
        CreateUserRequest request = CreateUserRequest.builder()
                .name("Amy")
                .email("amy@company.com")
                .password("password")
                .build();

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post(UsersController.BASE_PATH)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult createUserResponse = mockMvc.perform(builder).andReturn();

        Assertions.assertEquals(200, createUserResponse.getResponse().getStatus());

        UserResource user =
                objectMapper.readValue(createUserResponse.getResponse().getContentAsString(), UserResource.class);

        Assertions.assertEquals("Amy", user.getName());
        Assertions.assertNotNull(user.getId());
    }
}
