package swe.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String userId) {
    super("User is not found. UserId: %s".formatted(userId));
  }
}
