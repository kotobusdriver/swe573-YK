package swe.spec.scenarios;

import org.junit.jupiter.api.Test;
import swe.rest.models.*;
import swe.spec.Scenario;
import swe.spec.utils.Posting;

public class CreateCommunityTest extends Scenario {
  @Test
  public void create_community() throws Exception {
    UserResource user = createUserUsecase.invokeUsecase("Amy", "amy@company.com", "password");

    CommunityResource community =
        createCommunityUsecase.invokeUsecase(
            "BirdWatchers", "We watch birds!!", user.getId(), Posting.buildMySpecialTemplate("my-template"));

    getUserCommunitiesUsecase.checkUserHasAccessToCommunity(user.getId(), community.getId());

    getCommunityMembersUsecase.checkUserIsCommunityAdmin(user.getId(), community.getId());
  }
}
