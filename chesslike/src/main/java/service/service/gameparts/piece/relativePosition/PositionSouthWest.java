package service.gameparts.piece.relativePosition;

import service.gameException.NotOnBoardException;
import service.gameparts.Board;
import service.gameparts.Position;

public class PositionSouthWest extends RelativePosition {

	public PositionSouthWest(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getNext(Position position) throws NotOnBoardException {
		// TODO Auto-generated method stub
		String letterX = position.getLetterX();
		String letterY = position.getLetterY();
		String letter = decrease(letterX) + decrease(letterY);
		Board board = getBoard();
		try {
			return board.getPosition(letter);
		}catch(NotOnBoardException e) {
			throw e;
		}
	}

}
