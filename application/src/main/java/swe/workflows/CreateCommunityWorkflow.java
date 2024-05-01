package swe.workflows;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.CommunityMemberEntity;
import swe.rest.models.CreateCommunityRequest;
import swe.services.CommunitiesService;
import swe.services.MembersService;

@Component
@AllArgsConstructor
public class CreateCommunityWorkflow implements Workflow<CreateCommunityRequest, CommunityEntity> {
  private final CommunitiesService communitiesService;
  private final MembersService membersService;

  @Override
  public CommunityEntity execute(CreateCommunityRequest command) {
    CommunityEntity community = createCommunity(command);
    CommunityMemberEntity member = createMember(community, command.getAdminUserId());
    makeAdmin(member);
    return community;
  }

  private CommunityEntity createCommunity(CreateCommunityRequest command) {
    return communitiesService.createCommunity(command);
  }

  private CommunityMemberEntity createMember(CommunityEntity community, String userId) {
    return membersService.createMember(community.getId(), userId);
  }

  private void makeAdmin(CommunityMemberEntity member) {
    membersService.makeAdmin(member);
  }
}
