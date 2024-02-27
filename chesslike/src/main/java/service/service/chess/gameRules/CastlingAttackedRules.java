package service.chess.gameRules;

import java.util.Iterator;
import java.util.List;

import service.chess.gameparts.piece.moves.ChessMovesType;
import service.gameRules.MoveFilter;
import service.gameRules.Rules;
import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class CastlingAttackedRules extends Rules{
	private MoveFilter filter;
	private ChessGameRulesManager crm;
	private boolean castlingPathAttaced;
	
	public CastlingAttackedRules(MovesType movesType,ChessGameRulesManager crm) {
		// TODO Auto-generated constructor stub
		super(movesType);
		this.crm = crm;
	}
	
	public Moves judge(Moves moves) {
		MovesType type = moves.getMovesType();
		if(type.equalsType(ChessMovesType.Castling)) {
			
			Position kingPosition = moves.getFrom();
			Piece king = kingPosition.getOnPiece();
			Camp camp = king.getCamp();
			//활성화되지 않은 진영의 캐슬링 여부는 판단할 필요 없음
			if(!camp.isActive())
				return null;
			
			if(!crm.testCheck(camp)) {
				//체크인 수가 없다면
				Moves judgedMoves = null;
				//캐슬링의 판단 책임 넘김(책임연쇄)
				if(hasNext()) {
					//캐슬링 불가능 수는 null로 걸러질거고, 가능 수는 자살수까지 연쇄로 검토할 것
					Rules nextRules = getNextRules();
					judgedMoves = nextRules.judge(moves);
				}
				
				if(isBeginning(moves)) {
					//첫 움직임엔 플래그 초기화
					castlingPathAttaced = false;
				}
				
				//무브 생성 패턴상 (캐슬링 위치 아닌)경로가 먼저 판단됨
				if(judgedMoves == null) {
					//캐슬링 진행경로인 경우 공격받는지(진행경로로 이동가는한 말이 있는지) 확인
					Camp oppositeCamp = camp.getOppositeCamp();
					List<Piece> oppositeCampUnits = oppositeCamp.getActiveUnits();
					
					Iterator<Piece> pi = oppositeCampUnits.iterator();					
					oppositeCampUnits = oppositeCamp.getActiveUnits();
					pi = oppositeCampUnits.iterator();
					//상대의 모든 활성화 유닛에 대해서 실행
					//유닛마다 초기화해주고
					setMoveFilter();
					while(pi.hasNext()){
						Piece piece = pi.next();
						List<Moves> movesList = piece.getMoves();
						Iterator<Moves> mi = movesList.iterator();
						//해당 유닛의 모든 수에 대해서 실행
						while(mi.hasNext()){
							Moves oppositeUnitMoves = mi.next();
							//움직임에 대해 평가하기 전엔 항상 필터링을 거쳐야 한다.
							//setMoveFilter();
							Moves filteredMoves = filter.filter(oppositeUnitMoves);
							if(filteredMoves != null) {
								type = filteredMoves.getMovesType();
								//공격수이고
								if(type.equalsType(ChessMovesType.Move)) {
									//to의 위치와 일치하면
									if(oppositeUnitMoves.getTo().equals(moves.getTo())) {
										//캐슬링 위치로 이동 불가
										castlingPathAttaced = true;
									}
								}
							}
						}
					}
				}else if(judgedMoves != null){
					//캐슬링 수가 가능하더라도
					//경로가 공격받지 않는 경우만=2번룰
					if(!castlingPathAttaced) {
						//캐슬링 위치에서 킹이 공격받지 않는 경우=3번룰
						//캐슬링 리턴(위에서 한 번 판정 했으므로 무브 리턴만 함)
						return moves;
					}
				}
			}
		}
		return null;
	}

	private void setMoveFilter() {
		//상대방의 공격수에 대해서만 평가
		this.filter = crm.getInstanceCastlingRulesFilter();
	}
}
