package study.myproject.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("비밀번호를 잘못 입력하셨습니다.");
    }
}
