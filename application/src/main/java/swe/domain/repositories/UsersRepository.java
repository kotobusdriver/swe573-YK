package swe.domain.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
