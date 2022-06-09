package boardGame.rules;

import boardGame.game.GameMediator;
import boardGame.move.Move;

public class Judgement implements Rules {
	protected GameMediator gm;

	public Judgement(GameMediator gm) {
		// TODO Auto-generated constructor stub
		this.gm = gm;
	}

	@Override
	public JResult judge(Move move) {
		// TODO Auto-generated method stub
		return new JResultImpl(null);
	}

	@Override
	public GameMediator getGameManager() {
		// TODO Auto-generated method stub
		return this.gm;
	}
}
