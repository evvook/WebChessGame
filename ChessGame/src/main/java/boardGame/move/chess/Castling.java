package boardGame.move.chess;

import boardGame.move.Move;
import boardGame.partsOfGame.Position;

public class Castling extends Move {
	private Position rookFrom;
	private Position rookTo;

	public Castling(Position kingFrom,Position kingTo, Position rookFrom, Position rookTo) {
		this.from = kingFrom;
		this.to = kingTo;
		this.rookFrom = rookFrom;
		this.rookTo = rookTo;
		
	}
	@Override
	public void move() {
		//킹 이동
		to.setPiece(from.getPiece());
		//룩 이동
		rookTo.setPiece(rookFrom.getPiece());
	}
}
