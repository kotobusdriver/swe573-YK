package swe.spec.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swe.rest.controllers.PostsController;
import swe.rest.models.CreatePostRequest;
import swe.rest.models.PostFieldResource;
import swe.rest.models.PostResource;

@Component
public class SendPostUsecase {
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;

  public PostResource invokeUsecase(
          String communityId, String memberId, String templateId, List<PostFieldResource> fields) throws Exception {
    CreatePostRequest postToCommunityRequest =
        CreatePostRequest.builder()
            .communityId(communityId)
            .byMemberId(memberId)
            .templateId(templateId)
            .fields(fields)
            .build();

    MockHttpServletRequestBuilder createPostBuilder =
        MockMvcRequestBuilders.post(PostsController.BASE_PATH)
            .content(objectMapper.writeValueAsString(postToCommunityRequest))
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult createPostResponse = mockMvc.perform(createPostBuilder).andReturn();

    Assertions.assertEquals(200, createPostResponse.getResponse().getStatus());

    PostResource post =
        objectMapper.readValue(
            createPostResponse.getResponse().getContentAsString(), PostResource.class);

    Assertions.assertNotNull(post.getId());

    return post;
  }
}
