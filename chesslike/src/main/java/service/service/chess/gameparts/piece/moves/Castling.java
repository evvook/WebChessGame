package service.chess.gameparts.piece.moves;

import service.chess.gameparts.piece.ChessPieceType;
import service.game.GameStateBroker;
import service.gameException.CastlingRookException;
import service.gameException.NotExistisPieceException;
import service.gameException.NotOnBoardException;
import service.gameparts.Board;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class Castling extends Moves{
	private Piece rook;
	private Position rookPosition;
	private Position rookCastlingPosition;
	private ChessMovesType moveType;
	private ChessPieceType pieceType;
	
	public Castling(ChessPieceMovesPattern pattern) {
		// TODO Auto-generated constructor stub
		this.pattern = pattern;
		this.moveType = pattern.getMoveType();
		this.pieceType = pattern.getPieceType();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//수를 둘 기물 선택
		undoPosition = from;
		Piece piece = getPiece();
		setPiecePosition(piece,to);
		setPiecePosition(rook, rookCastlingPosition);
		isExcuted = true;
	}
	
	public void execute(Board chessBoard) {
		try {
			setRookPositions(chessBoard);
			try {
				setRook();
			}catch(NotExistisPieceException e) {
				throw new CastlingRookException(rookPosition.getNotation());
			}
			//룩이 있으면 옵저버 등록
			registRookGameStateSubject();
			execute();
		} catch (NotOnBoardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(CastlingRookException e) {
			System.out.println(e.getMessage());
		}
	}

	private void registRookGameStateSubject() {
		// TODO Auto-generated method stub
		Piece piece = getPiece();
		GameStateBroker broker = piece.getGameStateBroker();
		if(broker != null) {
			broker.registGameStateSub(rook);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if(isExcuted) {
			//룩 옵저버 등록
			registRookGameStateSubject();
			//undo실행
			to = undoPosition;
			Piece piece = getPiece();
			setPiecePosition(piece,to);
			setPiecePosition(rook, rookPosition);
			isExcuted = false;
		}else {
			
		}
	}
	
	private void setRookPositions(Board chessBoard) throws NotOnBoardException {
		String axisY = from.getLetterY();
		String fromX = from.getLetterX();
		String toX = to.getLetterX();
		//룩은 고정위치에 있기 때문에 방향만 알면 가져올 수 있다.
		int numFromX = (int)fromX.charAt(0);
		int numToX = (int)toX.charAt(0);
		
		if(numToX > numFromX) {
			//"h"+axisY 킹사이드 룩 포지션
			rookPosition = chessBoard.getPosition("h"+axisY);
			rookCastlingPosition = chessBoard.getPosition("f"+axisY);
		}if(numToX < numFromX) {
			//"a"+axisY 퀸사이드 룩 포지션
			rookPosition = chessBoard.getPosition("a"+axisY);
			rookCastlingPosition = chessBoard.getPosition("d"+axisY);
		}
	}
	
	private void setRook() throws NotExistisPieceException{
		if(rookPosition.getOnPiece() == null) {
			throw new NotExistisPieceException();
		}
		rook = rookPosition.getOnPiece();
	}

//	@Override
//	public boolean equalsType(Object type) {
//		// TODO Auto-generated method stub
//		if(moveType.getMovesType().equals(type)) {
//			return true;
//		}else if(pieceType.getPieceType().equals(type)) {
//			return true;
//		}
//		return false;
//	}

	public MovesType getMovesType() {
		// TODO Auto-generated method stub
		return moveType;
	}

	public PieceType getPieceType() {
		// TODO Auto-generated method stub
		return pieceType;
	}

//	@Override
//	public boolean equalsType(PieceType type) {
//		// TODO Auto-generated method stub
//		return pieceType.equalsType(type.getPieceType());
//	}
//
//	@Override
//	public boolean equalsType(MovesType type) {
//		// TODO Auto-generated method stub
//		return moveType.equalsType(type.getMovesType());
//	}
}
