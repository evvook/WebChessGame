package boardGame.rules.chess;

import boardGame.move.Move;
import boardGame.rules.JResult;
import boardGame.rules.Rules;
import boardGame.rules.RulesDecorator;

public class PwanMoveDirection extends RulesDecorator {
	
	public PwanMoveDirection(Rules rules) {
		super(rules);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JResult judge(Move move) {
		// TODO Auto-generated method stub
		
		String piecePositionLetter = gm.getCenterPosition().getLetter();
		String moveToLetter = move.getToPositionLetter();
		
		if(gm.isGoingFirst()) {
			if(isUpDirection(piecePositionLetter,moveToLetter)) {
				return super.judge(move);
			}else {
				return super.judge(null);
			}
		}else {
			if(isLowDirection(piecePositionLetter,moveToLetter)) {
				return super.judge(move);
			}else {
				return super.judge(null);
			}
		}
		
		
	}

	private boolean isLowDirection(String piecePositionLetter, String moveToLetter) {
		// TODO Auto-generated method stub
		int file1 = Integer.valueOf(piecePositionLetter.charAt(1));
		int file2 = Integer.valueOf(moveToLetter.charAt(1));
		
		return file1 > file2;
	}

	private boolean isUpDirection(String piecePositionLetter, String moveToLetter) {
		// TODO Auto-generated method stub
		int file1 = Integer.valueOf(piecePositionLetter.charAt(1));
		int file2 = Integer.valueOf(moveToLetter.charAt(1));
		
		return file2 > file1;
	}
}
