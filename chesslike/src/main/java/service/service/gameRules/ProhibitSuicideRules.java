package service.gameRules;

import service.gameparts.Board;
import service.gameparts.Camp;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

//�ڻ� �� ����
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
			//����ȭ�� ���� ����
			//�˻縦 ���� �׽�Ʈ ���� �̰ų� ������ Ȱ��ȭ�� ���� ��� �ƴѰ�� �˻� �� ��
			if(!camp.isTestState() && !camp.isActive()) {
				return moves;
			}
			//�� ����
			moves.execute(board);
			//���� ���� ���ؼ� üũ ���� Ȯ��
			boolean isCheck = rm.testCheck(camp);
			//�� �������
			moves.undo();
			//üũ�� �ƴϸ�
			if(!isCheck) {
				if(hasNext()) {
					Rules nextRules = getNextRules();
					return nextRules.judge(moves);
				}else {
					return moves;
				}
			}
		}
		//üũ ���� �ִٸ� �� ����
		return null;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
