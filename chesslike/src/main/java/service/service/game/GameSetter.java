package service.game;

import java.util.Iterator;
import java.util.List;

import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;

public abstract class GameSetter {
	public abstract void setGame(GameManager gm);
	
	public void setMoveObserver(Piece piece) {
		List<Moves> pieceMovesList = piece.getAllMoves();
		Iterator<Moves> mi = pieceMovesList.iterator();
		while(mi.hasNext()) {
			piece.registMoveObserver(mi.next());
		}
	}
	
	public void setPieceOnPosition(Piece piece, Position position) {
		piece.registMoveObserver(position);
		piece.registStateObserver(position);
		piece.setPosition(position);
	}
}
