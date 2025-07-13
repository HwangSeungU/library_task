package bookManager;

import java.util.ArrayList;
import java.util.List;

public class BookList {
	List<Book> booklist = new ArrayList<>();
	booklist.add(new Book(1,"코스모스","칼 세이건",false));
	Book book2 = new Book(2,"데미안","헤르만 헤세",false);
	booklist.add(book2);
	booklist.add(new Book(3,"돈의 심리학","모건 하루절",false));
	booklist.add(new Book(4,"넛지","리차드탈러",false));
	booklist.add(new Book(5,"부자들의 개인 도서관","이상건",false));
	booklist.add(new Book(6,"장미와 나이프","히가시노 게이고",false));
	booklist.add(new Book(7,"챗GPT 글쓰기","정태일",false));
	booklist.add(new Book(8,"아주 작은 습관의 힘","제임스 클리어",false));
	booklist.add(new Book(9,"월간채소","송지현",false));
	booklist.add(new Book(10,"윤슬의 바다","백은별",false));
	booklist.add(new Book(11,"마법천자문","스튜디오시리얼",false));
	booklist.add(new Book(12,"청춘의 독서","유시민",false));
	booklist.add(new Book(13,"해커스 토익 기출문제집","해커스",false));
	booklist.add(new Book(14,"초역부처의 말","코이케 류노스케",false));
	booklist.add(new Book(15,"살아있는 자들을 위한 죽음 수업","이호",false));
}
