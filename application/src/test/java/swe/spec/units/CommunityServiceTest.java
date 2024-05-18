package swe.spec.units;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swe.domain.entities.CommunityEntity;
import swe.domain.models.CommunityStatus;
import swe.domain.models.CommunityVisibility;
import swe.domain.repositories.CommunitiesRepository;
import swe.rest.models.CreateCommunityRequest;
import swe.services.CommunitiesService;
import swe.spec.utils.Posting;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class CommunityServiceTest {
    private CommunitiesRepository communitiesRepository = Mockito.mock();
    private CommunitiesService sut;

    @BeforeEach
    public void setup() {
        sut = new CommunitiesService(communitiesRepository);
        Mockito.when(communitiesRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void should_create_community() {
        // given
        CreateCommunityRequest command = buildCommand();

        // when
        CommunityEntity actualEntity = sut.createCommunity(command);

        // then
        Assertions.assertNotNull(actualEntity);
        Assertions.assertEquals("my community", actualEntity.getName());
        Assertions.assertEquals("my description", actualEntity.getDescription());
        Assertions.assertEquals(CommunityVisibility.PUBLIC, actualEntity.getVisibility());
        Assertions.assertEquals(CommunityStatus.ACTIVE, actualEntity.getStatus());

        Mockito.verify(communitiesRepository, times(1)).save(any());
    }

    private CreateCommunityRequest buildCommand() {
        return CreateCommunityRequest.builder()
                .name("my community")
                .description("my description")
                .adminUserId("my-admin-user-id")
                .visibility(CommunityVisibility.PUBLIC)
                .template(Posting.buildMySpecialTemplate())
                .build();
    }
}
