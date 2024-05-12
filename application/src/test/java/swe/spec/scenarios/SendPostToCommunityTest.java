package swe.spec.scenarios;

import org.junit.jupiter.api.Test;
import swe.rest.models.*;
import swe.spec.Scenario;
import swe.spec.utils.Posting;

public class SendPostToCommunityTest extends Scenario {
    @Test
    public void create_community() throws Exception {
        UserResource user = createUserUsecase.invokeUsecase("Amy", "amy@company.com", "password");

        CommunityResource community = createCommunityUsecase.invokeUsecase("BirdWatchers", "We watch birds!!", user.getId(), Posting.buildMySpecialTemplate());

        CommunityMemberResource member = getCommunityMembersUsecase.getUserMembershipForCommunity(user.getId(), community.getId());

        sendPostUsecase.invokeUsecase(community.getId(), member.getId(), Posting.createMySpecialPostFields(community));
    }
}
