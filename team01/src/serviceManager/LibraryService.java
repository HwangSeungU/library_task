package serviceManager;

import java.util.ArrayList;

import bookManager.Book;
import bookManager.BookList;
import exceptionManager.BookAlreadyException;
import exceptionManager.BookNotAvailableException;
import exceptionManager.MaxBorrowException;
import memberManager.Member;

public class LibraryService {

//   서비스 로직 설계자   LibraryService     리스트 인터페이스 객체
//   //★팀과제          					도서 목록 출력 메소드
//                     					도서 검색 메소드 
//                     					도서 대여 메소드(매개변수2개=Member, 도서제목)
//                     					도서 반납 메소드(매개변수2개=Member, 도서제목)
	// 도서 목록 출력 메소드
//	BookList printEntireBooks = new BookList();
//	public void printEntireBooks() {
//		printEntireBooks.printAll();
//	}

//	// 도서 검색 메소드
//	public void searchBooks() {
//		// 전체 도서목록에서 도서제목으로 검색
//		if(getBooklist.equals(getTitle)) {
//			//검색한 책을 보유중일 경우 책제목/저자명/  출력
//			System.out.println(getBooklist .equals(getTitle));
//		}
//		if(!getBooklist.equals(getTitle)) {
//			System.out.println("입력하신 책이 존재하지 않습니다");		
//		}
//	}
//	// 도서 검색 메소드
//	public void searchBooks() {
//		// 전체 도서목록에서 도서제목으로 검색
//		if(getBooklist .equals(getTitle)) {
//			//검색한 책을 보유중일 경우 책제목/저자명/  출력
//			System.out.println(getBooklist .equals(getTitle));
//		}
//		if(!getBooklist .equals(getTitle)) {
//			System.out.println("입력하신 책이 존재하지 않습니다");		
//		}
//	}

	// 도서 대여 메소드((매개변수2개=Member, 도서제목))
	public void borrow(Member member, String getTitle) throws BookNotAvailableException,BookAlreadyException,MaxBorrowException {
			member.borrowBook(BookList.searchTitleBook(getTitle));		
	}

	// 도서 반납 메소드((매개변수2개=Member, 도서제목))
	public void returnBook(Member member, String getTitle) throws BookNotAvailableException{
			member.returnBook(BookList.searchTitleBook(getTitle));
	}

}
