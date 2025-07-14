package useManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import bookManager.Book;
import bookManager.BookList;
import userManager.User;


public class ConsoleUI {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ConsoleUI cu = new ConsoleUI();
		cu.login(sc);
	}
	
   private int button = 0;
   // 로그인 기능
public void login(Scanner sc) {
      while (true) {
    	  String id = "";
    	  String pw = "";
    	  String phoneNumber = "";
    	  String name = "";
         ConsoleUI.menu("로그인", "회원가입", "프로그램 종료");
         button = sc.nextInt();
         sc.nextLine();
         switch (button) {
         case 1:
            //로그인 메소드
            System.out.println("로그인");
            System.out.print("ID 입력 : \n");
            id = sc.nextLine();
            System.out.print("PASSWORD 입력 : \n");
            pw = sc.nextLine();
            if(User.login(id,pw)) {
            	consol(sc);
            } else {
            	System.out.println("프로그램을 강제로 종료합니다.");
            	return;
            }
            break;
         case 2:
            //회원가입 메소드
            System.out.println("회원가입");
            System.out.print("이름 : \n");
            name = sc.nextLine();
            System.out.print("전화번호 : \n");
            phoneNumber = sc.nextLine();
            System.out.print("아이디 : ");
            id = sc.nextLine();
            System.out.print("비밀번호 : ");
            pw = sc.nextLine();
            User addUser = new User(name, phoneNumber,id,pw);
            User.signUpUser(addUser);
            break;
         case 3:
            // 종료 메소드
            System.out.println("프로그램을 종료합니다");
            break;
         default:
        	 System.out.println("제시된 번호 외의 다른 번호를 입력하지 말아주세요.");
            break;
         } // switch
         if(button == 3) {
            break;
         }
      } 
   }
   
    public void consol(Scanner sc) {
      while (true) {
         ConsoleUI.menu("목록 확인", "책 대출하기", "반납 하기", "내 대여 목록 확인" ,"뒤로가기");
         BookList bl = new BookList();
         button = sc.nextInt();
         sc.nextLine();
         switch (button) {
         case 1:
            //목록확인 메소드
        	 bl.printAll();
        	 break;
         case 2:
        	 System.out.print("대출할 책 ID를 입력해주세요 : ");
        	 int bookId = sc.nextInt();
        	 Book searchIdBook = bl.searchIdBook(bookId);
        	 if(searchIdBook == null) {
        		 System.out.println("입력하신 ID는 존재하지 않는 ID 입니다.");
        	 }else if(!searchIdBook.isBorrow()) {
        		 bl.addBook(searchIdBook);
        		 System.out.println(searchIdBook.getTitle() + " 대출 완료");
        	 }else {
        		 System.out.println("대출할 수 없는 책입니다.");
        	 }
            break;
         case 3:
            //반납하는 메소드
            System.out.println("반납");
            break;   
         case 4 : 
        	 System.out.println("현재 내 대여 목록");
        	 break;
         case 5 : 
        	 //완전 종료
        	 System.out.println("뒤로가기");
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
