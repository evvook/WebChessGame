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
import service.gameparts.piece.relativePosition.PositionNorthEast;
import service.gameparts.piece.relativePosition.PositionNorthWest;
import service.gameparts.piece.relativePosition.PositionSouthEast;
import service.gameparts.piece.relativePosition.PositionSouthWest;
import service.gameparts.piece.relativePosition.RelativePosition;

public class BishopMaker extends ChessPieceMaker {
	public BishopMaker(Board chessBoard) {
		super(chessBoard, ChessPieceType.getInstance(ChessPieceType.Bishop));
	}
	
	@Override
	protected List<Moves> getMove() {
		// TODO Auto-generated method stub
		List<RelativePosition> path1 = Arrays.asList(new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard));
		List<RelativePosition> path2 = Arrays.asList(new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard));
		List<RelativePosition> path3 = Arrays.asList(new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard));
		List<RelativePosition> path4 = Arrays.asList(new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard));
		
		PieceMovesPattern movePattern1 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Move), path1);
		PieceMovesPattern movePattern2 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Move), path2);
		PieceMovesPattern movePattern3 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Move), path3);
		PieceMovesPattern movePattern4 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Move), path4);
		
		List<Moves> moves = movePattern1.getMovesInstances();
		moves.addAll(movePattern2.getMovesInstances());
		moves.addAll(movePattern3.getMovesInstances());
		moves.addAll(movePattern4.getMovesInstances());
		
		return moves;
	}

	@Override
	protected List<Moves> getAttack() {
		// TODO Auto-generated method stub
		List<RelativePosition> path1 = Arrays.asList(new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard));
		List<RelativePosition> path2 = Arrays.asList(new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard));
		List<RelativePosition> path3 = Arrays.asList(new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard));
		List<RelativePosition> path4 = Arrays.asList(new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard));
		
		PieceMovesPattern attackPattern1 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Attack), path1);
		PieceMovesPattern attackPattern2 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Attack), path2);
		PieceMovesPattern attackPattern3 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Attack), path3);
		PieceMovesPattern attackPattern4 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Bishop),ChessMovesType.getInstance(ChessMovesType.Attack), path4);
		
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
