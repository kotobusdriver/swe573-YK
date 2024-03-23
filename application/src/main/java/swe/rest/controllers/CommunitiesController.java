package swe.rest.controllers;

import static swe.rest.controllers.CommunitiesController.BASE_PATH;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.CommunityMemberEntity;
import swe.rest.models.*;
import swe.services.MembersService;
import swe.workflows.CreateCommunityWorkflow;

@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class CommunitiesController {
  public static final String BASE_PATH = "/api/communities";

  private final CreateCommunityWorkflow createCommunityWorkflow;
  private final MembersService membersService;

  @PostMapping
  ResponseEntity<CommunityResource> createCommunity(
      @RequestBody CreateCommunityRequest createCommunityRequest) {
    CommunityEntity community = createCommunityWorkflow.execute(createCommunityRequest);
    return ResponseEntity.ok(CommunityResource.convert(community));
  }

  @GetMapping("/{id}/members")
  ResponseEntity<CommunityMemberListResponse> getCommunities(
      @PathVariable("id") String communityId) {
    List<CommunityMemberEntity> memberships = membersService.findByCommunityId(communityId);
    return ResponseEntity.ok(
        CommunityMemberListResponse.builder()
            .members(memberships.stream().map(m -> CommunityMemberResource.convert(m)).toList())
            .build());
  }
}
