package swe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.CommunityPostTemplateEntity;

public interface CommunityPostTemplatesRepository
    extends JpaRepository<CommunityPostTemplateEntity, String> {}
