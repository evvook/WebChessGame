package boardGame.rules;

import boardGame.move.Move;

public class AttackRulePieceOpposite extends RulesDecorator {
	
	public AttackRulePieceOpposite(Rules rules) {
		super(rules);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JResult judge(Move move) {
		// TODO Auto-generated method stub
		if(gm.isEnemy())
			return new JResultImpl(true,move);
		
		return super.judge(move);
	}
}
