package userManager;

import java.util.HashMap;
import java.util.Map;

/*
 *  User, LoginManager   User 추상클래스 설계
                  사용자 기본정보
                  LoginManager 클래스 설계
                  (Map에 사용자 저장/조회)
                  사용자 로그인 기능 구현
                  
 * */
public class  User {
	private String name;
	private String phoneNumber;
	private String id;
	private String password;
	
	static Map<String, User> userMap = new HashMap<>();
	
	//전체를 받는 생성자
	public User(String name, String phoneNumber, String id, String password) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.password = password;
	}
	
	//getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// toString과 equals
	// toString을 이용하여 객체를 볼 때 고객의 비밀번호는 * + 뒤 4자리, 전화번호는 뒤 4자리만 공개
	@Override
	public String toString() {
		String markUpPassword = "";
		String markUpPhoneNumber = "";
		for(int i=0; i< password.length()-4; i++) {
			markUpPassword +="*"; 
		}
		markUpPassword += markUpPassword +=  password.substring(password.length()-4,password.length());
		markUpPhoneNumber = phoneNumber.substring(phoneNumber.length()-4, phoneNumber.length());
		return "User [name=" + name + ", phoneNumber=" + phoneNumber + ", id=" + id + ", password=" + markUpPassword + "]";
	}
	//id를 이용하여 검사하는 equals로직 재정의
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof User) {
			String userInfo = ((User)obj).id;
			return this.id.equals(userInfo);
		}
		return false;
	}
	
	//전화번호 -를 제외하고 int타입으로 변환해서 반환하는 메서드, hashCode()
	public int exceptSlash() {
		int intPhoneNum = Integer.parseInt(getPhoneNumber().replaceAll("-", ""));
		return intPhoneNum;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(exceptSlash());
	}
	
	
	

	// 회원 추가
	public static void signUpUser(User user) {
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
	public static void searchUser(User user, String id, String password) {
		if (userMap.containsKey(userMap.containsKey(id))) {
			System.out.println(user.getId() + "아이디의 회원 이름 : " + user.getName());
			System.out.println(user.getId() + "아이디의 회원 전화번호 : " + user.getPhoneNumber());
		} else {
			System.out.println("입력하신 정보의 유저가 없습니다.");
		}
	}

	// 로그인
	public static User login(String id, String password) {
		if (userMap.containsKey(id)) {
			User user = userMap.get(id);
			if (user.getPassword().equals(password)) {
				System.out.println(user.getName() + "회원님 로그인 성공하셨습니다.");
				return user;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		} else {
			System.out.println("존재하지 않는 id 입니다.");
		}		
		return null;
	}
	
}
