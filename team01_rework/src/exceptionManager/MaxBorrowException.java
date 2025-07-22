package exceptionManager;

public class MaxBorrowException extends Exception {

	public MaxBorrowException() {
		super("이미 최대로 빌린 상태입니다.");
	}
	
}
