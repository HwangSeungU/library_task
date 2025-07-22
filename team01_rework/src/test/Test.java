package test;

import java.util.List;

import DAO.UserDAO;
import DTO.UserDTO;
//import bookManager.Book;
//import bookManager.BookList;
//import exceptionManager.BookAlreadyException;
//import exceptionManager.BookNotAvailableException;
//import exceptionManager.MaxBorrowException;
//import memberManager.Member;
//import serviceManager.LibraryService;
//import useManager.ConsoleUI;
//import userManager.User;

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
		
//		userDTO.setUserId("testAccount");
//		userDTO.setUserPassword("1234");
//		userDTO.setUserName("테스트계정");
//		userDTO.setUserPhoneNumber("010-1234-5678");
		
//		회원가입
//		userDAO.userSign(userDTO);
		
//		회원탈퇴
//		userDAO.userSign(userDTO);
//		if(userDAO.userDelete(3)) {
//			System.out.println("회원 삭제");
//		}else {
//			System.out.println("회원 삭제 실패");
//		}
		
//		로그인
//		존재하는 계정 => user_number 추출
//		System.out.println(userDAO.loginUser("testAccount", "1234"));
//		존재하지 않는 계정 => -1 반환
//		System.out.println(userDAO.loginUser("hello", "1111"));
		
//		아이디 찾기
//		List<String> findUserIds = userDAO.findId("테스트계", "010-1234-5678");
//		findUserIds.forEach(System.out::println);
//		아이디가 없을경우
//		if(findUserIds.size() == 0) {
//			System.out.println("bb");
//		}
		
//		비밀번호 찾기
//		List<String> findPasswords = userDAO.findPassword("테스트계", "testAccount", "010-1234-5678");
//		if(findPasswords.size() == 0) {
//			System.out.println("찾으시는 비밀번호 없음");
//		}else {
//			findPasswords.forEach(System.out::println);
//		}
		
//		회원정보 수정(user_number을 찾고 이름과 비번만 바꿔준다) true/false 반환
//		System.out.println(userDAO.updateUser("홍홍", "5555", 100));
		
//		회원정보 찾기
//		존재할 경우 userNum 가져오고 없을경우 -1 반환
		System.out.println(userDAO.findUser("testAccount", "1234", "01012345678"));
	}


//		Scanner sc=new Scanner(System.in);
//		Test ts = new Test();
//		ts.login(sc);

}

//		===================sek===============

//	public void login(Scanner sc) {
//
//		while (true) {
//			String setUserId = "";
//			String setUerPassword = "";
//			String setUserPhoneNumber = "";
//			String setUserName = "";
//			Test.menu("로그인", "회원가입", "프로그램 종료");
//			button = sc.nextInt();
//			sc.nextLine();
//			switch (button) {
//			case 1:
//				// 로그인 메소드
//				System.out.println("로그인");
//				System.out.print("ID 입력 : \n");
//				setUserId = sc.nextLine();
//				System.out.print("PASSWORD 입력 : \n");
//				setUerPassword = sc.nextLine();
//				User loginU = User.login(setUserId, setUerPassword); // ----------v
//				if (loginU != null) {
//					consol(sc, loginU);
//				} else {
//					System.out.println("프로그램을 강제로 종료합니다.");
//					return;
//				}
//				break;
//			case 2:
//				// 회원가입 메소드
//				System.out.println("회원가입");
//				System.out.print("이름 : \n");
//				setUserName = sc.nextLine();
//				System.out.print("전화번호 : \n");
//				setUserPhoneNumber = sc.nextLine();
//				System.out.print("아이디 : ");
//				setUserId = sc.nextLine();
//				System.out.print("비밀번호 : ");
//				setUerPassword = sc.nextLine();
//				User addUser = new Member(setUserName, setUserPhoneNumber, setUserId, setUerPassword,
//						new ArrayList<>());// -----------------v
//				User.signUpUser(addUser);// ----------------v
//				break;
//			case 3:
//				// 종료 메소드
//				System.out.println("프로그램을 종료합니다");
//				break;
//			default:
//				System.out.println("제시된 번호 외의 다른 번호를 입력하지 말아주세요.");
//				break;
//			} // switch
//			if (button == 3) {
//				break;
//			}
//		}
//	}
//
//	public void consol(Scanner sc, User addUser) { //----------v
//
//		LibraryService ls = new LibraryService();//---------v
//
//		while (true) {
//			Test.menu("목록 확인", "책 대출하기", "반납 하기", "내 대여 목록 확인", "뒤로가기");
//			Member member = (Member) addUser;//----------v
//			int bookId = 0;
//			BookList bl = new BookList();//---------v
//			Book searchIdBook = null;
//			button = sc.nextInt();
//			sc.nextLine();
//			switch (button) {
//			case 1:
//				// 목록확인 메소드
//				BookList.printAll();//-----------v
//				break;
//			case 2:
//				System.out.print("대출할 책 ID를 입력해주세요 : ");
//				bookId = sc.nextInt();
//				sc.nextLine();
//				searchIdBook = BookList.searchIdBook(bookId);//---------v
////        	 System.out.println(searchIdBook);
////        	 System.out.println(addUser.getName());
//				try {
//					ls.borrow(member, searchIdBook.getTitle());
//				} catch (BookAlreadyException | MaxBorrowException | BookNotAvailableException e) {
//					e.printStackTrace();//---------v
//				}
//				break;
//			case 3:
//				// 반납하는 메소드
//				System.out.println("반납하실 도서의 id를 입력해주세요");
//				bookId = sc.nextInt();
//				sc.nextLine();
//				searchIdBook = BookList.searchIdBook(bookId);//-----------v
//				try {
//					ls.returnBook(member, searchIdBook.setBookTitle());
//				} catch (BookNotAvailableException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case 4:
//				member.checkBook();
//				break;
//			case 5:
//				// 완전 종료
//				System.out.println("뒤로가기");
//				return;
//			default:
//				System.out.println("제시된 번호만 입력해주세요. 제발");
//				break;
//			} // switch
//		} // while
//	}
//
//	static public void menu(String... menuNames) {
//		int count = 1;
//		System.out.println("=================================");
//		for (String menu : menuNames) {
//			System.out.println("=\t\t\t\t=");
//			System.out.println("=\t" + count + ". " + menu);
//			System.out.println("=\t\t\t\t");
//			count++;
//		}
//		System.out.println("=\t\t\t\t");
//
//	}
