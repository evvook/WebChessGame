package chess;

public class Intersection {
	private Piece piece;
	private String letterX;
	private String letterY;
	
	public Intersection(String letterX, String letterY) {
		this.letterX = letterX;
		this.letterY = letterY;
	}
	
	//비었는지 확인한다.
	public boolean isBlank() {
		return this.piece == null;
	}
	
	//기물을 리턴
	public Piece getPiece() {
		return this.piece;
	}

	//기물을 세트
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	//기물을 비움
	public void setBlank() {
		this.piece = null;
	}
	
	//교차점의 문자를 리턴
	public String getLetter() {
		return this.letterX+this.letterY;
	}

	public String getLetterX() {
		return letterX;
	}

	public String getLetterY() {
		return letterY;
	}
}
