package chess;

import utils.CharCalculater;

public class UpFileCursor extends Cursor {

	public UpFileCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterY = position.getLetterY().charAt(0);
		String upFileLetterY = String.valueOf(CharCalculater.increase(letterY));
		return board.getIntersection(position.getLetterX()+upFileLetterY);
	}

}
