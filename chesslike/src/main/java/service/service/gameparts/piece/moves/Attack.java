package service.gameparts.piece.moves;

import service.gameparts.Board;
import service.gameparts.piece.Piece;

public class Attack extends Moves{
	
	public Attack(PieceMovesPattern pattern) {
		// TODO Auto-generated constructor stub
		this.pattern = pattern;
		this.movesType = pattern.getMoveType();
		this.pieceType = pattern.getPieceType();
	}

	private Piece attackedPiece;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//System.out.println("attack to "+to.getNotation()+"! "+from.getOnPiece().getRankName()+" "+from.getOnPiece().getCamp().isActive());
		//공격할 기물 등록
		if(to.getOnPiece() == null) {
			//throw new NotExistsAttacedPieceException();
		}
		setAttackedPiece();
		undoPosition = from;
		Piece piece = getPiece();
		setPiecePosition(piece,to);
		isExcuted = true;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if(isExcuted) {
			to = undoPosition;
			//System.out.println("undo attack to "+to.getNotation()+"! "+from.getOnPiece().getRankName()+" "+from.getOnPiece().getCamp().isActive());
			Piece piece = getPiece();
			setPiecePosition(piece,to);
			//공격 위치의 기물 제거(활성화)
			attackedPiece.setActive(true);
			isExcuted = false;
		}else {
			//예외처리
		}
	}

	@Override
	public void execute(Board board) {
		// TODO Auto-generated method stub
		execute();
	}

	public void setAttackedPiece() {
		//공격 위치의 기물 제거(비활성화)
		attackedPiece = to.getOnPiece();
		attackedPiece.setActive(false);
	}

}
