package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

import boardGame.cursor.CursorMaker;
import boardGame.game.GameMediator;
import boardGame.game.LooseCouplingContainer;
import boardGame.game.chess.pieceMaker.ChessPieceMaker;
import boardGame.game.chess.pieceMaker.KingMaker;
import boardGame.game.chess.pieceMaker.RookMaker;
import boardGame.partsOfGame.Board;
import boardGame.partsOfGame.Camp;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.Position;

class testLooseR {

	@Test
	void test() {
		//보드, 캠프, 기물 생성
		//게임매니저
		//기물제작자
		
		//보드는 그냥 만들면 되고,
		//느슨한 의존 보관자(lcc) 객체도 그냥 만들면 됨(나중에 캠프,기물,위치 채워넣어야함)
		
		//1.보드 만들기
		String[] rank = {"a","b","c","d","e","f","g","h"};
		String[] file = {"1","2","3","4","5","6","7","8"};
		Board chessBoard = new Board(Arrays.asList(rank),Arrays.asList(file));
		//2.lcc 만들기
		LooseCouplingContainer lcc = new LooseCouplingContainer();
		//3.게임매니저 만들기(보드와 lcc 전달)
		GameMediator gm = new GameMediator(chessBoard, lcc);
		//4.캠프 만들기(게임매니저 전달)
		Camp white = new Camp("WHITE", true, gm);
		Camp black = new Camp("BLACK", true, gm);
		//5.기물 만들기
		//5-1.커서메이커 만들기
		CursorMaker cm = new CursorMaker(gm);
		//5-2.기물제작자 만들기(커서메에커와 게임매니저 전달)
		ChessPieceMaker kingMaker = new KingMaker(cm, gm);
		ChessPieceMaker rookMaker = new RookMaker(cm, gm);
		//5-3.기물 만들기
		Piece whiteKing = kingMaker.makePiece();
		Piece whiteRook = rookMaker.makePiece();
		Piece blackKing = kingMaker.makePiece();
		Piece blackRook = rookMaker.makePiece();
		
		//6.lcc 세트
		//6-1.lcc에 게임매니저 세트
		lcc.setGameManager(gm);
		//6-2.lcc에 캠프 세트
		gm.setCamps(white, black);
		//6-3.lcc에 캠프와 기물 관계(=기물에 진영 배정)
		//기물을 배정할 진영 설정
		gm.selectCamp(white);
		gm.setPieceInvolveCamp(whiteKing);
		//6-4.lcc에 기물과 위치 관계(=기물을 보드위에 놓음)
		gm.selectPosition("e1");
		gm.setOnPiece(whiteKing);
		gm.setPieceInvolveCamp(whiteRook);
		gm.selectPosition("a1");
		gm.setOnPiece(whiteRook);
		
		gm.selectCamp(black);
		
		gm.setPieceInvolveCamp(blackKing);
		gm.selectPosition("e8");
		gm.setOnPiece(blackKing);
		
		gm.setPieceInvolveCamp(blackRook);
		gm.selectPosition("a8");
		gm.setOnPiece(blackRook);
		
		gm.selectPosition("a8");
		gm.move("a1");
		gm.selectPosition("a1");
		gm.move("e1");
		gm.selectPosition("e1");
		gm.move("e8");
		
		Set<Position>is = gm.getAllBoardIntersections();
		Iterator<Position> i = is.iterator();
		while(i.hasNext()) {
			Position inter = i.next();
			Piece p = lcc.getPieceOnPosition(inter);
			Camp c = lcc.getPieceAssociatedCamp(p);
			if(p != null) {
				System.out.println(c.getName()+"_"+ p.getRank()+"_"+inter.getLetter());
			}
		}
	}
}
