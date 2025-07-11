package bookManager;

import java.util.ArrayList;
import java.util.List;

//	- Book 클래스 설계
//	도서와 관련된 기본정보
//	도서 대출 메소드 오버라이딩
//	equals, hashCode 오버라이딩

public class Book<T> extends Memeber {
	// 필드
	private int bookID; //책 id
	private String bookTitle; // 책 제목
	private String author; // 작가
	private boolean isBorrow;// 빌린여부

	// 생성자
	public Book(int bookID, String bookTitle, String author, boolean isBorrow) {
		super();
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.author = author;
		this.isBorrow = isBorrow;
	}
	
	//arraylist 선언
	//책 목록 리스트 (20권 id는 1~20
//	booklist.add(new Book(1,"title","auther",false));
	
	//메소드
	//책 제목으로 리스트에서 책을 찾는 메소드
	// -> 리스트를 반복자(iterator)를 사용하여 훑어본 다음에 
	//책

	/*--------오버라이딩-----------*/
	// hashCode - 구분 대상은 bookID
	@Override 
	public int hashCode() {
		return this.bookID;
	}

	// equals - 책 제목이 동일하다면
	@Override
	public boolean equals(Object obj) {
		if(obj== this) {
			return true;
		}
		if(obj instanceof Book) {
			String other = ((Book)obj).bookTitle;
			if(this.bookTitle.length() == other.length()) {
				for(int i=0;i<bookTitle.length();i++) {
					if(this.bookTitle.charAt(i)!= other.charAt(i)) {
						return false;
					}//책 제목 한글자씩 비교
				}//책 제목만큼 반복 for
			}//책.Title과 동일하다면
			return true;
		}
		return false;
	}
	// 도서 대출 메소드

	// getter, setter
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isBorrow() {
		return isBorrow;
	}

	public void setBorrow(boolean isBorrow) {
		this.isBorrow = isBorrow;
	}
	
}
