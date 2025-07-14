package memberManager;
import java.util.ArrayList;

import bookManager.Book;
import bookManager.BookList;
import exceptionManager.BookAlreadyException;
import exceptionManager.BookNotAvailableException;
import exceptionManager.MaxBorrowException;
import userManager.User;

public class Member extends User{//User로 바꿔서 해야함
	//booklist 메소드를 사용하기 위한 객체 선언
	private BookList bookList;
	
	// List 필드 - 책 저장 리스트
	ArrayList<Book> borrowedBooks = new ArrayList<>();
	private final int MAX_BORROW = 20;

	// 생성자 
	public Member(String name, String phoneNumber, String id, String password, ArrayList<Book> borrowedBooks) {
		super(name, phoneNumber, id, password);
		this.borrowedBooks = borrowedBooks;
	}

	// 로그인 메소드 오버라이딩
	//UserManager 메소드 전부 User로 이동시켜서 User에서 상속받아서 login 메소드 이용할 수 있게 적용
	@Override
	public void login(String id, String password) {
		super.login(id, password);
		System.out.println("로그인 성공");
	}
	// 도서 대출 메소드/
		//borrowBook 
	public void borrowBook(Book book) 
			//  이미 책을 빌렸는지 최대로 빌린 상태인지 책이 사용상태인지
			throws BookAlreadyException, MaxBorrowException, BookNotAvailableException  {
		if(borrowedBooks.contains(book)) {//대출이 되는 책인지 확인
			throw new BookAlreadyException();
			}
		if(borrowedBooks.size() >= MAX_BORROW) {//최대 대출보다 작은지 확인
			throw new MaxBorrowException();
			}
		if(!book.isAvailable(book)) {//책이 사용가능 상태인지 확인
			throw new BookNotAvailableException();
		}
		borrowedBooks.add(book); //대출목록에 책 추가
		bookList.borrow(book);
		System.out.println("대출 성공. 대출한 책 제목: " + book.getTitle());
	}	

	// 도서 반남 메소드
	//returnBook 책을 빌렸었는지 먼저 하기
	public void returnBook(Book book) throws BookNotAvailableException {
		if(!borrowedBooks.contains(book)) {
			throw new BookNotAvailableException();
			}
		borrowedBooks.remove(book);
		bookList.returnBook(book);
		System.out.println("반납 성공. 반납한 책 제목: " + book.getTitle());
	}
	// 대출 중인 책 메소드
		//checkBooks 대출중인 책이 있는지 보기
	public void checkBooks() { 
		if(borrowedBooks.isEmpty()) {
			System.out.println("대출 중이 아닙니다");
		} else {
			System.out.println("대출 목록");
			for(Book book : borrowedBooks) {
				System.out.println(book.getTitle());
			}
		}
	}
}