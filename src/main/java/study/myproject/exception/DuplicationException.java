package study.myproject.exception;

public class DuplicationException extends RuntimeException {
    private final String value;

    public DuplicationException(String value) {
        super(value + "는 이미 있는 아이디입니다.");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}