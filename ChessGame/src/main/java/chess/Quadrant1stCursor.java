package chess;

import utils.CharCalculater;

public class Quadrant1stCursor extends Cursor {

	public Quadrant1stCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterX = position.getLetterX().charAt(0);
		Character letterY = position.getLetterY().charAt(0);
		String upRankLetterX = String.valueOf(CharCalculater.increase(letterX));
		String upFileLetterY = String.valueOf(CharCalculater.increase(letterY));
		return board.getIntersection(upRankLetterX+upFileLetterY);
	}

}
