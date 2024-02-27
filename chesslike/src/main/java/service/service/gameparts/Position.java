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
		//기물이 이미 있던 위치이면 기물을 비움(Piece의 From)
		if(piece.equals(onPiece)) {
			onPiece = null;
		}
		//위치와 일치하면 기물을 설정(Piece의 To)
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
