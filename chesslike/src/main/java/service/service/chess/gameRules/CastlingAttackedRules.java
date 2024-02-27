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
			//Ȱ��ȭ���� ���� ������ ĳ���� ���δ� �Ǵ��� �ʿ� ����
			if(!camp.isActive())
				return null;
			
			if(!crm.testCheck(camp)) {
				//üũ�� ���� ���ٸ�
				Moves judgedMoves = null;
				//ĳ������ �Ǵ� å�� �ѱ�(å�ӿ���)
				if(hasNext()) {
					//ĳ���� �Ұ��� ���� null�� �ɷ����Ű�, ���� ���� �ڻ������ ����� ������ ��
					Rules nextRules = getNextRules();
					judgedMoves = nextRules.judge(moves);
				}
				
				if(isBeginning(moves)) {
					//ù �����ӿ� �÷��� �ʱ�ȭ
					castlingPathAttaced = false;
				}
				
				//���� ���� ���ϻ� (ĳ���� ��ġ �ƴ�)��ΰ� ���� �Ǵܵ�
				if(judgedMoves == null) {
					//ĳ���� �������� ��� ���ݹ޴���(�����η� �̵������� ���� �ִ���) Ȯ��
					Camp oppositeCamp = camp.getOppositeCamp();
					List<Piece> oppositeCampUnits = oppositeCamp.getActiveUnits();
					
					Iterator<Piece> pi = oppositeCampUnits.iterator();					
					oppositeCampUnits = oppositeCamp.getActiveUnits();
					pi = oppositeCampUnits.iterator();
					//����� ��� Ȱ��ȭ ���ֿ� ���ؼ� ����
					//���ָ��� �ʱ�ȭ���ְ�
					setMoveFilter();
					while(pi.hasNext()){
						Piece piece = pi.next();
						List<Moves> movesList = piece.getMoves();
						Iterator<Moves> mi = movesList.iterator();
						//�ش� ������ ��� ���� ���ؼ� ����
						while(mi.hasNext()){
							Moves oppositeUnitMoves = mi.next();
							//�����ӿ� ���� ���ϱ� ���� �׻� ���͸��� ���ľ� �Ѵ�.
							//setMoveFilter();
							Moves filteredMoves = filter.filter(oppositeUnitMoves);
							if(filteredMoves != null) {
								type = filteredMoves.getMovesType();
								//���ݼ��̰�
								if(type.equalsType(ChessMovesType.Move)) {
									//to�� ��ġ�� ��ġ�ϸ�
									if(oppositeUnitMoves.getTo().equals(moves.getTo())) {
										//ĳ���� ��ġ�� �̵� �Ұ�
										castlingPathAttaced = true;
									}
								}
							}
						}
					}
				}else if(judgedMoves != null){
					//ĳ���� ���� �����ϴ���
					//��ΰ� ���ݹ��� �ʴ� ��츸=2����
					if(!castlingPathAttaced) {
						//ĳ���� ��ġ���� ŷ�� ���ݹ��� �ʴ� ���=3����
						//ĳ���� ����(������ �� �� ���� �����Ƿ� ���� ���ϸ� ��)
						return moves;
					}
				}
			}
		}
		return null;
	}

	private void setMoveFilter() {
		//������ ���ݼ��� ���ؼ��� ��
		this.filter = crm.getInstanceCastlingRulesFilter();
	}
}
