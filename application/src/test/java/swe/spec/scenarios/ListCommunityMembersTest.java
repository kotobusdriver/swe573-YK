package swe.spec.scenarios;

import org.junit.jupiter.api.Test;
import swe.rest.models.CommunityResource;
import swe.rest.models.UserResource;
import swe.spec.Scenario;
import swe.spec.utils.Posting;

public class ListCommunityMembersTest extends Scenario {
    @Test
    public void list_community_members() throws Exception {
        UserResource amy = createUserUsecase.invokeUsecase("Amy", "amy@company.com", "password");

        CommunityResource community = createCommunityUsecase.invokeUsecase("BirdWatchers", "We watch birds!!", amy.getId(), Posting.buildMySpecialTemplate());

        UserResource fred = createUserUsecase.invokeUsecase("Fred", "fred@company.com", "pa$$word");

        subscribeToCommunityUsecase.invokeUsecase(fred.getId(), community.getId());

        getCommunityMembersUsecase.getUserMembershipForCommunity(amy.getId(), community.getId());
        getCommunityMembersUsecase.getUserMembershipForCommunity(fred.getId(), community.getId());
    }
}
