package boardGame.move.chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import boardGame.game.GameMediator;
import boardGame.move.AttackOn;
import boardGame.move.Move;
import boardGame.move.MoveMaker;
import boardGame.partsOfGame.Position;
import boardGame.rules.JResult;
import boardGame.rules.Rules;

public class KnightAttackOnMaker implements MoveMaker {
	private GameMediator gm;
	private Rules attackOnRules;
	public KnightAttackOnMaker(GameMediator gm, Rules rules) {
		// TODO Auto-generated constructor stub
		this.gm = gm;
		this.attackOnRules = rules;
		}

	public List<Move> makeMove(List<Position> path){
		List<Move> attackOns = new ArrayList<Move>();
		
		Iterator<Position> ii = path.iterator();
		Position to = null;
		while(ii.hasNext()) {
			to = ii.next();
		}
		if(to != null) {
			gm.setAroundPosition(gm.findPosition(to.getLetter()));
			//규칙에 맞으면 수를 생성한다.
			JResult jReuslt = attackOnRules.judge(new AttackOn(gm, to));
			if(!jReuslt.isEmptyMove()) {
				attackOns.add(jReuslt.getMove());
			}
		}
		return attackOns;
	}
}
