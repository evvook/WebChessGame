package service.gameRules;

import service.gameparts.Position;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class AttackPathRules extends Rules{
	protected Position unblankPassedPosition;
	
	public AttackPathRules(MovesType movesType) {
		super(movesType);
	}
	
	
	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		if(moves == null)
			return null;
		MovesType moveType = moves.getMovesType();
		//이동의 경우
		if(getMovesType().equalsType(moveType)) {
			//시작위치인 경우 초기화
			if(isBeginning(moves)) {
				unblankPassedPosition = null;
			}
			//지나간 위치가 있다면 움직임 수 필터링함
			if(unblankPassedPosition != null) {
				return null;
			}else {
				//지나간 위치가 설정되지 않았고
				//비어있지 않다면
				if(moves.getTo().getOnPiece() != null) {
					//기물이 존재하는 위치 설정하고
					unblankPassedPosition = moves.getTo();
					//판단을 넘긴다.
					if(hasNext()) {
						Rules nextRules = getNextRules();
						return nextRules.judge(moves);
					}else {
						return moves;
					}
				}
			}
		}
		//어떤 경우도 아닌 경우 수 생성하지 않음
		return null;
	}
}
