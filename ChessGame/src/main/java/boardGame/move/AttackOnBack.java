package boardGame.move;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.Position;

public class AttackOnBack extends Move {
	private Piece removedPiece;
	public AttackOnBack(GameMediator gm, Position to, Piece removedPiece) {
		// TODO Auto-generated constructor stub
		this.gm = gm;
		this.to = to;
		this.from = gm.getCenterPosition();
		this.removedPiece = removedPiece;
	}
	@Override
	public Move move() {
		gm.selectPosition(from.getLetter());
		gm.changePiecePosition(to);
		gm.restorePiece(from, removedPiece);
		
		return null;
	}
}
