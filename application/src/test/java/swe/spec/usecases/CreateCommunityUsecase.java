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
import swe.domain.models.CommunityStatus;
import swe.domain.models.CommunityVisibility;
import swe.rest.controllers.CommunitiesController;
import swe.rest.models.*;

import java.util.List;

@Component
public class CreateCommunityUsecase {
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;

  public CommunityResource invokeUsecase(
          String name, String description, String adminUserId, List<PostTemplate> templates) throws Exception {
    CreateCommunityRequest createCommunityRequest =
        CreateCommunityRequest.builder()
            .name(name)
            .description(description)
            .visibility(CommunityVisibility.PUBLIC)
            .adminUserId(adminUserId)
            .templates(templates)
            .build();

    MockHttpServletRequestBuilder createCommunityBuilder =
        MockMvcRequestBuilders.post(CommunitiesController.BASE_PATH)
            .content(objectMapper.writeValueAsString(createCommunityRequest))
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult createCommunityResponse = mockMvc.perform(createCommunityBuilder).andReturn();

    Assertions.assertEquals(200, createCommunityResponse.getResponse().getStatus());

    CommunityResource community =
        objectMapper.readValue(
            createCommunityResponse.getResponse().getContentAsString(), CommunityResource.class);

    Assertions.assertEquals(name, community.getName());
    Assertions.assertEquals(description, community.getDescription());
    Assertions.assertEquals(CommunityVisibility.PUBLIC, community.getVisibility());
    Assertions.assertEquals(CommunityStatus.ACTIVE, community.getStatus());
    Assertions.assertNotNull(community.getId());

    return community;
  }
}
