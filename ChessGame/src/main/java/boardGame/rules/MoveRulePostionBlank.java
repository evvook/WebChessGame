package boardGame.rules;

import boardGame.move.Move;

public class MoveRulePostionBlank extends RulesDecorator {
	public MoveRulePostionBlank(Rules rules) {
		super(rules);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JResult judge(Move move) {
		// TODO Auto-generated method stub
		if(gm.isCursoredPositionBlank())
			return new JResultImpl(false,move);
		
		return super.judge(move);
	}

}
