package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import utils.CharCalculater;

public class LowRankCursor extends Cursor {

	public LowRankCursor(GameMediator gm) {
		super(gm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		Position currentPosition = gm.getAroundPosition();
		
		Character letterX = currentPosition.getLetterX().charAt(0);
		String lowRankLetterX = String.valueOf(CharCalculater.decrease(letterX));
		
		gm.setAroundPosition(gm.findPosition(lowRankLetterX+currentPosition.getLetterY()));
		return gm.getAroundPosition();
	}

}
