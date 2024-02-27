package service.chess.gameparts.piece.maker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import service.chess.gameparts.piece.ChessPieceType;
import service.chess.gameparts.piece.moves.ChessMovesType;
import service.chess.gameparts.piece.moves.ChessPieceMovesPattern;
import service.gameparts.Board;
import service.gameparts.piece.moves.PieceMovesPattern;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.relativePosition.PositionEast;
import service.gameparts.piece.relativePosition.PositionNorth;
import service.gameparts.piece.relativePosition.PositionSouth;
import service.gameparts.piece.relativePosition.PositionWest;
import service.gameparts.piece.relativePosition.RelativePosition;

public class RookMaker extends ChessPieceMaker {
	public RookMaker(Board chessBoard) {
		super(chessBoard, ChessPieceType.getInstance(ChessPieceType.Rook));
	}
	
	@Override
	protected List<Moves> getMove() {
		// TODO Auto-generated method stub
		List<RelativePosition> path1 = Arrays.asList(new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard));
		List<RelativePosition> path2 = Arrays.asList(new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard));
		List<RelativePosition> path3 = Arrays.asList(new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard));
		List<RelativePosition> path4 = Arrays.asList(new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard));
		
		PieceMovesPattern movePattern1 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Move), path1);
		PieceMovesPattern movePattern2 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Move), path2);
		PieceMovesPattern movePattern3 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Move), path3);
		PieceMovesPattern movePattern4 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Move), path4);
		
		List<Moves> moves = movePattern1.getMovesInstances();
		moves.addAll(movePattern2.getMovesInstances());
		moves.addAll(movePattern3.getMovesInstances());
		moves.addAll(movePattern4.getMovesInstances());
		
		return moves;
	}

	@Override
	protected List<Moves> getAttack() {
		// TODO Auto-generated method stub
		List<RelativePosition> path1 = Arrays.asList(new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard)
				,new PositionNorth(chessBoard));
		List<RelativePosition> path2 = Arrays.asList(new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard)
				,new PositionEast(chessBoard));
		List<RelativePosition> path3 = Arrays.asList(new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard)
				,new PositionSouth(chessBoard));
		List<RelativePosition> path4 = Arrays.asList(new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard)
				,new PositionWest(chessBoard));
		
		PieceMovesPattern attackPattern1 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Attack), path1);
		PieceMovesPattern attackPattern2 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Attack), path2);
		PieceMovesPattern attackPattern3 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Attack), path3);
		PieceMovesPattern attackPattern4 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Rook),ChessMovesType.getInstance(ChessMovesType.Attack), path4);
		
		List<Moves> moves = attackPattern1.getMovesInstances();
		moves.addAll(attackPattern1.getMovesInstances());
		moves.addAll(attackPattern2.getMovesInstances());
		moves.addAll(attackPattern3.getMovesInstances());
		moves.addAll(attackPattern4.getMovesInstances());
		
		return moves;
	}

	@Override
	protected List<Moves> getCastling() {
		// TODO Auto-generated method stub
		
		return new ArrayList<Moves>();
	}

	@Override
	protected List<Moves> getPromotion() {
		// TODO Auto-generated method stub
		return new ArrayList<Moves>();
	}

	@Override
	protected List<Moves> EnPassant() {
		// TODO Auto-generated method stub
		return new ArrayList<Moves>();
	}

}
