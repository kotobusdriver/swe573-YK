package swe.rest.controllers;

import static swe.rest.controllers.MembersController.BASE_PATH;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swe.domain.entities.CommunityMemberEntity;
import swe.rest.models.*;
import swe.services.MembersService;

@CrossOrigin
@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class MembersController {
  public static final String BASE_PATH = "/api/members";

  private final MembersService membersService;

  @GetMapping("/{id}")
  ResponseEntity<CommunityMemberUserResource> getCommunityMember(@PathVariable("id") String id) {
    Optional<CommunityMemberEntity> member = membersService.findByMemberId(id);
    if (member.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(CommunityMemberUserResource.convert(member.get()));
  }
}
