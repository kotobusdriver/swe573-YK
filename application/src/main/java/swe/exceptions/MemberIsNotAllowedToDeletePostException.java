package swe.exceptions;

public class MemberIsNotAllowedToDeletePostException extends RuntimeException {
    public MemberIsNotAllowedToDeletePostException(String memberId, String postId) {
        super("Member is not allowed to delete the post. MemberId: %s, PostId: %s".formatted(memberId, postId));
    }
}
