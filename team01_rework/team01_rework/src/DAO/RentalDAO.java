package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import DTO.RentalDTO;

public class RentalDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

//책 대여
	// rental 테이블에 누가 어떤 책을 빌렸는지 저장하는 메소드
	// userDTO.userNumber, bookDTO.bookId가 rentalDTO 안에 저장되어있어야함 
	public void rentalUser(RentalDTO rentalDTO) {
		String query = "insert into tbl_rental(user_number , book_id) " + "values(?,?)";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, rentalDTO.getUserNumber());
			preparedStatement.setInt(2, rentalDTO.getBookId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("rentalUser() 중 sql 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//책 반납
	// userDTO.userNumber, bookDTO.bookId가 rentalDTO 안에 저장되어있어야함 
	// boolean return 있음 반납 성공시 true 실패시 false
	public boolean returnUser(RentalDTO rentalDTO) {
		String query = "delete from tbl_rental where user_number = ? and book_id = ? ";
		connection = DBConnecter.getConnection();
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, rentalDTO.getUserNumber());
			preparedStatement.setInt(2, rentalDTO.getBookId());
			count = preparedStatement.executeUpdate();
			System.out.println(count+"=====확인");
		} catch (SQLException e) {
			System.out.println("returnUser() 중 sql 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count>0;
	}

	// 대여한 책 목록
	//내(userDTO.userNumber)가 빌린 책 목록 출력
	//userDTO.userNumber 필요함
	public void bookList(int userNumber) {
		String query = "SELECT book_title,book_id FROM tbl_book WHERE book_id "
				+ "in (SELECT book_id FROM tbl_rental WHERE user_number = ?)";
		connection = DBConnecter.getConnection();
		Map<Integer,String> bookMap = new HashMap<>();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String bookTitle = resultSet.getString("book_title");
				int bookId = resultSet.getInt("book_id");
				bookMap.put(bookId, bookTitle);
			}
			  if(bookMap.isEmpty()) {
		            System.out.println("책을 대여하고 있지 않습니다");
		         }else{
		        		for(Entry<Integer,String> map : bookMap.entrySet()) {
		        			System.out.println("빌린 도서 ID : "+map.getKey() +", 책 이름 : " +map.getValue());
		        		}
		            
		         }
		} catch (SQLException e) {
			System.out.println("bookList() 중 sql 오류 발생");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
