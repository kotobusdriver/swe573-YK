package swe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.CommunityEntity;

import java.util.List;

public interface CommunitiesRepository extends JpaRepository<CommunityEntity, String> {
    List<CommunityEntity> findByNameContainingIgnoreCase(String searchText);
}
