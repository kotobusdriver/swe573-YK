package swe.spec.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swe.rest.controllers.CommunitiesController;
import swe.rest.models.CommunityMemberListResponse;
import swe.rest.models.CommunityMemberResource;

@Component
public class GetCommunityMembersUsecase {
  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected MockMvc mockMvc;

  public CommunityMemberListResponse invokeUsecase(String communityId) throws Exception {
    MockHttpServletRequestBuilder getCommunityMembersRequestBuilder =
        MockMvcRequestBuilders.get(CommunitiesController.BASE_PATH + "/" + communityId + "/members")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult getCommunityMembersResponse =
        mockMvc.perform(getCommunityMembersRequestBuilder).andReturn();

    Assertions.assertEquals(200, getCommunityMembersResponse.getResponse().getStatus());

    CommunityMemberListResponse communityMemberList =
        objectMapper.readValue(
            getCommunityMembersResponse.getResponse().getContentAsString(),
            CommunityMemberListResponse.class);

    return communityMemberList;
  }

  public CommunityMemberResource getUserMembershipForCommunity(String userId, String communityId)
      throws Exception {
    CommunityMemberListResponse communityMemberList = invokeUsecase(communityId);

    Assertions.assertFalse(communityMemberList.getMembers().isEmpty());

    Optional<CommunityMemberResource> member =
        communityMemberList.getMembers().stream()
            .filter(c -> c.getUserId().equals(userId))
            .findFirst();

    Assertions.assertTrue(member.isPresent());

    return member.get();
  }

  public void checkUserIsCommunityAdmin(String userId, String communityId) throws Exception {
    CommunityMemberResource member = getUserMembershipForCommunity(userId, communityId);
    Assertions.assertTrue(member.getAdmin());
  }
}
