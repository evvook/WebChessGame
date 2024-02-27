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
		//������ �⹰ ���
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
			//���� ��ġ�� �⹰ ����(Ȱ��ȭ)
			attackedPiece.setActive(true);
			isExcuted = false;
		}else {
			//����ó��
		}
	}

	@Override
	public void execute(Board board) {
		// TODO Auto-generated method stub
		execute();
	}

	public void setAttackedPiece() {
		//���� ��ġ�� �⹰ ����(��Ȱ��ȭ)
		attackedPiece = to.getOnPiece();
		attackedPiece.setActive(false);
	}

}
