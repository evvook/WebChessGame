package chess;

import utils.CharCalculater;

public class LowFileCursor extends Cursor {

	public LowFileCursor(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Intersection getPosition(Intersection position) {
		// TODO Auto-generated method stub
		Character letterY = position.getLetterY().charAt(0);
		String lowFilekLetterY = String.valueOf(CharCalculater.decrease(letterY));
		return board.getIntersection(position.getLetterX()+lowFilekLetterY);
	}

}
