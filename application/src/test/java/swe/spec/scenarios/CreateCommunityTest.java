package swe.spec.scenarios;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swe.domain.models.CommunityStatus;
import swe.domain.models.CommunityVisibility;
import swe.domain.models.FieldType;
import swe.rest.controllers.CommunitiesController;
import swe.rest.controllers.PostsController;
import swe.rest.controllers.UsersController;
import swe.rest.models.*;
import swe.spec.IntegrationTest;

public class CreateCommunityTest extends IntegrationTest {
  @Test
  public void create_community() throws Exception {
    // Create a user
    CreateUserRequest createUserRequest =
        CreateUserRequest.builder()
            .name("Amy")
            .email("amy@company.com")
            .password("password")
            .build();

    MockHttpServletRequestBuilder createUserbuilder =
        MockMvcRequestBuilders.post(UsersController.BASE_PATH)
            .content(objectMapper.writeValueAsString(createUserRequest))
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult createUserResponse = mockMvc.perform(createUserbuilder).andReturn();

    Assertions.assertEquals(200, createUserResponse.getResponse().getStatus());

    UserResource user =
        objectMapper.readValue(
            createUserResponse.getResponse().getContentAsString(), UserResource.class);

    Assertions.assertEquals("Amy", user.getName());
    Assertions.assertNotNull(user.getId());

    // Create a community
    CreateCommunityRequest createCommunityRequest =
        CreateCommunityRequest.builder()
            .name("BirdWatchers")
            .description("We watch birds!!")
            .visibility(CommunityVisibility.PUBLIC)
            .adminUserId(user.getId())
            .template(buildMySpecialTemplate())
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

    Assertions.assertEquals("BirdWatchers", community.getName());
    Assertions.assertEquals("We watch birds!!", community.getDescription());
    Assertions.assertEquals(CommunityVisibility.PUBLIC, community.getVisibility());
    Assertions.assertEquals(CommunityStatus.ACTIVE, community.getStatus());
    Assertions.assertNotNull(community.getId());

    // Check user can see new community in his/her communities list
    MockHttpServletRequestBuilder getCommunitiesOfUserRequestBuilder =
        MockMvcRequestBuilders.get(UsersController.BASE_PATH + "/" + user.getId() + "/communities")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult getCommunitiesResponse =
        mockMvc.perform(getCommunitiesOfUserRequestBuilder).andReturn();

    Assertions.assertEquals(200, getCommunitiesResponse.getResponse().getStatus());

    CommunityResourceListResponse communityList =
        objectMapper.readValue(
            getCommunitiesResponse.getResponse().getContentAsString(),
            CommunityResourceListResponse.class);

    Assertions.assertFalse(communityList.getCommunities().isEmpty());

    Assertions.assertTrue(
        communityList.getCommunities().stream()
            .map(c -> c.getId())
            .toList()
            .contains(community.getId()));

    // Check user is community member and admin
    MockHttpServletRequestBuilder getCommunityMembersRequestBuilder =
        MockMvcRequestBuilders.get(
                CommunitiesController.BASE_PATH + "/" + community.getId() + "/members")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult getCommunityMembersResponse =
        mockMvc.perform(getCommunityMembersRequestBuilder).andReturn();

    Assertions.assertEquals(200, getCommunitiesResponse.getResponse().getStatus());

    CommunityMemberListResponse communityMemberList =
        objectMapper.readValue(
            getCommunityMembersResponse.getResponse().getContentAsString(),
            CommunityMemberListResponse.class);

    Assertions.assertFalse(communityMemberList.getMembers().isEmpty());

    Optional<CommunityMemberResource> member =
        communityMemberList.getMembers().stream()
            .filter(c -> c.getUserId().equals(user.getId()))
            .findFirst();

    Assertions.assertTrue(member.isPresent());
    Assertions.assertTrue(member.get().getAdmin());

    // Send a new post to a community
    CreatePostRequest postToCommunityRequest =
        CreatePostRequest.builder()
            .communityId(community.getId())
            .byMemberId(member.get().getId())
            .fields(createMySpecialPostFields(community))
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
  }

  private List<PostFieldResource> createMySpecialPostFields(CommunityResource community) {
    Map<String, PostTemplateResource.TemplateFieldResource> fieldDefinitions =
        community.getPostTemplateResource().getFields().stream()
            .collect(Collectors.toMap(f -> f.getName(), Function.identity()));

    return List.of(
        PostFieldResource.builder()
            .templateFieldId(fieldDefinitions.get("Title").getId())
            .data("I saw a crow")
            .build(),
        PostFieldResource.builder()
            .templateFieldId(fieldDefinitions.get("Picture").getId())
            .data("http://pictures.com/the-crow.jpg")
            .build(),
        PostFieldResource.builder()
            .templateFieldId(fieldDefinitions.get("Date").getId())
            .data("2024-02-09")
            .build());
  }

  private PostTemplate buildMySpecialTemplate() {
    return PostTemplate.builder()
        .field(
            PostTemplate.TemplateField.builder()
                .name("Title")
                .optional(false)
                .type(FieldType.TEXT)
                .build())
        .field(
            PostTemplate.TemplateField.builder()
                .name("Picture")
                .optional(false)
                .type(FieldType.IMAGE)
                .build())
        .field(
            PostTemplate.TemplateField.builder()
                .name("Date")
                .optional(false)
                .type(FieldType.DATE)
                .build())
        .field(
            PostTemplate.TemplateField.builder()
                .name("Story")
                .optional(true)
                .type(FieldType.TEXT)
                .build())
        .build();
  }
}
