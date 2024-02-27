package service.gameRules;

import service.gameparts.Position;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class MovePathRules extends Rules{
	protected Position unblankPassedPosition;
	
	public MovePathRules(MovesType movesType) {
		super(movesType);
	}
	
	
	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		if(moves == null)
			return null;
		MovesType moveType = moves.getMovesType();
		//이동의 경우
		if(getMovesType().equalsType(moveType)) {
			//새로운 수의 시작에선 지나간 위치 초기화
			//시작위치인 경우 초기화
			if(isBeginning(moves)) {
				//기물이 존재하는 이미 지나간 위치
				unblankPassedPosition = null;
			}
			//지나간 위치가 있다면 움직임 수 필터링함
			if(unblankPassedPosition != null) {
				return null;
			}else {
				//지나간 위치가 설정되지 않았고
				//비어있지 않다면
				if(moves.getTo().getOnPiece() != null) {
					//기물이 존재하는 위치 설정
					unblankPassedPosition = moves.getTo();
					return null;
				}else {
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
