package swe.spec.scenarios;

import org.junit.jupiter.api.Test;
import swe.rest.models.CommunityResource;
import swe.rest.models.UserResource;
import swe.spec.Scenario;
import swe.spec.utils.Posting;

public class SubscribeToCommunityTest extends Scenario {
    @Test
    public void subscribe_to_community() throws Exception {
        UserResource amy = createUserUsecase.invokeUsecase("Amy", "amy@company.com", "password");

        CommunityResource community = createCommunityUsecase.invokeUsecase("BirdWatchers", "We watch birds!!", amy.getId(), Posting.buildMySpecialTemplate());

        UserResource fred = createUserUsecase.invokeUsecase("Fred", "fred@company.com", "pa$$word");

        subscribeToCommunityUsecase.invokeUsecase(fred.getId(), community.getId());

        getUserCommunitiesUsecase.checkUserHasAccessToCommunity(fred.getId(), community.getId());
    }
}
