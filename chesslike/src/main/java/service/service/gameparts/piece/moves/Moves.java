package service.gameparts.piece.moves;

import service.gameparts.Board;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.PieceMoveObserver;
import service.gameparts.piece.PieceType;

public abstract class Moves implements PieceMoveObserver{
	protected PieceMovesPattern pattern;
	protected Position from;
	protected Position to;
	protected Position undoPosition;
	protected boolean isExcuted;
	
	protected MovesType movesType;
	protected PieceType pieceType;
	
	public Position getFrom() {
		// TODO Auto-generated method stub
		return from;
	}
	public Position getTo() {
		return to;
	}
	public void setTo() {
		to = pattern.makeMovesToPosition(this);
		isExcuted = false;
	}
	public abstract void execute() throws Exception;
	public abstract void execute(Board board);
	public abstract void undo();
	
	protected Piece getPiece() {
		return from.getOnPiece();
	}
	
	protected void setPiecePosition(Piece piece,Position to) {
		//���� �� �⹰�� ������ ��ġ ���
		piece.registMoveObserver(to);
		piece.registStateObserver(to);
		//��ġ �̵� ��û
		piece.setPosition(to);
	}
	
	public MovesType getMovesType() {
		// TODO Auto-generated method stub
		return movesType;
	}

	public PieceType getPieceType() {
		// TODO Auto-generated method stub
		return pieceType;
	}
	
	@Override
	public void notifyPiecePosition(Piece piece) {
		//�⹰ ��ġ�� ���ϸ�, ������ from, to�� �ʱ�ȭ���ش�.
		from = piece.getPosition();
		to = null;
	}
}
