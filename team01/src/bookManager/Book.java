package bookManager;

<<<<<<< HEAD
public class Book {
	// 필드
	private int bookID;
	private String title;
	private String author;
	private boolean isBorrow;
	private BookList bookList;

	// 생성자
	public Book(int bookID, String title, String author, boolean isBorrow) {
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.isBorrow = isBorrow;
	}
	
	//책을 추가할때 사용할 생성자
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}


	// getter, setter
	public boolean isBorrow() {
		return isBorrow;
	}
	
	public void setBorrow(boolean isBorrow) {
		this.isBorrow = isBorrow;
	}

	public int getBookID() {
		return bookID;
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
=======
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
>>>>>>> 9e0e6dd6955ebdd42274cf7ed7f00b5e6ff793df
	}

	public String getAuthor() {
		return author;
	}

<<<<<<< HEAD
	// 오버라이딩 	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return bookID;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof Book) {
			Book otherbook = (Book)obj;
//			System.out.println(this);
//			System.out.println(otherbook);
//			System.out.print("author: "+this.author.equals(otherbook.author));
//			System.out.println("  title: " +this.title.equals(otherbook.title));
			return (this.author.equals(otherbook.author) && this.title.equals(otherbook.title));
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "[Book] ID: "+bookID+", title: "+title+", author: "+author+", isborrow: "+isBorrow;
	}
	
	public boolean isAvailable(Book book) {
		if(bookList.findBook(book) == -1) {
			return false;
		}else {
			return true;
		}
	}

}
=======
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
>>>>>>> 9e0e6dd6955ebdd42274cf7ed7f00b5e6ff793df
