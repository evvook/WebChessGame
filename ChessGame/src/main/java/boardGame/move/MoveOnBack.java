package boardGame.move;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;

public class MoveOnBack extends Move {
	public MoveOnBack(GameMediator gm,Position to) {
		this.gm = gm;
		this.from = gm.getCenterPosition();
		this.to = to;
	}
	@Override
	public Move move() {
		gm.selectPosition(from.getLetter());
		gm.changePiecePosition(to);
		
		return null;
	}
}
