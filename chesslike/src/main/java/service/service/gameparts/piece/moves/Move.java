package service.gameparts.piece.moves;

import service.gameparts.Board;
import service.gameparts.piece.Piece;

public class Move extends Moves{
	
	public Move(PieceMovesPattern pattern) {
		// TODO Auto-generated constructor stub
		this.pattern = pattern;
		this.movesType = pattern.getMoveType();
		this.pieceType = pattern.getPieceType();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//System.out.println("move to "+to.getNotation()+"! "+from.getOnPiece().getRankName()+" "+from.getOnPiece().getCamp().isActive());
		undoPosition = from;
		//수를 둘 기물 선택
		Piece piece = getPiece();
		setPiecePosition(piece,to);
		isExcuted = true;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if(isExcuted) {
			to = undoPosition;
			//System.out.println("undo move to "+to.getNotation()+"! "+from.getOnPiece().getRankName()+" "+from.getOnPiece().getCamp().isActive());
			Piece piece = getPiece();
			setPiecePosition(piece,to);
			isExcuted = false;
		}else {
			
		}
	}

	@Override
	public void execute(Board board) {
		// TODO Auto-generated method stub
		execute();
	}
}
