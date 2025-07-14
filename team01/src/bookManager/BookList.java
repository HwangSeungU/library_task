package bookManager;

import java.util.ArrayList;
import java.util.List;

public class BookList {
	public static List<Book> booklist ;
//	Iterator<Book> iter = booklist.iterator();

	// 생성자
	public BookList() {
		booklist = new ArrayList<>();
		// 기본 책 15권 생성
		booklist.add(new Book(1, "코스모스", "칼 세이건", false));
		booklist.add(new Book(2, "데미안", "헤르만 헤세", false));
		booklist.add(new Book(3, "돈의 심리학", "모건 하루절", false));
		booklist.add(new Book(4, "넛지", "리차드탈러", false));
		booklist.add(new Book(5, "부자들의 개인 도서관", "이상건", false));
		booklist.add(new Book(6, "장미와 나이프", "히가시노 게이고", false));
		booklist.add(new Book(7, "챗GPT 글쓰기", "정태일", false));
		booklist.add(new Book(8, "아주 작은 습관의 힘", "제임스 클리어", false));
		booklist.add(new Book(9, "월간채소", "송지현", false));
		booklist.add(new Book(10, "윤슬의 바다", "백은별", false));
		booklist.add(new Book(11, "마법천자문", "스튜디오시리얼", false));
		booklist.add(new Book(12, "청춘의 독서", "유시민", false));
		booklist.add(new Book(13, "해커스 토익 기출문제집", "해커스", false));
		booklist.add(new Book(14, "초역부처의 말", "코이케 류노스케", false));
		booklist.add(new Book(15, "살아있는 자들을 위한 죽음 수업", "이호", false));

	}

	// 리스트에 책 추가
	public void addBook(Book book) {
		if (booklist.contains(book)) {// 대출이 되는 책인지 확인
			System.out.println("책은 이미 준비되어있습니다");
		}
		book.setBookID(booklist.size() + 1);
		book.setBorrow(false);
		booklist.add(book);
		System.out.println("다음 책이 추가되었습니다. ");
		System.out.println(book);
	}

	// 리스트에서 책 삭제
	public void removeBook(int bookID) {
		booklist.remove(bookID);
	}

	// 모든 책을 출력
	public void printAll() {
		for (Book b : booklist) {
			System.out.println(b);
		}
		System.out.println();
	}

	// getter -> 다른 클래스에서 Booklist 사용하기 위해서 리스트를 불러갈떄 사용
	public static List<Book> getBooklist() {
		return booklist;
	}
	
	public int findBook(Book book) {
		for(int i=0;i<booklist.size();i++) {
//			System.out.println(i);
//		System.out.println(booklist.get(i));
//		System.out.println(book);
			if(booklist.get(i).equals(book)) { 
				return book.getBookID();
			}
		}
//		throws new BookNotAvailableException();
		return -1;
	}
	
	//책 대출 메소드
	public void borrow(Book book) {
		int findBookIdx = findBook(book);
		if(findBookIdx == -1) System.out.println("책을 찾을 수 없습니다.");
		else {
			book.setBorrow(true);
			booklist.add(findBookIdx-1, book);
		}
	}
	
	//책 반납 메소드
	public void returnBook(Book book) {
		int findBookIdx = findBook(book);
		if(findBookIdx == -1) System.out.println("책을 찾을 수 없습니다.");
		else {
			book.setBorrow(false);
			booklist.add(findBookIdx-1, book);
		}
	}
	
	// 메인 작업 중 추가
	//책 id를 통해 책을 찾는 메서드
	public Book searchIdBook(int id) {
		for(int i=0; i<booklist.size(); i++) {
			Book bookList = booklist.get(i);
			if(bookList.getBookID() == id) {
				return bookList;
			}
		}
		return null;
	}
}
