package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import utils.CharCalculater;

public class Quadrant1stCursor extends Cursor {

	public Quadrant1stCursor(GameMediator gm) {
		super(gm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		Position currentPosition = gm.getAroundPosition();
		
		Character letterX = currentPosition.getLetterX().charAt(0);
		Character letterY = currentPosition.getLetterY().charAt(0);
		String upRankLetterX = String.valueOf(CharCalculater.increase(letterX));
		String upFileLetterY = String.valueOf(CharCalculater.increase(letterY));
		gm.setAroundPosition(gm.findPosition(upRankLetterX+upFileLetterY));
		return gm.getAroundPosition();
	}

}
