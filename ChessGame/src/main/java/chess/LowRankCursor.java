package chess;

import utils.CharCalculater;

public class LowRankCursor extends Cursor {

	public LowRankCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterX = position.getLetterX().charAt(0);
		String lowRankLetterX = String.valueOf(CharCalculater.decrease(letterX));
		return board.getIntersection(lowRankLetterX+position.getLetterY());
	}

}
