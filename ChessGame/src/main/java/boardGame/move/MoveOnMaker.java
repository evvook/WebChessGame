package boardGame.move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import boardGame.game.GameMediator;
import boardGame.partsOfGame.Position;
import boardGame.rules.JResult;
import boardGame.rules.Rules;

public class MoveOnMaker implements MoveMaker {
	private GameMediator gm;
	private Rules moveOnRules;
	public MoveOnMaker(GameMediator gm,Rules moveOnRules) {
		this.gm = gm;
		this.moveOnRules = moveOnRules;
	}
	
	public List<Move> makeMove(List<Position> path){
		
		List<Move> moveOns = new ArrayList<Move>();
		Iterator<Position> ii = path.iterator();
		while(ii.hasNext()){
			Position to = ii.next();
			//커서를 to로 이동하고, 판정
			gm.setAroundPosition(gm.findPosition(to.getLetter()));
			//규칙에 맞으면 수를 생성한다.
			JResult jReuslt = moveOnRules.judge(new MoveOn(gm, to));
			if(!jReuslt.isEmptyMove()) {
				moveOns.add(jReuslt.getMove());
			}
			if(jReuslt.isStopedToMakeMove()) {
				break;
			}
		}
		return moveOns;
	}
}
