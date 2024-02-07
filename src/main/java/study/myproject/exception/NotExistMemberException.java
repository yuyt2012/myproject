package study.myproject.exception;

public class NotExistMemberException extends RuntimeException {
    public NotExistMemberException() {
        super("회원이 존재 하지 않습니다.");
    }
}
