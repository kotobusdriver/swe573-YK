package swe.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swe.domain.entities.UserEntity;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
