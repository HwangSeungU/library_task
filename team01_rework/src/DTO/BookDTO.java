package DTO;

public class BookDTO {
//CREATE TABLE tbl_book(
//		   book_id NUMBER,
//		   book_title varchar2(100),
//		   book_author varchar2(100),
//		   book_is_borrow varchar2(5),
//		   CONSTRAINT pk_book PRIMARY key(book_id)
//		);

	private int bookId;
	private String bookTitle;
	private String bookAuthor;

	public int getBookId() {
		return bookId;
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


	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor
				+ "]";
	}

}
