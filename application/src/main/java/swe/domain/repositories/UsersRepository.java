package swe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, String> {
}
