package DAO;

public class UserDAO {
//	-- 시퀀스 users, book
//	CREATE SEQUENCE seq_user;
//	CREATE SEQUENCE seq_book;
//	CREATE TABLE tbl_user(
//	   user_number NUMBER,
//	   user_id varchar2(100),
//	   user_password varchar2(100),
//	   user_name varchar2(100),
//	   user_phone_number varchar2(100),
//	   CONSTRAINT pk_user PRIMARY key(user_number)
//	);
	private int userNumber;
	private String userId;
	private String userPasword;
	private String userName;
	private String userPhoneNumber;
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPasword() {
		return userPasword;
	}
	public void setUserPasword(String userPasword) {
		this.userPasword = userPasword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	@Override
	public String toString() {
		return "UserDAO [userNumber=" + userNumber + ", userId=" + userId + ", userPasword=" + userPasword
				+ ", userName=" + userName + ", userPhoneNumber=" + userPhoneNumber + "]";
	}
	 
	
	
	
}
