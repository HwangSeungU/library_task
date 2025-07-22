package main;

import java.util.Scanner;

import DAO.BookDAO;
import DAO.RentalDAO;
import DAO.UserDAO;
import DTO.BookDTO;
import DTO.RentalDTO;
import DTO.UserDTO;
import exceptionManager.BookNotAvailableException;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main mn = new Main();
		mn.login(sc);
	}

	private int button = 0;
	public void login(Scanner sc) {
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		while (true) {
			String id = "";
			String pw = "";
			String phoneNumber = "";
			String name = "";
			Main.menu("로그인", "회원가입", "프로그램 종료");
			button = sc.nextInt();
			sc.nextLine();
			switch (button) {
			case 1:
				// 로그인 메소드
				System.out.println("로그인");
				System.out.print("ID 입력 : \n");
				id = sc.nextLine();
				System.out.print("PASSWORD 입력 : \n");
				pw = sc.nextLine();
				int result = userDAO.loginUser(id, pw);
				if (result >0) {
					consol(sc, result);
				} else {
					System.out.println("로그인 실패!!");
					System.out.println("프로그램을 강제로 종료합니다.");
					return;
				}
				break;
			case 2:
				// 회원가입 메소드
				System.out.println("회원가입");
				System.out.print("이름 : \n");
				name = sc.nextLine();
				System.out.print("전화번호 : \n");
				phoneNumber = sc.nextLine();
				System.out.print("아이디 : ");
				id = sc.nextLine();
				System.out.print("비밀번호 : ");
				pw = sc.nextLine();
				
				userDTO.setUserId(id);
				userDTO.setUserPassword(pw);
				userDTO.setUserName(name);
				userDTO.setUserPhoneNumber(phoneNumber);
				userDAO.userSign(userDTO);
				if(userDTO!=null) {
					System.out.println("회원가입 성공");
				}else {
					System.out.println("회원가입 실패");
				}
				break;
			case 3:
				// 종료 메소드
				System.out.println("프로그램을 종료합니다");
				break;
			default:
				System.out.println("제시된 번호 외의 다른 번호를 입력하지 말아주세요.");
				break;
			} // switch
			if (button == 3) {
				break;
			}
		}
	}

	public void consol(Scanner sc, int userNumber) {
		
		RentalDTO rentalDTO = new RentalDTO();
		RentalDAO rentalDAO = new RentalDAO();
		rentalDTO.setUserNumber(userNumber);

		while (true) {
			Main.menu("목록 확인", "책 대출하기", "반납 하기", "내 대여 목록 확인", "뒤로가기");
			String bookName = "";
			String bookAuthor = "";
			BookDTO bookDTO = new BookDTO();
	    	BookDAO bookDAO = new BookDAO();
			button = sc.nextInt();
			sc.nextLine();
			switch (button) {
			case 1:
				// 목록확인 메소드
				bookDAO.printBookList();
				break;
			case 2:
				System.out.print("대출할 책 번호를 입력해주세요 : ");
				String input = "";
				bookName = sc.nextLine();
				System.out.print("대출할 책 작가명을 입력해주세요 : ");
				bookAuthor = sc.nextLine();
				int result = 0;
				result = bookDAO.findBook(bookName, bookAuthor);
				if(result >0) {
					System.out.println("대여한 책 번호 : " + result);
					rentalDTO.setBookId(result);
					rentalDAO.rentalUser(rentalDTO);
					System.out.println("책 대여를 성공하였습니다.");
				}else {
					System.out.println("대여를 실패하였습니다.");
				}
				break;
			case 3:
				// 반납하는 메소드
				System.out.println("반납하실 도서의 id를 입력해주세요");
				int bookId= 0;
				bookId = sc.nextInt();
				sc.nextLine();
				rentalDTO.setBookId(bookId);
				if(rentalDAO.returnUser(rentalDTO)) {
					System.out.println("책 반납 성공");
				}else {
					System.out.println("책 반납 실패");
				}
				break;
			case 4:
				rentalDAO.bookList(userNumber);
				break;
			case 5:
				System.out.println("뒤로가기(로그아웃)");
				
				return;
			default:
				System.out.println("제시된 번호만 입력해주세요. 제발");
				break;
			} // switch
		} // while
	}

	static public void menu(String... menuNames) {
		int count = 1;
		System.out.println("=================================");
		for (String menu : menuNames) {
			System.out.println("=\t\t\t\t=");
			System.out.println("=\t" + count + ". " + menu);
			System.out.println("=\t\t\t\t");
			count++;
		}
		System.out.println("=\t\t\t\t");
		System.out.println("=================================");
	}
}
