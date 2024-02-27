package service.gameRules;

import service.gameparts.Board;
import service.gameparts.Camp;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

//자살 수 금지
public class ProhibitSuicideRules extends Rules{
	protected Board board;
	protected RulesManager rm;
	
	public ProhibitSuicideRules(MovesType movesType,RulesManager rm) {
		// TODO Auto-generated constructor stub 
		super(movesType);
		this.rm = rm;
	}

	@Override
	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		if(moves == null)
			return null;
		
		MovesType moveType = moves.getMovesType();
		if(getMovesType().equalsType(moveType)) {
			
			Camp camp = moves.getFrom().getOnPiece().getCamp();
			//최적화를 위한 조건
			//검사를 위한 테스트 상태 이거나 진영이 활성화된 상태 모두 아닌경우 검사 안 함
			if(!camp.isTestState() && !camp.isActive()) {
				return moves;
			}
			//수 실행
			moves.execute(board);
			//실행 수에 대해서 체크 여부 확인
			boolean isCheck = rm.testCheck(camp);
			//수 실행취소
			moves.undo();
			//체크가 아니면
			if(!isCheck) {
				if(hasNext()) {
					Rules nextRules = getNextRules();
					return nextRules.judge(moves);
				}else {
					return moves;
				}
			}
		}
		//체크 수가 있다면 수 제외
		return null;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
