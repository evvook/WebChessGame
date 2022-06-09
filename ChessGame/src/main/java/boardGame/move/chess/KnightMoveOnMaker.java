package boardGame.move.chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import boardGame.game.GameMediator;
import boardGame.move.Move;
import boardGame.move.MoveMaker;
import boardGame.move.MoveOn;
import boardGame.partsOfGame.Position;
import boardGame.rules.JResult;
import boardGame.rules.Rules;

public class KnightMoveOnMaker implements MoveMaker {
	private GameMediator gm;
	private Rules moveOnRules;
	public KnightMoveOnMaker(GameMediator gm,Rules moveOnRules) {
		this.gm = gm;
		this.moveOnRules = moveOnRules;
	}
	
	public List<Move> makeMove(List<Position> path){
		List<Move> moveOns = new ArrayList<Move>();
		
		Iterator<Position> ii = path.iterator();
		Position to = null;
		while(ii.hasNext()) {
			to = ii.next();
		}
		if(to != null) {
			gm.setAroundPosition(gm.findPosition(to.getLetter()));
			//규칙에 맞으면 수를 생성한다.
			JResult jReuslt = moveOnRules.judge(new MoveOn(gm, to));
			if(!jReuslt.isEmptyMove()) {
				moveOns.add(jReuslt.getMove());
			}
		}

		return moveOns;
	}
}
