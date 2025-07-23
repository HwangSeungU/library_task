package Book;

public class BookDTO {

	private int bookId;
	private String bookTitle;
	private String bookAuthor;

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

//String 형태로 리턴값 반환 

	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor
				+ "]";
	}

}
