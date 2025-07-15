package bookManager;
//  Borrow 인터페이스
//	추상 메소드 2개(대여 -> 예외 던지기, 반납)

import exceptionManager.BookAlreadyException;
import exceptionManager.BookNotAvailableException;
import exceptionManager.MaxBorrowException;
import memberManager.Member;

public interface Borrow {
	//추상메소드 
	//그 책 빌리습니다. 
	public abstract boolean borrow(Member who, Book book)throws BookAlreadyException, MaxBorrowException, BookNotAvailableException;
	/* 매개변수 : 누가 빌려갔는지 (멤버클래스 who) 어떤책 빌려갈건지 (Book book) 
	 * 리턴값 : boolean
	 * -- 예외처리 throws
	 * 존재하지 않은 책에 대해 물어볼때 - BookNotAvailableException 
	 * 누군가가 이미 빌려간 책일때 - BookAlreadyException
	 * 최대 대여 권수를 초과했을 때 - MaxBorrowException
	 */
	
	// 그 책 반납하겠습니다.
	public abstract boolean bookReturn(Member who, Book book)throws BookNotAvailableException;
	/* 매개변수 : 누가 반납하는지 (멤버크래스 who) 어떤책 반납하는지 (북클래스 book)
	 * 리턴 : boolean
	 * 
	 */
}
