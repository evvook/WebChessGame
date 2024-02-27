package service.chess.gameparts.piece.maker;

import java.util.ArrayList;
import java.util.List;

import service.chess.gameparts.piece.ChessPiece;
import service.gameparts.Board;
import service.gameparts.piece.PieceMaker;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.Moves;

public abstract class ChessPieceMaker implements PieceMaker {
	protected Board chessBoard;
	protected PieceType pieceType;

	protected ChessPieceMaker(Board chessBoard, PieceType pieceType) {
		// TODO Auto-generated constructor stub
		this.chessBoard = chessBoard;
		this.pieceType = pieceType;
	}
	
	//템플릿 메서드 패턴 사용
	@Override
	public ChessPiece makePiece() {
		// TODO Auto-generated method stub
		List<Moves> piecesMoves = new ArrayList<Moves>();
		piecesMoves.addAll(getMove());
		piecesMoves.addAll(getAttack());
		piecesMoves.addAll(getCastling());
		piecesMoves.addAll(getPromotion());
		piecesMoves.addAll(EnPassant());
		
		return new ChessPiece(pieceType,piecesMoves);
	}
	
	abstract protected List<Moves> getMove();
	abstract protected List<Moves> getAttack();
	abstract protected List<Moves> getCastling();
	abstract protected List<Moves> getPromotion();
	abstract protected List<Moves> EnPassant();
}
