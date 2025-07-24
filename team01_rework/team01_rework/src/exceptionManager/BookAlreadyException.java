package exceptionManager;

public class BookAlreadyException extends Exception{

	public BookAlreadyException() {
		super("책은 이미 준비되어있습니다");
	}

}
