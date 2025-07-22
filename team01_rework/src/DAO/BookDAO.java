package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// book에 관련된 메소드 생성 
public class BookDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	// 책 목록 출력
	/**
	 * @param 매개변수 없음
	 * @return 반환값 없이 모두 출력 or 리스트로 반환
	 * @throws sql error, sql 닫을 때 error
	 * @author team01 서진
	 * @see 설명 while(List) 을 사용하여 모든 내용을 반복하여 결과List에 넣고 출력
	 * 		책 목록이 비어있다면 목록에책이없다고 출력
	 */
	public List<BookDTO> printBookList() {
		try {
			//모든 요소를 출력하는 쿼리문
			String query = "SELECT BOOK_ID, BOOK_TITTLE, BOOK_AUTHOR, BOOK_ISBORROW FROM TBL_BOOK"; // 이렇게 써도 되나 의문이 듬
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			result = preparedStatement.executeQuery();
			
			
			
			
			
			
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
		}

	}

	//// 책 찾기 메소드

	// book title로 책 찾기
	/**
	 * @param 매개변수 String bookTitle
	 * @return 반환값 int bookId
	 * @throws 예외처리 
	 * @author team01 서진
	 * @see bookTitle를 입력받고 해당하는 책을 찾은 후 그 책의 bookId를 반환해주는 메소드
	 */

	// book author로 책 찾기
	/**
	 * @param 매개변수 String bookAuthor
	 * @return 반환값 int bookId
	 * @throws 예외처리
	 * @author team01 서진
	 * @see 작가를 입력받고 해당하는 책을 찾은 후 그 책의 bookId를 반환하는 메소드
	 */
	public void findBookAuthor(String author) {
		String query = "";
		
		connection = DBConnecter.getConnection();
		preparedStatement = connection.prepareStatement(query);
		
	}

	// 책 추가
	/**
	 * @param 매개변수 String title, String author
	 * @return bookDTO
	 * @throws 예외처리 
	 * @author team01 서진
	 * @see 설명
	 */
	public void addBook(BookDTO bookDTO) {
		String query = "";//DB에 행을 추가하는 쿼리문
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,bookDTO.getBookId());
			preparedStatement.setString(2,bookDTO.getBookTitle());
			preparedStatement.setString(3,bookDTO.getBookAuthor());
			
			preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				System.out.println("BookADO.addBook() sql닫을때 오류");
				e.printStackTrace();
			}
		}
	}

	// 책 삭제
	/**
	 * @param 매개변수 bookDTO
	 * @return 반환값 void
	 * @throws 예외처리 sql 구문에러, sql 연결 해제 에러
	 * @author team01 서진
	 * @see 설명 
	 */
	public void removeBook(BookDTO bookDTO) {
		String query = "";//DB에 행을 삭제하는 쿼리문
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,bookDTO.getBookId());
			preparedStatement.setString(2,bookDTO.getBookTitle());
			preparedStatement.setString(3,bookDTO.getBookAuthor());
			
			preparedStatement.executeUpdate();
			
			
			
			
			
			
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
	}

}
