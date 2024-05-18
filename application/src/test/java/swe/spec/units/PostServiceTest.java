package swe.spec.units;

import org.h2.engine.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.CommunityMemberEntity;
import swe.domain.entities.PostEntity;
import swe.domain.entities.UserEntity;
import swe.domain.models.CommunityStatus;
import swe.domain.models.CommunityVisibility;
import swe.domain.repositories.CommunitiesRepository;
import swe.domain.repositories.CommunityMembersRepository;
import swe.domain.repositories.PostsRepository;
import swe.rest.models.CreateCommunityRequest;
import swe.rest.models.CreatePostRequest;
import swe.services.CommunitiesService;
import swe.services.PostService;
import swe.spec.utils.Posting;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class PostServiceTest {
    private CommunitiesRepository communitiesRepository = Mockito.mock();
    private CommunityMembersRepository communityMembersRepository = Mockito.mock();
    private PostsRepository postsRepository = Mockito.mock();
    private PostService sut;

    @BeforeEach
    public void setup() {
        sut = new PostService(communitiesRepository, communityMembersRepository, postsRepository);

        Mockito.when(communitiesRepository.findById(any())).thenReturn(buildTestCommunity());
        Mockito.when(communityMembersRepository.findById(any())).thenReturn(buildTestCommunityMember());
        Mockito.when(postsRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void should_create_post() {
        // given
        CreatePostRequest command = buildCommand();

        // when
        PostEntity actualPost = sut.createPost(command);

        // then
        Assertions.assertNotNull(actualPost);
        Assertions.assertEquals(buildTestCommunity().get(), actualPost.getCommunity());
        Assertions.assertEquals(buildTestCommunityMember().get(), actualPost.getMember());
    }

    private CreatePostRequest buildCommand() {
        return CreatePostRequest.builder()
                .communityId("my community id")
                .byMemberId("member id")
                .fields(Collections.emptyList())
                .build();
    }

    private Optional<CommunityEntity> buildTestCommunity() {
        return Optional.of(CommunityEntity.builder()
                .id("my community id")
                .name("Bird watchers community")
                .description("We watch birds!!")
                .status(CommunityStatus.ACTIVE)
                .visibility(CommunityVisibility.PUBLIC)
                .fieldDefinitions(Collections.emptyList())
                .build());
    }

    private Optional<CommunityMemberEntity> buildTestCommunityMember() {
        return Optional.of(CommunityMemberEntity.builder()
                .id("member id")
                .isAdmin(false)
                .community(buildTestCommunity().get())
                .user(buildTestUser().get())
                .build());
    }

    private Optional<UserEntity> buildTestUser() {
        return Optional.of(UserEntity.builder()
                .id("user id")
                .email("user@company.com")
                .password("pa$$word")
                .build());
    }
}
