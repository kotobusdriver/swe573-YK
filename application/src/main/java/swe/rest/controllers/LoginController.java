package swe.rest.controllers;

import static swe.rest.controllers.LoginController.BASE_PATH;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swe.domain.entities.UserEntity;
import swe.domain.repositories.UsersRepository;
import swe.rest.models.*;

@CrossOrigin
@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class LoginController {
  public static final String BASE_PATH = "/api/login";

  private final UsersRepository usersRepository;

  @PostMapping
  ResponseEntity<UserResource> login(@RequestBody LoginRequest request) {
    Optional<UserEntity> user =
        usersRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
    if (user.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.ok(UserResource.convert(user.get()));
  }
}
