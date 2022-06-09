package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import boardGame.game.GameManager;
import boardGame.game.chess.ChessGameSetter;

class testGameCheckState2 {

	@Test
	void test() {
		GameManager manager = new GameManager(ChessGameSetter.setGame());
		assertEquals("WHITE", manager.getTurnedCampName());
		
		//assertFalse(manager.isCheck());
		
		showList(manager.getCampUnit());
		manager.selectPiecePosition("b1");
		showList(manager.getSelectedPieceMoves());
		manager.move("g6");
		showList(manager.getOppositeCampUnit());
		
		//assertEquals("BLACK", manager.getTurnedCampName());
		//assertTrue(manager.isCheck());
		
		assertEquals("BLACK", manager.getTurnedCampName());
		showList(manager.getCampUnit());
		manager.selectPiecePosition("a8");
		showList(manager.getSelectedPieceMoves());
		manager.move("a2");
		showList(manager.getCampUnit());
		showList(manager.getOppositeCampUnit());
	}
	
	public void showList(List<String> list) {
		Iterator<String> il = list.iterator();
		while(il.hasNext()) {
			System.out.println(il.next());
		}
	}

}
