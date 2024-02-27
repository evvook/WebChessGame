package service.chess.game;

import java.util.Arrays;

import service.chess.gameRules.ChessGameRulesManager;
import service.chess.gameparts.piece.maker.BishopMaker;
import service.chess.gameparts.piece.maker.KingMaker;
import service.chess.gameparts.piece.maker.KnightMaker;
import service.chess.gameparts.piece.maker.PawnMaker;
import service.chess.gameparts.piece.maker.QueenMaker;
import service.chess.gameparts.piece.maker.RookMaker;
import service.game.Game;
import service.game.GameManager;
import service.game.GameSetter;
import service.gameException.NotOnBoardException;
import service.gameparts.Board;
import service.gameparts.Camp;
import service.gameparts.piece.Piece;
import service.gameparts.piece.PieceMaker;

public class ChessGameSetterMocker extends GameSetter{
	public void setGame(GameManager gm) {
		//게임 매니저를 전달 받아서
		//게임 매니저에게 셋팅된 게임 정보를 넘겨줌
		
		String[] axisX = {"a","b","c","d","e","f","g","h"};
		String[] axisY = {"1","2","3","4","5","6","7","8"};
		Board chessBoard = new Board(Arrays.asList(axisX), Arrays.asList(axisY));
		
		//기물 생성자
		PieceMaker kingMaker = new KingMaker(chessBoard);
		PieceMaker queenMaker = new QueenMaker(chessBoard);
		PieceMaker rookMaker = new RookMaker(chessBoard);
		PieceMaker bishopMaker = new BishopMaker(chessBoard);
		PieceMaker knightMaker = new KnightMaker(chessBoard);
		PieceMaker pawnMaker = new PawnMaker(chessBoard);
		
		//기물 생성
		Piece whiteKing1 = kingMaker.makePiece();
		Piece whiteQueen1 = queenMaker.makePiece();
		Piece whiteRook1 = rookMaker.makePiece();
		Piece whiteRook2 = rookMaker.makePiece();
		Piece whiteBishop1 = bishopMaker.makePiece();
		Piece whiteBishop2 = bishopMaker.makePiece();
		Piece whiteKnight1 = knightMaker.makePiece();
		Piece whiteKnight2 = knightMaker.makePiece();
		//폰들
		Piece whitePawn1 = pawnMaker.makePiece();
		Piece whitePawn2 = pawnMaker.makePiece();
		Piece whitePawn3 = pawnMaker.makePiece();
		Piece whitePawn4 = pawnMaker.makePiece();
		Piece whitePawn5 = pawnMaker.makePiece();
		Piece whitePawn6 = pawnMaker.makePiece();
		Piece whitePawn7 = pawnMaker.makePiece();
		Piece whitePawn8 = pawnMaker.makePiece();
		
		Piece blackKing1 = kingMaker.makePiece();
		Piece blackQueen1 = queenMaker.makePiece();
		Piece blackRook1 = rookMaker.makePiece();
		Piece blackRook2 = rookMaker.makePiece();
		Piece blackBishop1 = bishopMaker.makePiece();
		Piece blackBishop2 = bishopMaker.makePiece();
		Piece blackKnight1 = knightMaker.makePiece();
		Piece blackKnight2 = knightMaker.makePiece();
		//폰들
		Piece blackPawn1 = pawnMaker.makePiece();
		Piece blackPawn2 = pawnMaker.makePiece();
		Piece blackPawn3 = pawnMaker.makePiece();
		Piece blackPawn4 = pawnMaker.makePiece();
		Piece blackPawn5 = pawnMaker.makePiece();
		Piece blackPawn6 = pawnMaker.makePiece();
		Piece blackPawn7 = pawnMaker.makePiece();
		Piece blackPawn8 = pawnMaker.makePiece();
		
		//진영생성
		Camp white = new Camp("white",true);
		Camp black = new Camp("black",false);
		white.setOppositeCamp(black);
		black.setOppositeCamp(white);
		
		//진영과 기물 바인딩
		whiteKing1.setCamp(white);
		whiteQueen1.setCamp(white);
		whiteRook1.setCamp(white);
		whiteRook2.setCamp(white);
		whiteBishop1.setCamp(white);
//		whiteBishop2.setCamp(white);
//		whiteKnight1.setCamp(white);
//		whiteKnight2.setCamp(white);
		whitePawn1.setCamp(white);
//		whitePawn2.setCamp(white);
//		whitePawn3.setCamp(white);
//		whitePawn4.setCamp(white);
		
		blackKing1.setCamp(black);
//		blackQueen1.setCamp(black);
//		blackRook1.setCamp(black);
//		blackRook2.setCamp(black);
//		blackBishop1.setCamp(black);
//		blackBishop2.setCamp(black);
//		blackKnight1.setCamp(black);
//		blackKnight2.setCamp(black);
		blackPawn1.setCamp(black);
//		blackPawn2.setCamp(black);
//		blackPawn3.setCamp(black);
//		blackPawn4.setCamp(black);
		
		
		try {
			//기물이 가진 무브에 옵저버 등록
			//기물의 무브는 기물의 움직임 감시
			setMoveObserver(whiteKing1);
			setMoveObserver(whiteQueen1);
			setMoveObserver(whiteRook1);
			setMoveObserver(whiteRook2);
			setMoveObserver(whiteBishop1);
//			setMoveObserver(whiteBishop2);
//			setMoveObserver(whiteKnight1);
//			setMoveObserver(whiteKnight2);
			setMoveObserver(whitePawn1);
//			setMoveObserver(whitePawn2);
//			setMoveObserver(whitePawn3);
			
			setMoveObserver(blackKing1);
//			setMoveObserver(blackQueen1);
//			setMoveObserver(blackRook1);
//			setMoveObserver(blackRook2);
//			setMoveObserver(blackBishop1);
//			setMoveObserver(blackBishop2);
//			setMoveObserver(blackKnight1);
//			setMoveObserver(blackKnight2);
			setMoveObserver(blackPawn1);
//			setMoveObserver(blackPawn2);
//			setMoveObserver(blackPawn3);
//			setMoveObserver(blackPawn4);	
			
			//기물과 위치 바인딩
			setPieceOnPosition(whiteKing1,chessBoard.getPosition("e1"));
			setPieceOnPosition(whiteQueen1,chessBoard.getPosition("b8"));
			setPieceOnPosition(whiteRook1,chessBoard.getPosition("g6")); 
			setPieceOnPosition(whiteRook2,chessBoard.getPosition("e6"));
			setPieceOnPosition(whiteBishop1,chessBoard.getPosition("f5")); 
//			setPieceOnPosition(whiteKnight1,chessBoard.getPosition("c1"));
//			setPieceOnPosition(whiteKnight2,chessBoard.getPosition("f1"));
//			setPieceOnPosition(whiteBishop2,chessBoard.getPosition("g1"));
//			//폰
			setPieceOnPosition(whitePawn1,chessBoard.getPosition("b3"));
//			setPieceOnPosition(whitePawn3,chessBoard.getPosition("e5"));
//			setPieceOnPosition(whitePawn1,chessBoard.getPosition("f4"));
//			
			setPieceOnPosition(blackKing1,chessBoard.getPosition("f7"));
//			setPieceOnPosition(blackKnight1,chessBoard.getPosition("b8"));
//			setPieceOnPosition(blackRook1,chessBoard.getPosition("a8"));
//			setPieceOnPosition(blackBishop1,chessBoard.getPosition("b8"));
//			setPieceOnPosition(blackQueen1,chessBoard.getPosition("d8"));
//			setPieceOnPosition(blackKnight2,chessBoard.getPosition("f8"));
//			setPieceOnPosition(blackBishop2,chessBoard.getPosition("g8"));
//			setPieceOnPosition(blackRook2,chessBoard.getPosition("h8"));
//			//폰
			setPieceOnPosition(blackPawn1,chessBoard.getPosition("b5"));
//			setPieceOnPosition(blackPawn2,chessBoard.getPosition("e4"));
//			setPieceOnPosition(blackPawn3,chessBoard.getPosition("f2"));
//			setPieceOnPosition(blackPawn4,chessBoard.getPosition("c4"));
			
		} catch (NotOnBoardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//게임매니저에게 게임 정보를 전달하고
		gm.setGameInfo(new Game(chessBoard,white,black));
		gm.setGameRulesManager(new ChessGameRulesManager(chessBoard));
	}
}
