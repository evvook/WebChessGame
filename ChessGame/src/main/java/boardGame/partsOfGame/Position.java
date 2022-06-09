package boardGame.partsOfGame;

public class Position {
	private String letterX;
	private String letterY;
	
	public Position(String letterX, String letterY) {
		this.letterX = letterX;
		this.letterY = letterY;
	}
	
	//위치의 문자를 리턴
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
