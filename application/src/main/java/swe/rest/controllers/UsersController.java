package swe.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swe.domain.entities.UserEntity;
import swe.rest.models.CreateUserRequest;
import swe.rest.models.UserResource;
import swe.workflows.CreateUserWorkflow;

import static swe.rest.controllers.UsersController.BASE_PATH;

@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class UsersController {
    public static final String BASE_PATH = "/api/users";

    private final CreateUserWorkflow createUserWorkflow;

    @PostMapping
    ResponseEntity<UserResource> createEntitlement(@RequestBody CreateUserRequest createUserRequest) {
        UserEntity user = createUserWorkflow.execute(createUserRequest);
        return ResponseEntity.ok(UserResource.convert(user));
    }
}
