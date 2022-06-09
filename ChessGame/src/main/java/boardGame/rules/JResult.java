package boardGame.rules;

import boardGame.move.Move;

public interface JResult {
	public boolean isStopedToMakeMove();
	public boolean isEmptyMove();
	public Move getMove();
}
