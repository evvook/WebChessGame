package boardGame.game.chess.pieceMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.cursor.CursorMaker;
import boardGame.cursor.LowFileCursor;
import boardGame.cursor.LowRankCursor;
import boardGame.cursor.Quadrant1stCursor;
import boardGame.cursor.Quadrant2ndCursor;
import boardGame.cursor.Quadrant3rdCursor;
import boardGame.cursor.Quadrant4thCursor;
import boardGame.cursor.UpFileCursor;
import boardGame.cursor.UpRankCursor;
import boardGame.game.Capability;
import boardGame.game.movement.PointTo;
import boardGame.game.movement.StraightOn;
import boardGame.move.AttackOnMaker;
import boardGame.move.MoveMaker;
import boardGame.move.MoveOnMaker;
import boardGame.move.chess.CastlingMaker;
import boardGame.move.chess.KnightMoveOnMaker;
import boardGame.movement.Movement;
import boardGame.partsOfGame.Board;
import boardGame.partsOfGame.Camp;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.Position;
import boardGame.partsOfGame.chess.ChessPiece;

public class ChessPieceMakerOld {
	public static Piece makeKnight(Camp onSide,Position onPosition,CursorMaker cursorMaker) {
		
		Cursor[] move1 = {cursorMaker.makeUpFileCursor(),cursorMaker.makeQuadrant2ndCursor()};
		Cursor[] move2 = {cursorMaker.makeUpFileCursor(),cursorMaker.makeQuadrant1stCursor()};
		Cursor[] move3 = {cursorMaker.makeUpRankCursor(),cursorMaker.makeQuadrant1stCursor()};
		Cursor[] move4 = {cursorMaker.makeUpRankCursor(),cursorMaker.makeQuadrant4thCursor()};
		Cursor[] move5 = {cursorMaker.makeLowFileCursor(),cursorMaker.makeQuadrant4thCursor()};
		Cursor[] move6 = {cursorMaker.makeLowFileCursor(),cursorMaker.makeQuadrant3rdCursor()};
		Cursor[] move7 = {cursorMaker.makeLowRankCursor(),cursorMaker.makeQuadrant3rdCursor()};
		Cursor[] move8 = {cursorMaker.makeLowRankCursor(),cursorMaker.makeQuadrant2ndCursor()};
		
		Movement movement = new PointTo();
		MoveMaker moveOn = new KnightMoveOnMaker();
		List<Capability> knightCapabilities = new ArrayList<Capability>();
		knightCapabilities.add(new Capability(Arrays.asList(move1), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move2), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move3), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move4), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move5), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move6), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move7), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move8), movement, moveOn));
		
		MoveMaker attackOn = new AttackOnMaker(onSide);
		knightCapabilities.add(new Capability(Arrays.asList(move1), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move2), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move3), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move4), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move5), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move6), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move7), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move8), movement, attackOn));
		
		ChessPiece knight = new ChessPiece("KNIGHT",knightCapabilities,onPosition);
		onSide.setPiece(knight);
		
		return knight;
	}
	
	public static Piece makeRook(Camp onSide,Position onPosition,CursorMaker cursorMaker) {
		ChessPiece rook = null;
		
		Cursor[] move1 = {cursorMaker.makeUpRankCursor()};
		Cursor[] move2 = {cursorMaker.makeLowRankCursor()};
		Cursor[] move3 = {cursorMaker.makeUpFileCursor()};
		Cursor[] move4 = {cursorMaker.makeLowFileCursor()};
		
		Movement movement = new StraightOn();
		MoveMaker moveOn = new MoveOnMaker();
		List<Capability> rookCapabilities = new ArrayList<Capability>();
		rookCapabilities.add(new Capability(Arrays.asList(move1), movement, moveOn));
		rookCapabilities.add(new Capability(Arrays.asList(move2), movement, moveOn));
		rookCapabilities.add(new Capability(Arrays.asList(move3), movement, moveOn));
		rookCapabilities.add(new Capability(Arrays.asList(move4), movement, moveOn));
		
		MoveMaker attackOn = new AttackOnMaker(onSide);
		rookCapabilities.add(new Capability(Arrays.asList(move1), movement, attackOn));
		rookCapabilities.add(new Capability(Arrays.asList(move2), movement, attackOn));
		rookCapabilities.add(new Capability(Arrays.asList(move3), movement, attackOn));
		rookCapabilities.add(new Capability(Arrays.asList(move4), movement, attackOn));
		
		rook = new ChessPiece("ROOK",rookCapabilities,onPosition);
		onSide.setPiece(rook);
		
		return rook;
	}
	
	public static Piece makeKing(Camp onSide,Position onPosition,CursorMaker cursorMaker) {
		ChessPiece king = null;
		
		Cursor[] move1 = {cursorMaker.makeUpFileCursor()};
		Cursor[] move2 = {cursorMaker.makeQuadrant1stCursor()};
		Cursor[] move3 = {cursorMaker.makeUpRankCursor()};
		Cursor[] move4 = {cursorMaker.makeQuadrant4thCursor()};
		Cursor[] move5 = {cursorMaker.makeLowFileCursor()};
		Cursor[] move6 = {cursorMaker.makeQuadrant3rdCursor()};
		Cursor[] move7 = {cursorMaker.makeLowRankCursor()};
		Cursor[] move8 = {cursorMaker.makeQuadrant2ndCursor()};
		
		Movement movement = new PointTo();
		
		MoveMaker moveOn = new MoveOnMaker();
		List<Capability> kingCapabilities = new ArrayList<Capability>();
		kingCapabilities.add(new Capability(Arrays.asList(move1), movement, moveOn));
		kingCapabilities.add(new Capability(Arrays.asList(move2), movement, moveOn));
		kingCapabilities.add(new Capability(Arrays.asList(move3), movement, moveOn));
		kingCapabilities.add(new Capability(Arrays.asList(move4), movement, moveOn));
		kingCapabilities.add(new Capability(Arrays.asList(move5), movement, moveOn));
		kingCapabilities.add(new Capability(Arrays.asList(move6), movement, moveOn));
		kingCapabilities.add(new Capability(Arrays.asList(move7), movement, moveOn));
		kingCapabilities.add(new Capability(Arrays.asList(move8), movement, moveOn));
		
		MoveMaker attackOn = new AttackOnMaker(onSide);
		kingCapabilities.add(new Capability(Arrays.asList(move1), movement, attackOn));
		kingCapabilities.add(new Capability(Arrays.asList(move2), movement, attackOn));
		kingCapabilities.add(new Capability(Arrays.asList(move3), movement, attackOn));
		kingCapabilities.add(new Capability(Arrays.asList(move4), movement, attackOn));
		kingCapabilities.add(new Capability(Arrays.asList(move5), movement, attackOn));
		kingCapabilities.add(new Capability(Arrays.asList(move6), movement, attackOn));
		kingCapabilities.add(new Capability(Arrays.asList(move7), movement, attackOn));
		kingCapabilities.add(new Capability(Arrays.asList(move8), movement, attackOn));
		
		Cursor castlingSideCursor = null;
		if("WHITE".equals(onSide.getName())) {
			castlingSideCursor = cursorMaker.makeUpRankCursor();
			Cursor[] whiteKingSideCastling = {castlingSideCursor,castlingSideCursor};
			MoveMaker kingSideCastling = new CastlingMaker(onSide,castlingSideCursor,cursorMaker.makeOppositeCursor(castlingSideCursor));
			kingCapabilities.add(new Capability(Arrays.asList(whiteKingSideCastling), movement, kingSideCastling));
			
			castlingSideCursor = cursorMaker.makeLowRankCursor();
			Cursor[] whiteQueenSideCastling = {castlingSideCursor,castlingSideCursor};
			MoveMaker QueenSideCastling = new CastlingMaker(onSide,castlingSideCursor,cursorMaker.makeOppositeCursor(castlingSideCursor));
			kingCapabilities.add(new Capability(Arrays.asList(whiteQueenSideCastling), movement, QueenSideCastling));
		}
		if("BLACK".equals(onSide.getName())) {
			castlingSideCursor = cursorMaker.makeLowRankCursor();
			Cursor[] blackKingSideCastling = {castlingSideCursor,castlingSideCursor};
			MoveMaker kingSideCastling = new CastlingMaker(onSide,castlingSideCursor,cursorMaker.makeOppositeCursor(castlingSideCursor));
			kingCapabilities.add(new Capability(Arrays.asList(blackKingSideCastling), movement, kingSideCastling));

			castlingSideCursor = cursorMaker.makeUpRankCursor();
			Cursor[] blackQueenSideCastling = {castlingSideCursor,castlingSideCursor};
			MoveMaker QueenSideCastling = new CastlingMaker(onSide,castlingSideCursor,cursorMaker.makeOppositeCursor(castlingSideCursor));
			kingCapabilities.add(new Capability(Arrays.asList(blackQueenSideCastling), movement, QueenSideCastling));
		}
		
		king = new ChessPiece("KING",kingCapabilities,onPosition);
		onSide.setPiece(king);
		
		return king;
	}
}
