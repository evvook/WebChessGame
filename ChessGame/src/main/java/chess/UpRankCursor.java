package chess;

import utils.CharCalculater;

public class UpRankCursor extends Cursor {

	public UpRankCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterX = position.getLetterX().charAt(0);
		String upRankLetterX = String.valueOf(CharCalculater.increase(letterX));
		return board.getIntersection(upRankLetterX+position.getLetterY());
	}

}
