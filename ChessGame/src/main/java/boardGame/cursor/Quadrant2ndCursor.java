package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import utils.CharCalculater;

public class Quadrant2ndCursor extends Cursor {

	public Quadrant2ndCursor(GameMediator gm) {
		super(gm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		Position currentPosition = gm.getAroundPosition();
		
		Character letterX = currentPosition.getLetterX().charAt(0);
		Character letterY = currentPosition.getLetterY().charAt(0);
		String lowRankLetterX = String.valueOf(CharCalculater.decrease(letterX));
		String upFileLetterY = String.valueOf(CharCalculater.increase(letterY));
		gm.setAroundPosition(gm.findPosition(lowRankLetterX+upFileLetterY));
		return gm.getAroundPosition();
	}

}
