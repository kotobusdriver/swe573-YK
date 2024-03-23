package swe.exceptions;

public class MemberNotFoundException extends RuntimeException {
  public MemberNotFoundException(String memberId) {
    super("Member is not found. MemberId: %s".formatted(memberId));
  }
}
