package main;

import java.util.List;
import java.util.Scanner;

import Book.BookDAO;
import Book.BookDTO;
import Rental.RentalDAO;
import Rental.RentalDTO;
import user.UserDAO;
import user.UserDTO;

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
			Main.menu("로그인", "회원가입", "아이디 찾기", "비밀번호 찾기", "프로그램 종료");
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
				if (result > 0 && result != 1) {
					consol(sc, result);
				} else if (result == 1) {
					adminConsol(sc, result);
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
				if (userDTO != null) {
					System.out.println("회원가입 성공");
				} else {
					System.out.println("회원가입 실패");
				}
				break;

			case 3:
				System.out.println("이름과 전화번호를 입력해주세요");
				System.out.print("이름 : ");
				name = sc.nextLine();
				System.out.print("전화번호 : ");
				phoneNumber = sc.nextLine();
				List<String> findUserIds = userDAO.findId(name, phoneNumber);
				if (findUserIds.size() == 0) {
					System.out.println("존재하지 않는 회원입니당");
				} else {
					System.out.println("아이디는 " + findUserIds.get(0) + "입니다");
				}
				break;
			case 4:
				System.out.println("찾으실 때는 이름, 아이디, 전화번호가 필요합니다.");
				System.out.print("아이디 : ");
				id = sc.nextLine();
				System.out.print("이름 : ");
				name = sc.nextLine();
				System.out.print("전화번호 : ");
				phoneNumber = sc.nextLine();
				List<String> findPasswords = userDAO.findPassword(name, id, phoneNumber);
				if (findPasswords.size() == 0) {
					System.out.println("어딘가 잘못되어서 비번을 찾을 수 없습니다.");
				} else {
					System.out.println("비밀번호는 " + findPasswords.get(0) + "입니다");
				}
				break;
			case 5:
				// 종료 메소드
				System.out.println("프로그램을 종료합니다");
				break;
			default:
				System.out.println("제시된 번호 외의 다른 번호를 입력하지 말아주세요.");
				break;
			} // switch
			if (button == 5) {
				break;
			}
		}
	}

	public void adminConsol(Scanner sc, int userNumber) {
		System.out.println("관리자로 로그인합니다.");
		while (true) {
			Main.menu("책 리스트 확인", "책 추가", "책 삭제", "회원정보 수정", "유저 삭제", "종료");
			BookDTO bookDTO = new BookDTO();
			BookDAO bookDAO = new BookDAO();
			UserDTO userDTO = new UserDTO();
			UserDAO userDAO = new UserDAO();
			button = sc.nextInt();
			sc.nextLine();
			switch (button) {
			case 1:
				// 목록확인 메소드
				bookDAO.printBookList();
				break;
			case 2:
				System.out.println("책 제목 입력해주세요");
				String title = sc.nextLine();
				System.out.println("책 작가 입력해주세요");
				String author = sc.nextLine();
				bookDAO.addBook(title, author);
				break;
			case 3:
				bookDAO.printBookList();
				System.out.println("삭제할 책 id를 입력해주세요");
				int input = 0;
				input = Integer.parseInt(sc.nextLine());
				if(bookDAO.removeBook(input)) {
					System.out.println(input + " id인 책을 목록에서 삭제하였습니다.");
				}else {
					System.out.println("현재 책 리스트에 존재하는 id가 아닙니다.");
				}

				break;
			case 4:
				String name = "";
				String pw = "";
				int userNum = 0;
				System.out.println("회원의 이름과 비밀번호를 수정합니다.");
				System.out.println("수정할 회원의 회원번호를 입력해주세요");
				userNum = Integer.parseInt(sc.nextLine());
				System.out.println("수정할 회원의 이름과 비밀번호를 입력해주세요");
				System.out.print("이름 : ");
				name = sc.nextLine();
				System.out.println();
				System.out.print("비밀번호");
				pw = sc.nextLine();
				if (userDAO.updateUser(name, pw, userNum)) {
					System.out.println("수정 완료");
				}
				break;
			case 5:
				int num = 0;
				System.out.print("삭제할 회원의 회원번호를 입력해주세요 : ");
				num = sc.nextInt();
				sc.nextLine();
				System.out.println();
				if(userDAO.userDelete(num)) {
					System.out.println("회원 삭제 완료");
				}else {
					System.out.println("존재 하지 않는 회원입니다.");
				}
				break;
			case 6:
				System.out.println("종료");
				return;
			default:
				System.out.println("제시된 번호 외의 다른 번호를 입력하지 말아주세요.");
				break;
			}
		}

	}

	

	public void consol(Scanner sc, int userNumber) {

		RentalDTO rentalDTO = new RentalDTO();
		RentalDAO rentalDAO = new RentalDAO();
		rentalDTO.setUserNumber(userNumber);

		while (true) {
			Main.menu("목록 확인", "책 대출하기", "반납 하기", "내 대여 목록 확인", "종료");
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
				System.out.print("대출할 책 제목을 입력해주세요 : ");
				bookName = sc.nextLine();
				int result = 0;
				result = bookDAO.findBookTitle(bookName);
				if (result > 0) {
					System.out.println("대여한 책 번호 : " + result);
					rentalDTO.setBookId(result);
					rentalDAO.rentalUser(rentalDTO);
					System.out.println("책 대여를 성공하였습니다.");
				} else {
					System.out.println("대여를 실패하였습니다.");
				}
				break;
			case 3:
				// 반납하는 메소드
				rentalDAO.bookList(userNumber);
				System.out.println("반납하실 도서의 id를 입력해주세요");
				int bookId = 0;
				bookId = sc.nextInt();
				sc.nextLine();
				rentalDTO.setBookId(bookId);
				if (rentalDAO.returnUser(rentalDTO)) {
					System.out.println("책 반납 성공");
				} else {
					System.out.println("책 반납 실패");
				}
				break;
			case 4:
				rentalDAO.bookList(userNumber);
				break;
			case 5:
				System.out.println("종료");
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
