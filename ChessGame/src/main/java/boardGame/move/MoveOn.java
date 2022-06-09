package boardGame.move;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;

public class MoveOn extends Move {
	public MoveOn(GameMediator gm,Position to) {
		this.gm = gm;
		this.to = to;
	}
	@Override
	public Move move() {
		Position from = gm.getCenterPosition();
		gm.changePiecePosition(to);
		return new MoveOnBack(gm,from);
	}
}
