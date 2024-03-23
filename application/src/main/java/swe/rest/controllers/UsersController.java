package swe.rest.controllers;

import static swe.rest.controllers.UsersController.BASE_PATH;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.CommunityMemberEntity;
import swe.domain.entities.UserEntity;
import swe.rest.models.CommunityResource;
import swe.rest.models.CommunityResourceListResponse;
import swe.rest.models.CreateUserRequest;
import swe.rest.models.UserResource;
import swe.services.MembersService;
import swe.workflows.CreateUserWorkflow;

@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class UsersController {
  public static final String BASE_PATH = "/api/users";

  private final CreateUserWorkflow createUserWorkflow;
  private final MembersService membersService;

  @PostMapping
  ResponseEntity<UserResource> createUser(@RequestBody CreateUserRequest createUserRequest) {
    UserEntity user = createUserWorkflow.execute(createUserRequest);
    return ResponseEntity.ok(UserResource.convert(user));
  }

  @GetMapping("/{id}/communities")
  ResponseEntity<CommunityResourceListResponse> getCommunities(@PathVariable("id") String userId) {
    List<CommunityMemberEntity> memberships = membersService.findByUserId(userId);
    List<CommunityEntity> communityEntities =
        memberships.stream().map(m -> m.getCommunity()).toList();
    return ResponseEntity.ok(
        CommunityResourceListResponse.builder()
            .communities(
                communityEntities.stream().map(ce -> CommunityResource.convert(ce)).toList())
            .build());
  }
}
