package swe.domain.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.CommunityMemberEntity;

public interface CommunityMembersRepository extends JpaRepository<CommunityMemberEntity, String> {
  List<CommunityMemberEntity> findByUserId(String userId);

  List<CommunityMemberEntity> findByCommunityId(String communityId);
}
