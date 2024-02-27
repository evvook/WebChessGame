package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.gameparts.piece.moves.ChessMovesType;
import gameparts.piece.moves.MovesType;

class testChessMoveTypeEquals {

	@Test
	void test() {
		MovesType attack = ChessMovesType.getInstance(ChessMovesType.Attack);
		
		assertTrue(attack.equalsType(ChessMovesType.Attack));
	}

}
