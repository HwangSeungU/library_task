package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.DBConnecter;

public class UserDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	// 회원 추가
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
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("닫힐 때 오류");
				e.printStackTrace();
			}
		}
	}

	// 회원 삭제
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
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("닫힐 때 오류");
				e.printStackTrace();
			}
		}
		return result > 0;
	}

	// 로그인
	public int loginUser(String userId, String userPassword) {
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PASSWORD= ? ";
		int userNumber = -1;

		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPassword);

			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				userNumber = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loginUser() 쿼리문 오류");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return userNumber;

	};

	// 아이디 찾기
	public List<String> findId(String userName, String userPhoneNumber) {
		String query = "SELECT USER_ID FROM " + "TBL_USER WHERE USER_NAME = ? AND USER_PHONE_NUMBER = ?";
		List<String> userIds = new ArrayList<>();

		String exceptSlash = userPhoneNumber.replaceAll("-", "");

		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, exceptSlash);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String userId = resultSet.getString("USER_ID");
				userIds.add(userId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("findId() 쿼리문 오류");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userIds;
	}

	// 비밀번호 찾기
	public List<String> findPassword(String userName, String userId, String userPhoneNumber) {
		String query = "SELECT USER_PASSWORD FROM "
				+ "TBL_USER WHERE USER_NAME = ? AND USER_ID = ? AND USER_PHONE_NUMBER = ?";
		List<String> userPws = new ArrayList<>();

		String exceptSlash = userPhoneNumber.replaceAll("-", "");

		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userId);
			preparedStatement.setString(3, exceptSlash);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String userPassword = resultSet.getString("USER_PASSWORD");
				userPws.add(userPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("findPassword() 쿼리문 오류");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return userPws;
	}

	// 회원정보 수정
	public boolean updateUser(String userName, String userPassword, int userNumber) {
		String query = "UPDATE tbl_user SET USER_NAME = ? , USER_PASSWORD = ? WHERE user_number = ? ";
		connection = DBConnecter.getConnection();
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPassword);
			preparedStatement.setInt(3, userNumber);

	        result = preparedStatement.executeUpdate(); // 업데이트된 행 수
	       
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("updateUser() 쿼리문 오류");
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
				e.printStackTrace();
			}
		}
		return result > 0;
	}

	// 회원 정보 찾기
	public int findUser(String userId, String userPassword, String userPhoneNumber) {
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE (USER_ID = ? AND USER_PASSWORD = ? ) AND USER_PHONE_NUMBER = ?";
		String exceptSlash = userPhoneNumber.replaceAll("-", "");
		
		int result;
		result= -1;
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPassword);
			preparedStatement.setString(3, exceptSlash);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
