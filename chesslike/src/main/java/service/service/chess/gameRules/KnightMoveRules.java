package service.chess.gameRules;

import service.chess.gameparts.piece.ChessPieceType;
import service.gameRules.Rules;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class KnightMoveRules extends Rules{
	
	public KnightMoveRules(MovesType movesType) {
		super(movesType);
	}

	@Override
	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		if(moves == null)
			return null;
		MovesType moveType = moves.getMovesType();
		PieceType pieceType = moves.getPieceType();
		//이동의 경우
		if(pieceType.equalsType(ChessPieceType.Knight)) {
			//System.out.println("나이트 룰 적용!");
			if(getMovesType().equalsType(moveType)) {
				if(isBeginning(moves)) {
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
		if(hasNext()) {
			Rules nextRules = getNextRules();
			return nextRules.judge(moves);
		}
		return moves;
	}
	
}
