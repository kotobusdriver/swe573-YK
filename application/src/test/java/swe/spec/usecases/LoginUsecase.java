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
import swe.rest.controllers.LoginController;
import swe.rest.models.LoginRequest;

@Component
public class LoginUsecase {
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;

  public void invokeUsecase(String email, String password) throws Exception {
    LoginRequest request =
            LoginRequest.builder().email(email).password(password).build();

    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.post(LoginController.BASE_PATH)
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult loginResponse = mockMvc.perform(builder).andReturn();

    Assertions.assertEquals(200, loginResponse.getResponse().getStatus());
  }
}
