package bookManager;

public class test {

	public static void main(String[] args) {
		BookList booklist1 = new BookList();
		BookList booklist2;
//		booklist2 = BookList.getBooklist();
		
		Book book1 = booklist1.searchTitleBook("살아있는 자들을 위한 죽음 수업");
		System.out.println(book1);

	}

}
