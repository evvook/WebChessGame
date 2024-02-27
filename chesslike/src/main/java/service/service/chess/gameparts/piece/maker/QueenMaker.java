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
import service.gameparts.piece.relativePosition.PositionNorthEast;
import service.gameparts.piece.relativePosition.PositionNorthWest;
import service.gameparts.piece.relativePosition.PositionSouth;
import service.gameparts.piece.relativePosition.PositionSouthEast;
import service.gameparts.piece.relativePosition.PositionSouthWest;
import service.gameparts.piece.relativePosition.PositionWest;
import service.gameparts.piece.relativePosition.RelativePosition;

public class QueenMaker extends ChessPieceMaker {
	public QueenMaker(Board chessBoard) {
		super(chessBoard, ChessPieceType.getInstance(ChessPieceType.Queen));
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
		List<RelativePosition> path5 = Arrays.asList(new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard));
		List<RelativePosition> path6 = Arrays.asList(new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard));
		List<RelativePosition> path7 = Arrays.asList(new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard));
		List<RelativePosition> path8 = Arrays.asList(new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard));
		
		
		PieceMovesPattern movePattern1 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path1);
		PieceMovesPattern movePattern2 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path2);
		PieceMovesPattern movePattern3 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path3);
		PieceMovesPattern movePattern4 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path4);
		PieceMovesPattern movePattern5 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path5);
		PieceMovesPattern movePattern6 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path6);
		PieceMovesPattern movePattern7 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path7);
		PieceMovesPattern movePattern8 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Move), path8);
		
		List<Moves> moves = movePattern1.getMovesInstances();
		moves.addAll(movePattern2.getMovesInstances());
		moves.addAll(movePattern3.getMovesInstances());
		moves.addAll(movePattern4.getMovesInstances());
		moves.addAll(movePattern5.getMovesInstances());
		moves.addAll(movePattern6.getMovesInstances());
		moves.addAll(movePattern7.getMovesInstances());
		moves.addAll(movePattern8.getMovesInstances());
		
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
		List<RelativePosition> path5 = Arrays.asList(new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard)
				,new PositionNorthWest(chessBoard));
		List<RelativePosition> path6 = Arrays.asList(new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard)
				,new PositionNorthEast(chessBoard));
		List<RelativePosition> path7 = Arrays.asList(new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard)
				,new PositionSouthEast(chessBoard));
		List<RelativePosition> path8 = Arrays.asList(new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard)
				,new PositionSouthWest(chessBoard));
		
		PieceMovesPattern attackPattern1 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path1);
		PieceMovesPattern attackPattern2 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path2);
		PieceMovesPattern attackPattern3 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path3);
		PieceMovesPattern attackPattern4 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path4);
		PieceMovesPattern attackPattern5 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path5);
		PieceMovesPattern attackPattern6 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path6);
		PieceMovesPattern attackPattern7 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path7);
		PieceMovesPattern attackPattern8 = new ChessPieceMovesPattern(ChessPieceType.getInstance(ChessPieceType.Queen),ChessMovesType.getInstance(ChessMovesType.Attack), path8);
		
		List<Moves> moves = attackPattern1.getMovesInstances();
		moves.addAll(attackPattern1.getMovesInstances());
		moves.addAll(attackPattern2.getMovesInstances());
		moves.addAll(attackPattern3.getMovesInstances());
		moves.addAll(attackPattern4.getMovesInstances());
		moves.addAll(attackPattern5.getMovesInstances());
		moves.addAll(attackPattern6.getMovesInstances());
		moves.addAll(attackPattern7.getMovesInstances());
		moves.addAll(attackPattern8.getMovesInstances());
		
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
