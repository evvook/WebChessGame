package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import boardGame.game.GameManager;
import boardGame.game.chess.ChessGameSetter;

class testGameCheckState3 {

	@Test
	void test() {
		GameManager manager = new GameManager(ChessGameSetter.setGame());
		assertEquals("WHITE", manager.getTurnedCampName());
		
		//assertFalse(manager.isCheck());
		
		showList(manager.getCampUnit());
		manager.selectPiecePosition("b1");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
		manager.move("g6");
		showList(manager.getOppositeCampUnit());System.out.println("\n");
		
		assertEquals("BLACK", manager.getTurnedCampName());
		showList(manager.getCampUnit());System.out.println("\n");
		manager.selectPiecePosition("a8");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
		manager.selectPiecePosition("b8");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
		manager.selectPiecePosition("c8");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
		manager.selectPiecePosition("e8");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
		manager.selectPiecePosition("f8");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
		manager.selectPiecePosition("g8");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
		manager.selectPiecePosition("h8");
		showList(manager.getSelectedPieceMoves());System.out.println("\n");
//		manager.move("a1");
//		showList(manager.getCampUnit());
//		showList(manager.getOppositeCampUnit());
		
	}
	
	public void showList(List<String> list) {
		Iterator<String> il = list.iterator();
		while(il.hasNext()) {
			System.out.println(il.next());
		}
	}

}
