package boardGame.partsOfGame.chess;

import java.util.List;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Maneuver;
import boardGame.partsOfGame.Piece;

public class ChessPiece extends Piece {
	private boolean isFirstMove;

	public ChessPiece(GameMediator gm, String rank, List<Maneuver> maneuvers) {
		super(gm, rank,maneuvers);
		// TODO Auto-generated constructor stub
		this.isFirstMove = true;
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	public void moved() {
		this.isFirstMove = false;
	}
}
