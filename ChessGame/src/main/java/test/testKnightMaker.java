package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.Board;
import chess.Camp;
import chess.Intersection;
import chess.Move;
import chess.Piece;
import chess.PieceMaker;

class testKnightMaker {

	@Test
	void test() {
		String[] rank = {"a","b","c","d","e","f","g","h"};
		String[] file = {"1","2","3","4","5","6","7","8"};
		Board chessBoard = new Board(Arrays.asList(rank),Arrays.asList(file));
		
		Camp white = new Camp();
		Camp black = new Camp();
		
		Piece knight = PieceMaker.makeKnight(chessBoard,white);
		Piece knight2 = PieceMaker.makeKnight(chessBoard,black);
		
		Intersection tempPosition = chessBoard.getIntersection("b2");
		Intersection tempPosition2 = chessBoard.getIntersection("c4");
		tempPosition.setPiece(knight);
		tempPosition2.setPiece(knight2);
		
		List<Move> knightMoves =  tempPosition.getPiece().getMoves(tempPosition);
		
		for(Move m:knightMoves) {
			System.out.println(m.showTo());
		}
	}

}
