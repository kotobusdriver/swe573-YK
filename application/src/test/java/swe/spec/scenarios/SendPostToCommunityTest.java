package swe.spec.scenarios;

import org.junit.jupiter.api.Test;
import swe.rest.models.*;
import swe.spec.Scenario;
import swe.spec.utils.Posting;

public class SendPostToCommunityTest extends Scenario {
  @Test
  public void send_post() throws Exception {
    UserResource user = createUserUsecase.invokeUsecase("Amy", "amy@company.com", "password");

    CommunityResource community =
        createCommunityUsecase.invokeUsecase(
            "BirdWatchers", "We watch birds!!", user.getId(), Posting.buildMySpecialTemplate("my-template"));

    CommunityMemberResource member =
        getCommunityMembersUsecase.getUserMembershipForCommunity(user.getId(), community.getId());

    String templateId = community.getTemplateByName("my-template").get().getId();
    sendPostUsecase.invokeUsecase(
        community.getId(), member.getId(), templateId, Posting.createMySpecialPostFields(community, templateId));
  }
}
