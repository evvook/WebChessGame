package service.chess.gameRules;

import service.chess.gameparts.piece.ChessPieceType;
import service.gameRules.AttackPathRules;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class AttackPathRulesTypeChess extends AttackPathRules{
	
	public AttackPathRulesTypeChess(MovesType moveType) {
		super(moveType);
		// TODO Auto-generated constructor stub
	}

	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		//� ��쵵 �ƴ� ��� �� �������� ����
		//����Ʈ�� ��� �׻� �ʱ�ȭ
		PieceType pieceType = moves.getPieceType();
		if(pieceType.equalsType(ChessPieceType.Knight)) {
			unblankPassedPosition = null;
		}
		return super.judge(moves);
	}
}
