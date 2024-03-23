package swe.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.UserEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {
  String id;
  String name;

  public static UserResource convert(UserEntity user) {
    return UserResource.builder().id(user.getId()).name(user.getName()).build();
  }
}
