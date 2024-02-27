package service.gameRules;

import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class PieceCaptureingRules extends Rules{
	
	public PieceCaptureingRules(MovesType movesType) {
		super(movesType);
	}	
	@Override
	public Moves judge(Moves moves){
		
		if(moves == null)
			return null;
		MovesType type = moves.getMovesType();
		//���ݼ� �̸�
		if(type.equalsType(getMovesType())) {
			//����ִ��� �ƴ��� Ȯ���ϴ� ���
			Position fromPosition = moves.getFrom();
			Position toPosition = moves.getTo();
			Piece fromPositionPiece = fromPosition.getOnPiece();
			Piece toPositionPiece = toPosition.getOnPiece();
			//�̵���ġ�� �⹰�� ������
			if(toPositionPiece == null) {
				//������ �߸� �� ��� �⹰ ���� �Ѿ�� ���� ����
				//�⹰�� ������ ������ ���� �� �ϹǷ� null����
				return null;
			}else {
				
				Camp fromPieceCamp = fromPositionPiece.getCamp();
				Camp toPositionPieceCamp = toPositionPiece.getCamp();
				if(!fromPieceCamp.equals(toPositionPieceCamp)) {
					//�ٸ� ����
					//���ݼ��� �����ϹǷ� �Ǵ��� �������� �ѱ�
					try {
						if(hasNext()) {
							Rules nextRules = getNextRules();
							return nextRules.judge(moves);
						}else {
							return moves;
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		//���� ���� �⹰�̰ų� �⹰�� �ִ� ��ġ�� �������ٸ� ���� �������� ����
		return null;
	}
}
