package boardGame.rules;

import boardGame.move.Move;

public class AttackRulePostionBlank extends RulesDecorator {
	public AttackRulePostionBlank(Rules rules) {
		super(rules);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JResult judge(Move move) {
		// TODO Auto-generated method stub
		if(gm.isCursoredPositionBlank())
			return new JResultImpl(false,null);
			
		return super.judge(move);
	}

}
