package swe.domain.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.CommunityEntity;

public interface CommunitiesRepository extends JpaRepository<CommunityEntity, String> {
  List<CommunityEntity> findByNameContainingIgnoreCase(String searchText);
}
