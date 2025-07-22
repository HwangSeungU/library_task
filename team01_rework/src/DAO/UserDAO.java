package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.UserDTO;

public class UserDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	//회원 추가
	public void userSign(UserDTO userDTO) {
		String query = "INSERT INTO TBL_USER(USER_NUMBER, USER_ID, USER_PASSWORD, USER_NAME, USER_PHONE_NUMBER) "
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?)";
		connection = DBConnecter.getConnection();
		
		String exceptSlash = userDTO.getUserPhoneNumber().replaceAll("-", "");
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDTO.getUserId());
			preparedStatement.setString(2, userDTO.getUserPassword());
			preparedStatement.setString(3, userDTO.getUserName());
			preparedStatement.setString(4, exceptSlash);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("userSign() 메소드(회원가입) 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("닫힐 때 오류");
				e.printStackTrace();
			}
		}
	}
	
	
	//회원 삭제
	public boolean userDelete(int userNumber) {
		String query = "DELETE TBL_USER WHERE USER_NUMBER = ?";
		int result = 0;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("userDelelte() 쿼리문 오류");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("닫힐 때 오류");
				e.printStackTrace();
			}
		}
		return result>0;
	}
	
	//로그인(작업중)
	public int loginUser(String userId, String userPassword){
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PASSWORD= ? ";
		connection = DBConnecter.getConnection();
		return 0;
	};
	
	
}


