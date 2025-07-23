package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.BookDTO;

// book에 관련된 메소드 생성 
public class BookDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	// 책 목록 출력
	/**
	 * @param 매개변수 없음
	 * @return 반환값 없이 모두 출력
	 * @throws sql 구문 오류, sql 연결 시 사용한 객체 close 오류
	 * @see 호출하면 DB TBL_BOOK 에 있는 모든 값을 출력하고 종료됨
	 */
	public void printBookList() {
		String query = "SELECT BOOK_ID, BOOK_TITLE ,BOOK_AUTHOR FROM TBL_BOOK";
		// 쿼리문의 결과는 다중행이므로 List<String> 을 사용해야한다.
		BookDTO bookDTO = null;
		List<BookDTO> booklist = new ArrayList<>();

		try {
			// DB연결
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			// 값 받기
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) { // 쿼리의 행이 끝날 때까지 반복
				bookDTO = new BookDTO(); // 1회용 bookDTO 객체 만들기

				// 현재 행의 값(resultSet.) 의값을 하나씩 받아서 1회용 객체에 넣음
				bookDTO.setBookId(resultSet.getInt(1));
				bookDTO.setBookTitle(resultSet.getString(2));
				bookDTO.setBookAuthor(resultSet.getString(3));
				booklist.add(bookDTO); // 리스트에 완성된 객체를 넣음
			}

			// 받은 값 출력
			if (booklist.isEmpty()) { // 결과값에 아무것도 없다면
				System.out.println("책 목록이 비어있습니다.");
			} else {
				for (BookDTO book : booklist) {
		               String bookInfo = book.toString().substring(7);
		               System.out.println(bookInfo);
		            }
			}

		} catch (SQLException e) {
			System.out.println("bookDAO.printBookList() sql error");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("BookADO.printBookList() sql닫을때 오류");
				e.printStackTrace();
			}
		} // try-catch-finally end

	}// 책 목록 출력 메소드 printBookList() end

	/***** 책 찾기 메소드 *************************************************************/
	/**
	 * @param 매개변수 String bookTitle, String author
	 * @return 반환값 int bookId
	 * @throws 예외처리 sql 구문 오류, sql 연결 시 사용한 객체 close 오류
	 * @see 책 제목과 작가명을 입력받고 해당하는 책을 찾은 후 그 책의 bookId를 반환해주는 메소드
	 */
	public int findBook(String title, String author) {
		String query = "SELECT BOOK_ID FROM TBL_BOOK " + "WHERE BOOK_TITLE = ? AND BOOK_AUTHOR = ?";
		int foundBookId = -1;

		try {
			// DB연결
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// 쿼리 완성
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			// 값 받기
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) { // 결과 값이 있다면
				foundBookId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("bookDAO.findBook() sql error");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("BookADO.findBook() sql닫을때 오류");
				e.printStackTrace();
			}
		} // try-catch-finally end
		return foundBookId;
	}// public int findBookTitle() end

	/**
	 * @param 매개변수 String bookTitle
	 * @return 반환값 int bookId
	 * @throws 예외처리 sql 구문 오류, sql 연결 시 사용한 객체 close 오류
	 * @return
	 * @see bookTitle를 입력받고 해당하는 책을 찾은 후 그 책의 bookId를 반환해주는 메소드
	 */
	public int findBookTitle(String title,int userNumber) {
		String query = "SELECT BOOK_ID, BOOK_TITLE, BOOK_AUTHOR FROM TBL_BOOK "
				+ "WHERE BOOK_TITLE = ? AND book_id IN (SELECT book_id FROM tbl_rental WHERE user_number = ?)";
		// 쿼리문의 결과는 다중행일 수 있지만 이 메소드에서는 제일 처음에 나온 값만 반환하고 종료한다.
		int foundBookId = -1;

		try {
			// DB연결
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// 쿼리 완성
			preparedStatement.setString(1, title);
			preparedStatement.setInt(2, userNumber);
			// 값 받기
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) { // 결과 값이 있다면
				foundBookId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("bookDAO.findBookTitle() return int sql error");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("BookADO.findBookTitle() return sql닫을때 오류");
				e.printStackTrace();
			}
		} // try-catch-finally end
		return foundBookId;
	}// public int findBookTitle() end

	// book author로 책 찾기
	/**
	 * @param 매개변수 String bookAuthor
	 * @return 반환값 int bookId
	 * @throws 예외처리
	 * @see 작가를 입력받고 해당하는 책을 찾은 후 제일 첫 책의 bookId(int)를 반환하는 메소드
	 */
	public int findBookAuthor(String author) {
		String query = "SELECT BOOK_ID, BOOK_TITLE, BOOK_AUTHOR FROM TBL_BOOK " + "WHERE BOOK_AUTHOR = ?";
		// 이 쿼리문이 다중행 반환일 수 있지만 첫번째 결과값만 반환하고 종료된다.
		int foundBookId = -1;

		try {
			// DB연결
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// 쿼리 완성
			preparedStatement.setString(1, author);
			// 값 받기
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) { // 결과 값이 있다면
				foundBookId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("bookDAO.findBookAuthor() return int sql error");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("BookADO.findBookAuthor() reutrn int sql닫을때 오류");
				e.printStackTrace();
			}
		} // try-catch-finally end
		return foundBookId;

	}// public int findBookAuthor() end

	/********* 책 추가,삭제 *****************************************************************/
	/** 책 추가 메소드
	 * @see 설명 책 제목과 작가를 입력받고 해당하는 책을 도서 목록에 추가하는 메소드 DB에서 시퀀스로 순서대로 id를 할당하기 때문에
	 *      bookId는 따로 받지 않음
	 * @param 매개변수 String title, String author
	 * @return 없음
	 * @throws 예외처리 sql 구문 오류, sql 연결 시 사용한 객체 close 오류
	 */
	public void addBook(String title, String author) {
		String query = "INSERT INTO TBL_BOOK " 
					+ "VALUES(SEQ_BOOK.NEXTVAL,?,?)";// DB에 행을 추가하는 쿼리문
//		String query = "INSERT INTO TBL_BOOK VALUES(SEQ_BOOK.NEXTVAL,?,?)"
//				+ " WHERE ? NOT IN (SELECT t.book_title FROM tbl_book t)"
//				+ "AND ? NOT IN (SELECT a.book_author FROM tbl_book a );";
		int bookinlist = -1;
		bookinlist = findBook(title, author);
		try {
			// DB연결
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// SQL문 완성
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			
			// 두번쨰 query문을 사용할때는 여기부터 주석처리
			// 이미 있는 도서 인지 확인 
			if (bookinlist == -1) {// 만약에 넣으려는 책이 도서 목록에 없다면
				// 완성된 쿼리문 보내기
				preparedStatement.executeUpdate(); // select문이 아니므로 executeUpdate()사용
				System.out.println("책 추가가 완료되었습니다.");
			} else {
				System.out.println("이미 준비된 도서 입니다.");
			}
			// 두번쨰 query문 사용할때 여기부터 살려주기
//			preparedStatement.setString(3, title);
//			preparedStatement.setString(4, author);
//			preparedStatement.executeUpdate(); 
		} catch (SQLException e) {
			System.out.println("bookADO.addBook() sql error");
			e.printStackTrace();
		} 
		finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("BookADO.addBook() sql닫을때 오류");
				e.printStackTrace();
			}
		}
	} //public void addBook(title,author) end

	/** 책 삭제 메소드
	 * @param 매개변수 int bookId
	 * @return 반환값 없음
	 * @throws 예외처리 sql 구문 오류, sql 연결 시 사용한 객체 close 오류
	 * @see 설명 bookID에 해당하는 int값을 받고 해당하는 책을 지우는 시도를 한다.
	 * 			해당하는 id가 없으면 시도는 하지만, 변화는 없을 것이다
	 */
	public void removeBook(int bookId) {
		String query = "DELETE FROM TBL_BOOK WHERE BOOK_ID = ? ";
		int result = -1;

		try {
			// DB연결
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// SQL문 완성
			preparedStatement.setInt(1, bookId);
			// 쿼리문 보내기 -> DB에 해당하는 id가 없으면 실행은 하되, 아무런 변화가 없다. 
			preparedStatement.executeUpdate();
			System.out.println("bookId가 "+ bookId +"인 책을 삭제했습니다.");

		} catch (SQLException e) {
			System.out.println("BookDAO.removeBook() sql error");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("BookADO.removeBook() sql닫을때 오류");
				e.printStackTrace();
			}
		}
	} //publid void removeBook(int bookId) end

	/****** 책 찾는 메소드 return List<> **********************************/

	// book title로 책 찾기
