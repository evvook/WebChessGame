package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.Board;
import chess.Capability;
import chess.Cursor;
import chess.Intersection;
import chess.Move;
import chess.MoveMaker;
import chess.MoveOnMaker;
import chess.PointTo;
import chess.Quadrant1stCursor;
import chess.UpRankCursor;
import chess.Movement;
import chess.Piece;

class testMakePiece {

	@Test
	void test() {
		String[] rank = {"a","b","c","d","e","f","g","h"};
		String[] file = {"1","2","3","4","5","6","7","8"};
		Board chessBoard = new Board(Arrays.asList(rank),Arrays.asList(file));
		
		//나이트를 만들어보자
		Cursor[] cursors = {new UpRankCursor(chessBoard),new Quadrant1stCursor(chessBoard)};
		Movement movement = new PointTo();
		MoveMaker moveMake = new MoveOnMaker();
		List<Capability> knightCapabilities = new ArrayList<Capability>();
		knightCapabilities.add(new Capability(Arrays.asList(cursors), movement, moveMake));
		
		Piece knight = new Piece("KNIGHT",knightCapabilities);
		
		Intersection tempPosition = chessBoard.getIntersection("a1");
		List<Move> knightMoves =  knight.getMoves(tempPosition);
		
		for(Move m:knightMoves) {
			System.out.println(m.showTo());
		}
	}

}
