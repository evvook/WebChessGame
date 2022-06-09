package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import utils.CharCalculater;

public class UpFileCursor extends Cursor {

	public UpFileCursor(GameMediator gm) {
		super(gm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		Position currentPosition = gm.getAroundPosition();
		
		Character letterY = currentPosition.getLetterY().charAt(0);
		String upFileLetterY = String.valueOf(CharCalculater.increase(letterY));
		
		gm.setAroundPosition(gm.findPosition(currentPosition.getLetterX()+upFileLetterY));
		return gm.getAroundPosition();
	}

}
