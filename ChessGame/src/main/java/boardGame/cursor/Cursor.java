package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;

public abstract class Cursor {
	protected GameMediator gm;
	public Cursor(GameMediator gm) {
		this.gm = gm;
	}
	public abstract Position getPosition();
	
	public boolean isOutOfBoard() {
		return getPosition() == null;
	}
}
