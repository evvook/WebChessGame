package boardGame.move;

import java.util.ArrayList;
import java.util.List;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import boardGame.rules.JResult;
import boardGame.rules.Rules;

public class AttackOnMaker implements MoveMaker {
	private GameMediator gm;
	private Rules attackOnRules;
	public AttackOnMaker(GameMediator gm, Rules rules) {
		// TODO Auto-generated constructor stub
		this.gm = gm;
		this.attackOnRules = rules;
		}

	public List<Move> makeMove(List<Position> path){
		List<Move> attackOns = new ArrayList<Move>();
		for(Position to:path) {
			gm.setAroundPosition(gm.findPosition(to.getLetter()));
			//규칙에 맞으면 수를 생성한다.
			JResult jReuslt = attackOnRules.judge(new AttackOn(gm, to));
			if(!jReuslt.isEmptyMove()) {
				attackOns.add(jReuslt.getMove());
			}
			if(jReuslt.isStopedToMakeMove()) {
				break;
			}
		}
		return attackOns;
	}
}
