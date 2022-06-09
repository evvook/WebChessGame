package boardGame.rules;

import boardGame.move.Move;

public class JResultImpl implements JResult {
	private boolean isStoped;
	private Move move;
	
	public JResultImpl(boolean isStoped,Move move) {
		this.isStoped = isStoped;
		this.move = move;
	}
	
	public JResultImpl(Move move) {
		this.move = move;
		isStoped = true;	
	}

	@Override
	public boolean isStopedToMakeMove() {
		// TODO Auto-generated method stub
		return isStoped;
	}

	@Override
	public boolean isEmptyMove() {
		// TODO Auto-generated method stub
		return move == null;
	}

	@Override
	public Move getMove() {
		// TODO Auto-generated method stub
		return move;
	}

}
