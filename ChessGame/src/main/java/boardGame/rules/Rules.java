package boardGame.rules;

import boardGame.game.GameMediator;
import boardGame.move.Move;

public interface Rules {

	public JResult judge(Move move);
	public GameMediator getGameManager();
}
