package service.gameparts.piece.relativePosition;

import service.gameException.NotOnBoardException;
import service.gameparts.Board;
import service.gameparts.Position;

public class PositionNorth extends RelativePosition {

	public PositionNorth(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getNext(Position position) throws NotOnBoardException {
		// TODO Auto-generated method stub
		String letterX = position.getLetterX();
		String letterY = position.getLetterY();
		String letter = letterX + increase(letterY);
		Board board = getBoard();
		try {
			return board.getPosition(letter);
		}catch(NotOnBoardException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
