package bookManager;

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
	}

	public String getAuthor() {
		return author;
	}

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