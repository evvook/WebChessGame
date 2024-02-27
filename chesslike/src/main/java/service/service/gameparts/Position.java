package service.gameparts;

import service.gameparts.piece.Piece;
import service.gameparts.piece.PieceMoveObserver;
import service.gameparts.piece.PieceStateObserver;

public class Position implements PieceMoveObserver, PieceStateObserver{
	String letterX;
	String letterY;
	Piece onPiece;
	
	public Position(String letterX, String letterY) {
		// TODO Auto-generated constructor stub
		this.letterX = letterX;
		this.letterY = letterY;
	}

	public String getLetterX() {
		// TODO Auto-generated method stub
		return this.letterX;
	}

	public String getLetterY() {
		// TODO Auto-generated method stub
		return this.letterY;
	}
	
	public String getNotation() {
		return letterX+letterY;
	}
	
	public void setOnPiece(Piece piece) {
		// TODO Auto-generated method stub
		onPiece = piece;
	}

	public Piece getOnPiece() {
		return onPiece;
	}

	@Override
	public void notifyPiecePosition(Piece piece) {
		// TODO Auto-generated method stub
		//�⹰�� �̹� �ִ� ��ġ�̸� �⹰�� ���(Piece�� From)
		if(piece.equals(onPiece)) {
			onPiece = null;
		}
		//��ġ�� ��ġ�ϸ� �⹰�� ����(Piece�� To)
		if(this.equals(piece.getPosition())) {
			onPiece = piece;
		}
	}

	@Override
	public void notifyPieceState(Piece piece) {
		// TODO Auto-generated method stub
		this.onPiece = piece;
	}
}
