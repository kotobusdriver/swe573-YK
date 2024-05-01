package swe.rest.controllers;

import static swe.rest.controllers.CommunitiesController.BASE_PATH;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.CommunityMemberEntity;
import swe.domain.entities.PostEntity;
import swe.domain.repositories.CommunitiesRepository;
import swe.domain.repositories.PostsRepository;
import swe.rest.models.*;
import swe.services.MembersService;
import swe.workflows.CreateCommunityWorkflow;

@CrossOrigin
@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class CommunitiesController {
  public static final String BASE_PATH = "/api/communities";

  private final CreateCommunityWorkflow createCommunityWorkflow;
  private final MembersService membersService;
  private final CommunitiesRepository communitiesRepository;
  private final PostsRepository postsRepository;

  @PostMapping
  ResponseEntity<CommunityResource> createCommunity(
      @RequestBody CreateCommunityRequest createCommunityRequest) {
    CommunityEntity community = createCommunityWorkflow.execute(createCommunityRequest);
    return ResponseEntity.ok(CommunityResource.convert(community));
  }

  @PostMapping("/{id}/members")
  ResponseEntity<CommunityMemberUserResource> createCommunityMember(
          @PathVariable("id") String communityId, @RequestBody SubscriptionToCommunityRequest request) {
    CommunityMemberEntity member = membersService.createMember(communityId, request.getUserId());
    return ResponseEntity.ok(CommunityMemberUserResource.convert(member));
  }

  @GetMapping("/{id}/members")
  ResponseEntity<CommunityMemberListResponse> getCommunityMembers(
      @PathVariable("id") String communityId) {
    List<CommunityMemberEntity> memberships = membersService.findByCommunityId(communityId);
    return ResponseEntity.ok(
        CommunityMemberListResponse.builder()
            .members(memberships.stream().map(m -> CommunityMemberResource.convert(m)).toList())
            .build());
  }

  @GetMapping
  ResponseEntity<CommunityResourceListResponse> getCommunities() {
    List<CommunityEntity> communities = communitiesRepository.findAll();
    return ResponseEntity.ok(
            CommunityResourceListResponse.builder()
                    .communities(communities.stream().map(m -> CommunityResource.convert(m)).toList())
                    .build());
  }

  @GetMapping("/{id}")
  ResponseEntity<CommunityResource> getCommunities(@PathVariable("id") String communityId) {
    Optional<CommunityEntity> community = communitiesRepository.findById(communityId);
    if (community.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(CommunityResource.convert(community.get()));
  }

  @GetMapping("/{id}/posts")
  ResponseEntity<PostResourceListResponse> listPosts(@PathVariable("id") String communityId){
    List<PostEntity> posts = postsRepository.findByCommunityId(communityId);
    return ResponseEntity.ok(
            PostResourceListResponse.builder()
                    .posts(posts.stream().map(m -> PostResource.convert(m)).toList())
                    .build());
  }
}
