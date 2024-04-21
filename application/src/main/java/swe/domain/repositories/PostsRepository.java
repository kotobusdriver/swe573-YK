package swe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<PostEntity, String> {
    List<PostEntity> findByCommunityId(String communityId);
}
