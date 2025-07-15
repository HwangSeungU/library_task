package useManager;

import java.util.ArrayList;
import java.util.Scanner;

import bookManager.Book;
import bookManager.BookList;
import exceptionManager.BookAlreadyException;
import exceptionManager.BookNotAvailableException;
import exceptionManager.MaxBorrowException;
import memberManager.Member;
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
            User loginU = User.login(id, pw);
            if(loginU != null) {
            	consol(sc, loginU);
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
            User addUser = new Member(name, phoneNumber,id,pw, new ArrayList<>());
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
   
    public void consol(Scanner sc, User addUser) {
      while (true) {
         ConsoleUI.menu("목록 확인", "책 대출하기", "반납 하기", "내 대여 목록 확인" ,"뒤로가기");
         Member member = (Member) addUser;
       int bookId = 0;
       BookList bl = new BookList();
       Book searchIdBook = null;
         button = sc.nextInt();
         sc.nextLine();
         switch (button) {
         case 1:
            //목록확인 메소드
        	 bl.printAll();
        	 break;
         case 2:
        	 System.out.print("대출할 책 ID를 입력해주세요 : ");
        	 bookId = sc.nextInt();
        	 sc.nextLine();
        	 searchIdBook = bl.searchIdBook(bookId);
//        	 System.out.println(searchIdBook);
//        	 System.out.println(addUser.getName());
        	 try {
				member.borrowBook(searchIdBook);
			} catch (BookAlreadyException | MaxBorrowException | BookNotAvailableException e) {
				e.printStackTrace();
			}
            break;
         case 3:
            //반납하는 메소드
        	System.out.println("반납하실 도서의 id를 입력해주세요");
        	bookId = sc.nextInt();
        	sc.nextLine();
        	searchIdBook = bl.searchIdBook(bookId);
            try {
				member.returnBook(searchIdBook);
			} catch (BookNotAvailableException e) {
				e.printStackTrace();
			}
            break;   
         case 4 : 
        	 member.checkBooks();
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
