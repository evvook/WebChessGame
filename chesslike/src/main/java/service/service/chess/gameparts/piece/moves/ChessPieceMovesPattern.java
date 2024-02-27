package service.chess.gameparts.piece.moves;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import service.chess.gameparts.piece.ChessPiece;
import service.chess.gameparts.piece.ChessPieceType;
import service.gameException.NotExistsMoveToException;
import service.gameException.NotOnBoardException;
import service.gameparts.Position;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.PieceMovesPattern;
import service.gameparts.piece.moves.Attack;
import service.gameparts.piece.moves.Move;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;
import service.gameparts.piece.relativePosition.RelativePosition;

public class ChessPieceMovesPattern extends PieceMovesPattern{
	
	public ChessPieceMovesPattern(PieceType pieceType, MovesType movesType, List<RelativePosition> relativePositons) {
		super(pieceType,movesType,relativePositons);
	}

	public ChessPieceType getPieceType() {
		// TODO Auto-generated method stub
		return (ChessPieceType)pieceType;
	}
	
	public ChessMovesType getMoveType() {
		// TODO Auto-generated method stub
		return (ChessMovesType)moveType;
	}
	
	public List<Moves> getMovesInstances() {
		List<Moves> moves = new ArrayList<Moves>();
		for(int i=0;i<relativePositons.size();i++) {
			if(moveType.equalsType(ChessMovesType.Move)) {
				moves.add(new Move(this));
			}
			if(moveType.equalsType(ChessMovesType.Attack)) {
				moves.add(new Attack(this));
			}
			if(moveType.equalsType(ChessMovesType.Castling)) {
				moves.add(new Castling(this));
			}
		}
		return moves;
	}
	@Override
	public Position makeMovesToPosition(Moves moves) {
		// TODO Auto-generated method stub
		Position moveTo = moves.getFrom();//초기화
		
		Iterator<RelativePosition> ri = relativePositons.iterator();
		while(ri.hasNext()) {
			RelativePosition rp = ri.next();
			try {
				moveTo = rp.getNext(moveTo);
				if(moveTo == null) {
					throw new NotExistsMoveToException();
				}
				try {
					MovesType movesType = moves.getMovesType();
					Position position = moves.getFrom();
					ChessPiece chessPiece = (ChessPiece)position.getOnPiece();
					//없는 것만 만듬
					if(!chessPiece.existMoves(movesType, moveTo)) {
						return moveTo;
					}
				}catch (Exception e) {
					//System.out.println(moves.getFrom().getLetter());
					e.printStackTrace();
				}
			} catch (NotOnBoardException e) {
				//보드 위에 없는 경우 무시
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
}
