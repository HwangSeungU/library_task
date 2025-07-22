package test;

import DAO.UserDAO;
import DTO.UserDTO;

public class Test {
	public static void main(String[] args) {
//		연결 테스트
//		Connection conn = DBConnecter.getConnection();
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		
		userDTO.setUserId("testAccount");
		userDTO.setUserPassword("1234");
		userDTO.setUserName("테스트계정");
		userDTO.setUserPhoneNumber("010-1234-5678");
		
//		userDAO.userSign(userDTO);
//		if(userDAO.userDelete(3)) {
//			System.out.println("회원 삭제");
//		}else {
//			System.out.println("회원 삭제 실패");
//		}
		
		
		
		
	}
	

}
