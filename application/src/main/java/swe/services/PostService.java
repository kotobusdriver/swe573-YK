package swe.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swe.domain.entities.*;
import swe.domain.repositories.CommunitiesRepository;
import swe.domain.repositories.CommunityMembersRepository;
import swe.domain.repositories.PostsRepository;
import swe.exceptions.CommunityNotFoundException;
import swe.exceptions.MemberNotFoundException;
import swe.rest.models.CreatePostRequest;
import swe.rest.models.PostFieldResource;

@Service
@AllArgsConstructor
public class PostService {
  private final CommunitiesRepository communitiesRepository;
  private final CommunityMembersRepository communityMembersRepository;
  private final PostsRepository postsRepository;

  public PostEntity createPost(CreatePostRequest command) {
    Optional<CommunityEntity> foundCommunity =
        communitiesRepository.findById(command.getCommunityId());
    if (foundCommunity.isEmpty()) {
      throw new CommunityNotFoundException(command.getCommunityId());
    }
    Optional<CommunityMemberEntity> foundMember =
        communityMembersRepository.findById(command.getByMemberId());
    if (foundMember.isEmpty()) {
      throw new MemberNotFoundException(command.getByMemberId());
    }
    return createPost(foundCommunity.get(), foundMember.get(), command.getFields());
  }

  public PostEntity createPost(
      CommunityEntity community,
      CommunityMemberEntity member,
      List<PostFieldResource> fieldResources) {
    PostEntity post =
        PostEntity.builder().community(community).member(member).build();
    List<PostFieldEntity> fields = buildPostFields(community, fieldResources, post);
    post.setFields(fields);
    return postsRepository.save(post);
  }

  private List<PostFieldEntity> buildPostFields(
          CommunityEntity community, List<PostFieldResource> fieldResources, PostEntity post) {
    Map<String, FieldDefinitionEntity> map =
        community.getFieldDefinitions().stream()
            .collect(Collectors.toMap(fd -> fd.getId(), Function.identity()));

    return fieldResources.stream().map(fr -> fr.convert(map, post)).toList();
  }
}
