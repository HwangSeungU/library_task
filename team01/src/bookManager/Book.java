package bookManager;

//	- Book 클래스 설계
//	도서와 관련된 기본정보
//	도서 대출 메소드 오버라이딩
//	equals, hashCode 오버라이딩

public class Book extends memberManager {
	// 필드
	private String bookTitle; // 책 제목
	private String author; // 작가
	private boolean isBorrow;// 빌린여부

	// 생성자
	public Book(String bookTitle, String author, boolean isBorrow) {
		super();
		this.bookTitle = bookTitle;
		this.author = author;
		this.isBorrow = isBorrow;
	}

	/*--------오버라이딩-----------*/
	// hashCode
	@Override
	public int hashCode() {
		//
		return super.hashCode();
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
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
