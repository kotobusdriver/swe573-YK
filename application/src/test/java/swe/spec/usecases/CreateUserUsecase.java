package swe.spec.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swe.rest.controllers.UsersController;
import swe.rest.models.CreateUserRequest;
import swe.rest.models.UserResource;

@Component
public class CreateUserUsecase {
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;

  public UserResource invokeUsecase(String name, String email, String password) throws Exception {
    CreateUserRequest request =
        CreateUserRequest.builder().name(name).email(email).password(password).build();

    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.post(UsersController.BASE_PATH)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult createUserResponse = mockMvc.perform(builder).andReturn();

    Assertions.assertEquals(200, createUserResponse.getResponse().getStatus());

    UserResource user =
        objectMapper.readValue(
            createUserResponse.getResponse().getContentAsString(), UserResource.class);

    Assertions.assertEquals(name, user.getName());
    Assertions.assertNotNull(user.getId());

    return user;
  }
}
