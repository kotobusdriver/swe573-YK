package swe.spec.scenarios;

import org.junit.jupiter.api.Test;
import swe.rest.models.CommunityMemberResource;
import swe.rest.models.CommunityResource;
import swe.rest.models.UserResource;
import swe.spec.Scenario;
import swe.spec.utils.Posting;

public class SendPostsWithDifferingTemplatesToCommunityTest extends Scenario {
  @Test
  public void send_post() throws Exception {
    UserResource user = createUserUsecase.invokeUsecase("Amy", "amy@company.com", "password");

    CommunityResource community =
        createCommunityUsecase.invokeUsecase(
            "BirdWatchers", "We watch birds!!", user.getId(), Posting.buildMySpecialMultipleTemplates("my-template-1", "my-template-2"));

    CommunityMemberResource member =
        getCommunityMembersUsecase.getUserMembershipForCommunity(user.getId(), community.getId());

    String template1Id = community.getTemplateByName("my-template-1").get().getId();
    sendPostUsecase.invokeUsecase(
        community.getId(), member.getId(), template1Id, Posting.createMySpecialPostFields(community, template1Id));

    String template2Id = community.getTemplateByName("my-template-2").get().getId();
    sendPostUsecase.invokeUsecase(
            community.getId(), member.getId(), template2Id, Posting.createMySpecialPostFieldsTemplate2(community, template2Id));
  }
}
