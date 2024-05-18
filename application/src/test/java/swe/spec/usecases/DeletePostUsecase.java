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
import swe.rest.controllers.PostsController;

@Component
public class DeletePostUsecase {
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;

  public void invokeUsecase(String postId, String deletingMemberId) throws Exception {
    MockHttpServletRequestBuilder deleteRequest =
        MockMvcRequestBuilders.delete(PostsController.BASE_PATH + "/" + postId)
            .queryParam("memberId", deletingMemberId)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult createPostResponse = mockMvc.perform(deleteRequest).andReturn();

    Assertions.assertEquals(204, createPostResponse.getResponse().getStatus());
  }
}
