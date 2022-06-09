package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import boardGame.game.GameManager;
import boardGame.game.chess.ChessGameSetter;

class testGameSM {

	@Test
	void test() {
		GameManager manager = new GameManager(ChessGameSetter.setGame());
		assertEquals("WHITE", manager.getTurnedCampName());
		showList(manager.getCampUnit());
		manager.selectPiecePosition("e1");
		showList(manager.getSelectedPieceMoves());
		manager.move("d2");
		showList(manager.getOppositeCampUnit());
		
		assertNotEquals("WHITE", manager.getTurnedCampName());
		showList(manager.getCampUnit());
		manager.selectPiecePosition("a8");
		showList(manager.getSelectedPieceMoves());
		manager.move("d8");
		showList(manager.getOppositeCampUnit());
		
		assertEquals("WHITE", manager.getTurnedCampName());
		showList(manager.getCampUnit());
		manager.selectPiecePosition("d2");
		showList(manager.getSelectedPieceMoves());
		manager.move("c2");
		showList(manager.getOppositeCampUnit());
		
		assertNotEquals("WHITE", manager.getTurnedCampName());
		showList(manager.getCampUnit());
		manager.selectPiecePosition("d8");
		showList(manager.getSelectedPieceMoves());
		manager.move("d3");
		showList(manager.getOppositeCampUnit());
		
		assertEquals("WHITE", manager.getTurnedCampName());
		showList(manager.getCampUnit());
//		manager.selectPiecePosition("a1");
//		showList(manager.getSelectedPieceMoves());
//		manager.move("c1");
//		showList(manager.getOppositeCampUnit());
	}
	
	public void showList(List<String> list) {
		Iterator<String> il = list.iterator();
		while(il.hasNext()) {
			System.out.println(il.next());
		}
	}

}
