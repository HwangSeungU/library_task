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
	private BookList bookList = new BookList();
	
	// List 필드 - 책 저장 리스트
	
	ArrayList<Book> borrowedBooks = new ArrayList<>();
	private final int MAX = 20;

	// 생성자 
	public Member(String name, String phoneNumber, String id, String password, ArrayList<Book> borrowedBooks) {
		super(name, phoneNumber, id, password);
		this.borrowedBooks = new ArrayList<>();
	}

	
	@Override
	public String toString() {
		return "대출한 책: " + borrowedBooks + ", 최대로 빌릴 수 있는 책 수: " + MAX + "]";
	}


	// 로그인 메소드 오버라이딩
	//UserManager 메소드 전부 User로 이동시켜서 User에서 상속받아서 login 메소드 이용할 수 있게 적용
	//static 메소드로 login을 메소드 변경하여서 오버라이딩 필요 X
//	@Override
//	public void login(String id, String password) {
//		super.login(id, password);
//		System.out.println("로그인 성공");
//	}

	
	// 도서 대출 메소드
	//borrowBook 
	//이미 책을 빌렸는지 최대로 빌린 상태인지 책이 사용상태인지
	//대출이 되는 책인지 확인하기
	//최대 대출보다 작은지 확인 max로 상수 설정 후에 사용
	//책이 사용가능 상태인지 확인 true false로 book에서 해주시면 감사할게요!!
	//대출목록에 책 추가하기
	//예외처리 

	public void borrowBook(Book book) 
			throws BookAlreadyException, MaxBorrowException, BookNotAvailableException  {
//		System.out.println("메소드 들어옴1");
		if(borrowedBooks.contains(book)) {
//			System.out.println("메소드 들어옴2");
			throw new BookAlreadyException();
			}
		if(borrowedBooks.size() >= MAX) {
//			System.out.println("메소드 들어옴3");
			throw new MaxBorrowException();
			}
//		System.out.println("메소드 들어옴4");

		
//		if(!book.isAvailable(book)) {//책이 사용가능 상태인지 확인
//			System.out.println("메소드 들어옴5");
//			throw new BookNotAvailableException();
//		}

		
//		System.out.println("메소드 들어옴6");
		System.out.println(book);
		borrowedBooks.add(book); //대출목록에 책 추가
		bookList.borrow(book);
		System.out.println("대출 성공. 대출한 책 제목: " + book.getTitle());
	}	

	// 도서 반남 메소드
	//returnBook 책을 빌렸었는지 먼저 하기
	//예외처리
	public void returnBook(Book book) 
			throws BookNotAvailableException {
		if(!borrowedBooks.contains(book)) {
			throw new BookNotAvailableException();
			}
		borrowedBooks.remove(book);
		bookList.returnBook(book);
		System.out.println("반납 성공. 반납한 책 제목: " + book.getTitle());
	}
	
	// 대출 중인 책 메소드
	//checkBooks 대출중인 책이 있는지 보기
	//book 클래스에 Title getter로 해주시면 그대로 사용할게요
	public ArrayList<Book> checkBooks() { 
		if(borrowedBooks.isEmpty()) {
			return null;
		} 
		else {
			System.out.println(borrowedBooks);
			return borrowedBooks;
		}
	}
	public void checkBook() {
		if(borrowedBooks.isEmpty()) {
			System.out.println("대출 중이 아닙니다");
		}
		else {
			System.out.println("대출 목록");
			for(Book book : borrowedBooks) {
				System.out.println(book.getTitle());
			}
		}
	}
}