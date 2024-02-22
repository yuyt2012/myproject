package study.myproject.exception;

public class NotEnoughStockException extends RuntimeException{

    public NotEnoughStockException() {
        super("재고는 0보다 적을 수 없습니다.");
    }
}
