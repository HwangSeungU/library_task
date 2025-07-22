package exceptionManager;

public class BookNotAvailableException extends Exception {

	public BookNotAvailableException() {
		super("책은 사용가능 상태가 아닙니다.");
	}
	
}
