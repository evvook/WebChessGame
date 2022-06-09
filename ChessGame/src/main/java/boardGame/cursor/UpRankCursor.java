package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import utils.CharCalculater;

public class UpRankCursor extends Cursor {

	public UpRankCursor(GameMediator gm) {
		super(gm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		Position currentPosition = gm.getAroundPosition();
		
		Character letterX = currentPosition.getLetterX().charAt(0);
		String upRankLetterX = String.valueOf(CharCalculater.increase(letterX));
		
		gm.setAroundPosition(gm.findPosition(upRankLetterX+currentPosition.getLetterY()));
		return gm.getAroundPosition();
	}

}
