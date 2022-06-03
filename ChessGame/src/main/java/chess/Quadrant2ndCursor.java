package chess;

import utils.CharCalculater;

public class Quadrant2ndCursor extends Cursor {

	public Quadrant2ndCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterX = position.getLetterX().charAt(0);
		Character letterY = position.getLetterY().charAt(0);
		String lowRankLetterX = String.valueOf(CharCalculater.decrease(letterX));
		String upFileLetterY = String.valueOf(CharCalculater.increase(letterY));
		return board.getIntersection(lowRankLetterX+upFileLetterY);
	}

}
