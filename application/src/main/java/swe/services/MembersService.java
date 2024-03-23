package swe.services;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.CommunityMemberEntity;
import swe.domain.entities.UserEntity;
import swe.domain.repositories.CommunitiesRepository;
import swe.domain.repositories.CommunityMembersRepository;
import swe.domain.repositories.UsersRepository;
import swe.exceptions.CommunityNotFoundException;
import swe.exceptions.MemberNotFoundException;
import swe.exceptions.UserNotFoundException;

@Service
@AllArgsConstructor
public class MembersService {
  private final UsersRepository usersRepository;
  private final CommunitiesRepository communitiesRepository;
  private final CommunityMembersRepository communityMembersRepository;

  public CommunityMemberEntity createMember(String communityId, String userId) {
    Optional<UserEntity> foundUser = usersRepository.findById(userId);
    if (foundUser.isEmpty()) {
      throw new UserNotFoundException(userId);
    }
    Optional<CommunityEntity> foundCommunity = communitiesRepository.findById(communityId);
    if (foundCommunity.isEmpty()) {
      throw new CommunityNotFoundException(communityId);
    }

    return createMember(foundUser.get(), foundCommunity.get());
  }

  public CommunityMemberEntity createMember(
      @NonNull UserEntity user, @NonNull CommunityEntity community) {
    CommunityMemberEntity member =
        CommunityMemberEntity.builder().user(user).community(community).admin(false).build();
    return communityMembersRepository.save(member);
  }

  public void makeAdmin(String memberId) {
    Optional<CommunityMemberEntity> foundMember = communityMembersRepository.findById(memberId);
    if (foundMember.isEmpty()) {
      throw new MemberNotFoundException(memberId);
    }
    makeAdmin(foundMember.get());
  }

  public void makeAdmin(CommunityMemberEntity member) {
    member.setAdmin(true);
    communityMembersRepository.save(member);
  }

  public List<CommunityMemberEntity> findByUserId(String userId) {
    return communityMembersRepository.findByUserId(userId);
  }

  public List<CommunityMemberEntity> findByCommunityId(String communityId) {
    return communityMembersRepository.findByCommunityId(communityId);
  }
}
