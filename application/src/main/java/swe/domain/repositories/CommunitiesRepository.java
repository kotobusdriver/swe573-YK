package swe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.CommunityEntity;

public interface CommunitiesRepository extends JpaRepository<CommunityEntity, String> {}
