package swe.rest.controllers;

import static swe.rest.controllers.CommunitiesController.BASE_PATH;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swe.domain.entities.CommunityEntity;
import swe.rest.models.CommunityResource;
import swe.rest.models.CreateCommunityRequest;
import swe.workflows.CreateCommunityWorkflow;

@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class CommunitiesController {
  public static final String BASE_PATH = "/api/communities";

  private CreateCommunityWorkflow createCommunityWorkflow;

  @PostMapping
  ResponseEntity<CommunityResource> createCommunity(
      @RequestBody CreateCommunityRequest createCommunityRequest) {
    CommunityEntity community = createCommunityWorkflow.execute(createCommunityRequest);
    return ResponseEntity.ok(CommunityResource.convert(community));
  }
}
