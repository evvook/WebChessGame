package boardGame.move;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.Position;

public class AttackOn extends Move {
	public AttackOn(GameMediator gm, Position to) {
		// TODO Auto-generated constructor stub
		this.gm = gm;
		this.to = to;
		//helpDebug();
	}
	@Override
	public Move move() {
		Position from = gm.getCenterPosition();
		Piece removedPiece = gm.removePiece(to);
		gm.changePiecePosition(to);
		return new AttackOnBack(gm,from,removedPiece);
	}
}
