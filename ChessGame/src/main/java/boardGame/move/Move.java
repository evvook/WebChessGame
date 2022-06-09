package boardGame.move;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;

public abstract class Move {
	protected GameMediator gm;
	protected Position to;
	protected Position from;
	
	public abstract Move move();
	//public abstract void undo();
	
	//수를 둘 교차점의 문자를 리턴
	public String showTo() {
		return this.to.getLetter();
	}
	
	public boolean equalsTo(Position position) {
		return this.to.equals(position);
	}
	
	public String getToPositionLetter() {
		return this.to.getLetter();
	}
}