//	/**
//	 * @param 매개변수 String bookTitle
//	 * @return 반환값 List<Integer>
//	 * @throws sql 구문 오류, sql 연결 시 사용한 객체 close 오류
//	 * @return 
//	 * @see bookTitle를 입력받고 해당하는 책을 찾은 후 그 책의 bookId를 반환해주는 메소드
//	 */
//	public List<Integer> findBookTitle2(String title) {
//		String query = "SELECT BOOK_ID, BOOK_TITLE, BOOK_AUTHOR FROM TBL_BOOK WHERE BOOK_TITLE = ?";
//		// 쿼리문의 결과는 다중행이므로 List<String> 을 사용해야한다.
//		BookDTO bookDTO = null;
//		List<BookDTO> booklist = new ArrayList<>();
//		List<Integer> bookIds = new ArrayList<>();
//
//		try {
//			// DB연결
//			connection = DBConnecter.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			//쿼리 완성
//			preparedStatement.setString(1,title);
//			// 값 받기
//			resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) { // 쿼리의 행이 끝날 때까지 반복
//				bookDTO = new BookDTO(); // 1회용 bookDTO 객체 만들기
//
//				// 현재 행의 값(resultSet.) 의값을 하나씩 받아서 1회용 객체에 넣음
//				bookDTO.setBookId(resultSet.getInt(1));
//				bookDTO.setBookTitle(resultSet.getString(2));
//				bookDTO.setBookAuthor(resultSet.getString(3));
//				booklist.add(bookDTO); // 리스트에 완성된 객체를 넣음
//				
//				Integer id = new Integer(resultSet.getInt(1));
//				bookIds.add(id);
//			}
//
//			// 받은 값 출력
//			if (booklist.isEmpty()) {
//				System.out.println("찾는 책이 목록에 없습니다.");
//			} else {
//				System.out.println(title+" 찾은 책 : ");
//				for (BookDTO book : booklist) {
//					System.out.println(book.getBookId());
//				}
//			}
//
//		} catch (SQLException e) {
//			System.out.println("bookDAO.printBookList() sql error");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (resultSet != null)
//					resultSet.close();
//				if (preparedStatement != null)
//					preparedStatement.close();
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) {
//				System.out.println("BookADO.printBookList() sql닫을때 오류");
//				e.printStackTrace();
//			}
//		} // try-catch-finally end
//		return bookIds;
//	}// public List<Integer> findBookTitle() end

	// book author로 책 찾기
//	/**
//	 * @param 매개변수 String author
//	 * @return 반환값 List<Integer>
//	 * @throws sql 구문 오류, sql 연결 시 사용한 객체 close 오류
//	 * @return 
//	 * @see bookauthor를 입력받고 해당하는 책을 찾은 후 그 책의 bookId를 반환해주는 메소드
//	 */
//	public List<Integer> findBookAuthor2(String author) {
//		String query = "SELECT BOOK_ID, BOOK_TITLE, BOOK_AUTHOR FROM TBL_BOOK WHERE BOOK_AUTHOR = ?";
//		// 쿼리문의 결과는 다중행이므로 List<String> 을 사용해야한다.
//		BookDTO bookDTO = null;
//		List<BookDTO> booklist = new ArrayList<>();
//		List<Integer> bookIds = new ArrayList<>();
//
//		try {
//			// DB연결
//			connection = DBConnecter.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			//쿼리 완성
//			preparedStatement.setString(1,author);
//			// 값 받기
//			resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) { // 쿼리의 행이 끝날 때까지 반복
//				bookDTO = new BookDTO(); // 1회용 bookDTO 객체 만들기
//
//				// 현재 행의 값(resultSet.) 의값을 하나씩 받아서 1회용 객체에 넣음
//				bookDTO.setBookId(resultSet.getInt(1));
//				bookDTO.setBookTitle(resultSet.getString(2));
//				bookDTO.setBookAuthor(resultSet.getString(3));
//				booklist.add(bookDTO); // 리스트에 완성된 객체를 넣음
//				
//				Integer id = new Integer(resultSet.getInt(1));
//				bookIds.add(id);
//			}
//
//			// 받은 값 출력
//			if (booklist.isEmpty()) {
//				System.out.println("찾는 책이 목록에 없습니다.");
//			} else {
//				System.out.println(author+" 찾은 책 : ");
//				for (BookDTO book : booklist) {
//					System.out.println(book.getBookId());
//				}
//			}
//
//		} catch (SQLException e) {
//			System.out.println("bookDAO.printBookList() sql error");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (resultSet != null)
//					resultSet.close();
//				if (preparedStatement != null)
//					preparedStatement.close();
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) {
//				System.out.println("BookADO.printBookList() sql닫을때 오류");
//				e.printStackTrace();
//			}
//		} // try-catch-finally end
//		return bookIds;
//	}// public List<Integer> findBookAuthor() end

} // class BookDAO {} end
