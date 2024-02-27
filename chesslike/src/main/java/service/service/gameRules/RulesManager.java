package service.gameRules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import service.gameparts.Camp;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;

public abstract class RulesManager {
	public abstract MoveFilter getInstanceCommonRulesFilter();
	public abstract MoveFilter getInstanceCheckStateFilter();
	public abstract MoveFilter getInstanceSimpleRuleFilter();
	
	public boolean testCheck(Camp camp) {
		List<Moves> checkMoves = new ArrayList<Moves>();
		
		Piece load = camp.findLordUnit();
		List<Piece> oppositeUnitss = camp.getOppositeCamp().getActiveUnits();
		Iterator<Piece> pi = oppositeUnitss.iterator();
		//상대의 모든 활성화 유닛에 대해서 실행
		MoveFilter filter = getInstanceSimpleRuleFilter();
		while(pi.hasNext()){
			Piece piece = pi.next();
			List<Moves> movesList = piece.getMoves();
			Iterator<Moves> mi = movesList.iterator();
			//해당 유닛의 모든 수에 대해서 실행
			while(mi.hasNext()){
				Moves oppositeUnitMoves = mi.next();
				Moves filteredMoves;
				filteredMoves = filter.filter(oppositeUnitMoves);
				if(filteredMoves != null) {
					//킹이 공격받는 경우
					//해당수의 경로에 킹이 있다면(어차피 필터로 걸러서 공격수 외에는 다른 진영의 유닛이 위치에 있을 수 없음)
					if(load.equals(filteredMoves.getTo().getOnPiece())) {
						//체크 수 수집
						checkMoves.add(filteredMoves);
					}
				}
			}
		}
		//체크 수가 없다면
		if(checkMoves.size()!=0) {
			return true;
		}else {
			return false;
		}
	}
}
