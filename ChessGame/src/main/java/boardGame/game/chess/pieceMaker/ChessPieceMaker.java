package boardGame.game.chess.pieceMaker;

import java.util.ArrayList;
import java.util.List;

import boardGame.cursor.CursorMaker;
import boardGame.game.GameMediator;
import boardGame.partsOfGame.Maneuver;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.chess.ChessPiece;

public abstract class ChessPieceMaker {
	protected CursorMaker cursorMaker;
	protected GameMediator gm;
	protected String rank;
	public ChessPieceMaker(CursorMaker cursorMaker,GameMediator gm) {
		this.cursorMaker = cursorMaker;
		this.gm = gm;
	}
	
	public Piece makePiece() {
		List<Maneuver> maneuvers = new ArrayList<Maneuver>();
		maneuvers.addAll(makeMoveOn());
		maneuvers.addAll(makeAttackOn());
		maneuvers.addAll(makeCastling());
		maneuvers.addAll(makePromotion());
		maneuvers.addAll(makeEmpassant());
		
		return new ChessPiece(gm,rank,maneuvers);
	}
	
	protected abstract List<Maneuver> makeMoveOn();
	protected abstract List<Maneuver> makeAttackOn();
	protected abstract List<Maneuver> makeCastling();
	protected abstract List<Maneuver> makePromotion();
	protected abstract List<Maneuver> makeEmpassant();
}
