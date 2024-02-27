package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import chess.game.ChessGameManager;
import chess.game.ChessGameSetter;
import game.GameManager;

class testCastlingCheckState1 {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		GameManager gm = new ChessGameManager();
		gm.setGame(new ChessGameSetter());
		ChessGameManager cgm = (ChessGameManager)gm;
		try {
			System.out.println("\n");
			gm.selectPiece("a1");
			cgm.showMovesTo();
			gm.unselect();
			System.out.println("\n");
			
			gm.selectPiece("e1");
			cgm.showMovesTo();
			cgm.move("e2");
			System.out.println("\n");
			
			gm.selectPiece("c3");
			cgm.showMovesTo();
			cgm.move("g3");
			System.out.println("\n");
			
			gm.selectPiece("e2");
			cgm.showMovesTo();
			cgm.move("e1");
			System.out.println("\n");
			
			gm.selectPiece("g3");
			cgm.showMovesTo();
			cgm.move("c3");
			System.out.println("\n");
			
			gm.selectPiece("e1");
			cgm.showMovesTo();
			
			System.out.println("\nactive camp");
			gm.showActiveCampUnitsInfo();
			System.out.println("\nopposite camp");
			gm.showOppositeCampUnitsInfo();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			System.out.println("\nall units on board");
			gm.showAllUnitsInfo();
		}
	}

}
