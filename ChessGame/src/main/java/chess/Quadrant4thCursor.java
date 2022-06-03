package chess;

import utils.CharCalculater;

public class Quadrant4thCursor extends Cursor {

	public Quadrant4thCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterX = position.getLetterX().charAt(0);
		Character letterY = position.getLetterY().charAt(0);
		String upRankLetterX = String.valueOf(CharCalculater.increase(letterX));
		String lowFilekLetterY = String.valueOf(CharCalculater.decrease(letterY));
		return board.getIntersection(upRankLetterX+lowFilekLetterY);
	}

}
