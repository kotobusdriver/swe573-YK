package swe.workflows;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import swe.domain.entities.UserEntity;
import swe.domain.repositories.UsersRepository;
import swe.rest.models.CreateUserRequest;

@Component
@AllArgsConstructor
public class CreateUserWorkflow implements Workflow<CreateUserRequest, UserEntity> {
    private final UsersRepository usersRepository;

    @Override
    public UserEntity execute(CreateUserRequest command) {
        UserEntity newUser = UserEntity.builder()
                .name(command.getName())
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
        return usersRepository.save(newUser);
    }
}
