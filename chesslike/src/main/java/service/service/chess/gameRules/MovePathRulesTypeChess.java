package service.chess.gameRules;

import service.chess.gameparts.piece.ChessPieceType;
import service.gameRules.MovePathRules;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class MovePathRulesTypeChess extends MovePathRules{
	
	public MovePathRulesTypeChess(MovesType moveType) {
		super(moveType);
		// TODO Auto-generated constructor stub
	}

	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		//어떤 경우도 아닌 경우 수 생성하지 않음
		//나이트인 경우 항상 초기화
		PieceType pieceType = moves.getPieceType();
		if(pieceType.equalsType(ChessPieceType.Knight)) {
			unblankPassedPosition = null;
		}
		return super.judge(moves);
	}
}
