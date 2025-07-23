package Rental;
//렌탈 테이블에서 사용하는 렌탈DTO 클래스에 userNumber와 bookId 설정
public class RentalDTO {
	private int userNumber;
	private int bookId;
	
	public int getBookId() {
		return bookId;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
	
}
