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
import swe.rest.controllers.CommunitiesController;
import swe.rest.models.*;

@Component
public class SubscribeToCommunityUsecase {
    @Autowired protected ObjectMapper objectMapper;
    @Autowired protected MockMvc mockMvc;

    public CommunityMemberUserResource invokeUsecase(String userId, String communityId) throws Exception {
        SubscriptionToCommunityRequest subscriptionToCommunityRequest =
                SubscriptionToCommunityRequest.builder()
                        .userId(userId)
                        .build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(CommunitiesController.BASE_PATH + "/" + communityId + "/members")
                        .content(objectMapper.writeValueAsString(subscriptionToCommunityRequest))
                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult subscribeResponse = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(200, subscribeResponse.getResponse().getStatus());

        CommunityMemberUserResource membership =
                objectMapper.readValue(
                        subscribeResponse.getResponse().getContentAsString(), CommunityMemberUserResource.class);

        Assertions.assertNotNull(membership.getId());
        Assertions.assertEquals(userId, membership.getUserId());

        return membership;
    }
}
