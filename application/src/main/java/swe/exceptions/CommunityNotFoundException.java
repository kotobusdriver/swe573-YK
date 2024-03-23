package swe.exceptions;

public class CommunityNotFoundException extends RuntimeException {
  public CommunityNotFoundException(String communityId) {
    super("Community is not found. CommunityId: %s".formatted(communityId));
  }
}
