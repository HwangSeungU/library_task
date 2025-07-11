package userManager;

import java.util.HashMap;
import java.util.Map;

public class UserManager_notUse extends User {

	public UserManager_notUse(String name, String phoneNumber, String id, String password) {
		super(name, phoneNumber, id, password);
		// TODO Auto-generated constructor stub
	}

	Map<String, User> userMap = new HashMap<>();

	

	// 회원 추가
	public void signUpUser(Map<String, User> userMap, User user) {
		if (userMap.containsKey(user.getId())) {
			System.out.println("중복된 ID입니다.");
		} else {
			userMap.put(user.getId(), user);
			{
				System.out.println(user.getName() + "님 회원 추가");
			}
		}
	}

	// 조회
	public void searchUser(Map<String, User> userMap, User user, String id, String password) {
		if (userMap.containsKey(userMap.containsKey(id))) {
			System.out.println(user.getId() + "아이디의 회원 이름 : " + user.getName());
			System.out.println(user.getId() + "아이디의 회원 전화번호 : " + user.getPhoneNumber());
		} else {
			System.out.println("입력하신 정보의 유저가 없습니다.");
		}
	}

	// 로그인
	

	@Override
	public void login(String id, String password) {
		if (userMap.containsKey(id)) {
			User u = userMap.get(id);
			if (u.getPassword().equals(password)) {
				System.out.println(u.getName() + "회원님 로그인 성공하셨습니다.");
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		} else {
			System.out.println("존재하지 않는 id 입니다.");
		}		
	}

	

}
