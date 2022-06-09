package boardGame.rules;

import boardGame.move.Move;

public class AttackRulePieceSameSide extends RulesDecorator {
	
	public AttackRulePieceSameSide(Rules rules) {
		super(rules);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JResult judge(Move move) {
		// TODO Auto-generated method stub
		if(!gm.isEnemy())
			return new JResultImpl(true,null);
			
		return super.judge(move);
	}
}
