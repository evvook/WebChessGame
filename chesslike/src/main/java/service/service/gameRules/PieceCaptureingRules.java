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
		//공격수 이면
		if(type.equalsType(getMovesType())) {
			//비어있는지 아닌지 확인하는 경우
			Position fromPosition = moves.getFrom();
			Position toPosition = moves.getTo();
			Piece fromPositionPiece = fromPosition.getOnPiece();
			Piece toPositionPiece = toPosition.getOnPiece();
			//이동위치에 기물이 없으면
			if(toPositionPiece == null) {
				//순서가 잘못 될 경우 기물 없이 넘어올 수도 있음
				//기물이 없으면 공격이 성립 안 하므로 null리턴
				return null;
			}else {
				
				Camp fromPieceCamp = fromPositionPiece.getCamp();
				Camp toPositionPieceCamp = toPositionPiece.getCamp();
				if(!fromPieceCamp.equals(toPositionPieceCamp)) {
					//다른 진영
					//공격수가 성립하므로 판단을 다음으로 넘김
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
		//같으 진영 기물이거나 기물이 있는 위치를 지나갔다면 수를 리턴하지 않음
		return null;
	}
}
