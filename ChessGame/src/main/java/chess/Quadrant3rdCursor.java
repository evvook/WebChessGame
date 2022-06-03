package chess;

import utils.CharCalculater;

public class Quadrant3rdCursor extends Cursor {

	public Quadrant3rdCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterX = position.getLetterX().charAt(0);
		Character letterY = position.getLetterY().charAt(0);
		String lowRankLetterX = String.valueOf(CharCalculater.decrease(letterX));
		String lowFilekLetterY = String.valueOf(CharCalculater.decrease(letterY));
		return board.getIntersection(lowRankLetterX+lowFilekLetterY);
	}

}
