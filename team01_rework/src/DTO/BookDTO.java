package DTO;

public class BookDTO {
//CREATE TABLE tbl_book(
//		   book_id NUMBER,
//		   book_title varchar2(100),
//		   book_author varchar2(100),
//		   book_is_borrow varchar2(5),
//		   CONSTRAINT pk_book PRIMARY key(book_id)
//		);
//dbms의 값의 변형을 방지하기 위해 사용하기 위해 private 접근제한자를 사용
	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private String bookIsBorrow;
//값을 변경하고 사용하기 위해 get, set를 사용함
	public int getBookId() {
		return bookId; //
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookIsBorrow() {
		return bookIsBorrow;
	}

	public void setBookIsBorrow(String bookIsBorrow) {
		this.bookIsBorrow = bookIsBorrow;
	}
//String 형태로 리턴값 반환 
	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor
				+ ", bookIsBorrow=" + bookIsBorrow + "]";
	}

}
