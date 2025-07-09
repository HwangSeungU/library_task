package useManager;

import java.util.Scanner;

public class ConsoleUI {
	private int button = 0;
	// 로그인 기능
	
	 public void Consol(Scanner sc) {
		
		while (true) {
			ConsoleUI.menu("목록 확인", "대출 확인", "반납 하기");
			button = sc.nextInt();
			switch (button) {
			case 1:
				//목록확인 메소드
				System.out.println("목록확인");
				break;
			case 2:
				//대출확인 메소드
				System.out.println("대출 확인");
				break;
			case 3:
				//반납하는 메소드
				System.out.println("반납");
				break;	
			default:
				break;
			} // switch
			break; 
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

		System.out.println("=\t0. 종료");
		System.out.println("=\t\t\t\t");
		System.out.println("=================================");
	}
}
