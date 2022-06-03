package chess;

public abstract class Move {
	protected Intersection from;
	protected Intersection to;
	
	public abstract void move();
	
	//수를 둘 교차점의 문자를 리턴
	public String showTo() {
		return this.to.getLetter();
	}
}
