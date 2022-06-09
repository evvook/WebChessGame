package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import utils.CharCalculater;

public class Quadrant3rdCursor extends Cursor {

	public Quadrant3rdCursor(GameMediator gm) {
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
		String lowFilekLetterY = String.valueOf(CharCalculater.decrease(letterY));
		gm.setAroundPosition(gm.findPosition(lowRankLetterX+lowFilekLetterY));
		return gm.getAroundPosition();
	}

}
