package study.myproject.exception;

public class WrongIdException extends RuntimeException {

    public WrongIdException() {
        super("아이디를 잘못 입력하셨습니다.");
    }
}
