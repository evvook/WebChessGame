package service.gameparts.piece.relativePosition;

import service.gameException.NotOnBoardException;
import service.gameparts.Board;
import service.gameparts.Position;
public abstract class RelativePosition {
	private Board board;
	
	public RelativePosition(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}
	
	protected String increase(String letter) {
		char c = letter.charAt(0);
		int ascii = (int)c + 1;
		return String.valueOf((char) ascii);
	}
	protected String decrease(String letter) {
		char c = letter.charAt(0);
		int ascii = (int)c - 1;
		return String.valueOf((char) ascii);
	}
	
	public abstract Position getNext(Position position) throws NotOnBoardException;
}
