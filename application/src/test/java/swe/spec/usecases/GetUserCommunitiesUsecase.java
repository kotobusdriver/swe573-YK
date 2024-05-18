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
import swe.rest.models.CommunityResourceListResponse;

@Component
public class GetUserCommunitiesUsecase {
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;

  public CommunityResourceListResponse invokeUsecase(String userId) throws Exception {
    MockHttpServletRequestBuilder getCommunitiesOfUserRequestBuilder =
        MockMvcRequestBuilders.get(UsersController.BASE_PATH + "/" + userId + "/communities")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult getCommunitiesResponse =
        mockMvc.perform(getCommunitiesOfUserRequestBuilder).andReturn();

    Assertions.assertEquals(200, getCommunitiesResponse.getResponse().getStatus());

    CommunityResourceListResponse communityList =
        objectMapper.readValue(
            getCommunitiesResponse.getResponse().getContentAsString(),
            CommunityResourceListResponse.class);

    return communityList;
  }

  public void checkUserHasAccessToCommunity(String userId, String communityId) throws Exception {
    CommunityResourceListResponse communityList = invokeUsecase(userId);

    Assertions.assertFalse(communityList.getCommunities().isEmpty());

    Assertions.assertTrue(
        communityList.getCommunities().stream().map(c -> c.getId()).toList().contains(communityId));
  }
}
