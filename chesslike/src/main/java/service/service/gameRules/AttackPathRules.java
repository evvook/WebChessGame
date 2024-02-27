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
		//�̵��� ���
		if(getMovesType().equalsType(moveType)) {
			//������ġ�� ��� �ʱ�ȭ
			if(isBeginning(moves)) {
				unblankPassedPosition = null;
			}
			//������ ��ġ�� �ִٸ� ������ �� ���͸���
			if(unblankPassedPosition != null) {
				return null;
			}else {
				//������ ��ġ�� �������� �ʾҰ�
				//������� �ʴٸ�
				if(moves.getTo().getOnPiece() != null) {
					//�⹰�� �����ϴ� ��ġ �����ϰ�
					unblankPassedPosition = moves.getTo();
					//�Ǵ��� �ѱ��.
					if(hasNext()) {
						Rules nextRules = getNextRules();
						return nextRules.judge(moves);
					}else {
						return moves;
					}
				}
			}
		}
		//� ��쵵 �ƴ� ��� �� �������� ����
		return null;
	}
}
