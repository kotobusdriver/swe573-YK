package swe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.PostEntity;

public interface PostsRepository extends JpaRepository<PostEntity, String> {}
