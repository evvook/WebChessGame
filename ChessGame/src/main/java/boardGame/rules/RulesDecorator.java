package boardGame.rules;

import boardGame.game.GameMediator;
import boardGame.move.Move;

public class RulesDecorator implements Rules{
	private Rules rules;
	protected GameMediator gm;
	public RulesDecorator(Rules rules) {
		// TODO Auto-generated constructor stub
		this.rules = rules;
		this.gm = rules.getGameManager();
	}
	@Override
	public JResult judge(Move move) {
		// TODO Auto-generated method stub
		return rules.judge(move);
	}
	@Override
	public GameMediator getGameManager() {
		// TODO Auto-generated method stub
		return this.gm;
	}
}
