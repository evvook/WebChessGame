package boardGame.cursor;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import utils.CharCalculater;

public class LowFileCursor extends Cursor {

	public LowFileCursor(GameMediator gm) {
		super(gm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		Position currentPosition = gm.getAroundPosition();
		
		Character letterY = currentPosition.getLetterY().charAt(0);
		String lowFilekLetterY = String.valueOf(CharCalculater.decrease(letterY));
		
		gm.setAroundPosition(gm.findPosition(currentPosition.getLetterX()+lowFilekLetterY));
		return gm.getAroundPosition();
	}

}
