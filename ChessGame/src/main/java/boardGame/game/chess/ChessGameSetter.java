package boardGame.game.chess;

import java.util.Arrays;

import boardGame.cursor.CursorMaker;
import boardGame.game.GameMediator;
import boardGame.game.GameSetter;
import boardGame.game.LooseCouplingContainer;
import boardGame.game.chess.pieceMaker.BishopMaker;
import boardGame.game.chess.pieceMaker.ChessPieceMaker;
import boardGame.game.chess.pieceMaker.KingMaker;
import boardGame.game.chess.pieceMaker.KnightMaker;
import boardGame.game.chess.pieceMaker.PawnMaker;
import boardGame.game.chess.pieceMaker.QueenMaker;
import boardGame.game.chess.pieceMaker.RookMaker;
import boardGame.partsOfGame.Board;
import boardGame.partsOfGame.Camp;
import boardGame.partsOfGame.Piece;

public class ChessGameSetter implements GameSetter{
	public GameMediator setGame() {
		// TODO Auto-generated method stub
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
		ChessPieceMaker knightMaker = new KnightMaker(cm, gm);
		ChessPieceMaker bishopMaker = new BishopMaker(cm, gm);
		ChessPieceMaker queenMaker = new QueenMaker(cm, gm);
		ChessPieceMaker pawnMaker = new PawnMaker(cm, gm);
		//5-3.기물 만들기
		Piece whiteKing = kingMaker.makePiece();
		Piece whiteRook = rookMaker.makePiece();
		Piece whiteRook2 = rookMaker.makePiece();
		Piece whiteKnight = knightMaker.makePiece();
		Piece whiteKnight2 = knightMaker.makePiece();
		Piece whiteBishop = bishopMaker.makePiece();
		Piece whiteBishop2 = bishopMaker.makePiece();
		Piece whiteQueen = queenMaker.makePiece();
		Piece whitePawn = pawnMaker.makePiece();
		Piece whitePawn2 = pawnMaker.makePiece();
		Piece whitePawn3 = pawnMaker.makePiece();
		Piece whitePawn4 = pawnMaker.makePiece();
		Piece whitePawn5 = pawnMaker.makePiece();
		Piece whitePawn6 = pawnMaker.makePiece();
		Piece whitePawn7 = pawnMaker.makePiece();
		Piece whitePawn8 = pawnMaker.makePiece();
		
		Piece blackKing = kingMaker.makePiece();
		Piece blackRook = rookMaker.makePiece();
		Piece blackRook2 = rookMaker.makePiece();
		Piece blackKnight = knightMaker.makePiece();
		Piece blackKnight2 = knightMaker.makePiece();
		Piece blackBishop = bishopMaker.makePiece();
		Piece blackBishop2 = bishopMaker.makePiece();
		Piece blackQueen = queenMaker.makePiece();
		Piece blackPawn = pawnMaker.makePiece();
		Piece blackPawn2 = pawnMaker.makePiece();
		Piece blackPawn3 = pawnMaker.makePiece();
		Piece blackPawn4 = pawnMaker.makePiece();
		Piece blackPawn5 = pawnMaker.makePiece();
		Piece blackPawn6 = pawnMaker.makePiece();
		Piece blackPawn7 = pawnMaker.makePiece();
		Piece blackPawn8 = pawnMaker.makePiece();
		
		//6.lcc 세트
		//6-1.lcc에 게임매니저 세트
		lcc.setGameManager(gm);
		//6-2.lcc에 캠프 세트
		gm.setCamps(white, black);
		//6-3.lcc에 캠프와 기물 관계(=기물에 진영 배정)
		//기물을 배정할 진영 설정
		gm.selectCamp(white);
		gm.setPieceInvolveCamp(whiteKing);
		gm.setPieceInvolveCamp(whiteRook);
		gm.setPieceInvolveCamp(whiteRook2);
		gm.setPieceInvolveCamp(whiteKnight);
		gm.setPieceInvolveCamp(whiteKnight2);
		gm.setPieceInvolveCamp(whiteBishop);
		gm.setPieceInvolveCamp(whiteBishop2);
		gm.setPieceInvolveCamp(whiteBishop2);
		gm.setPieceInvolveCamp(whiteQueen);
		gm.setPieceInvolveCamp(whitePawn);
		gm.setPieceInvolveCamp(whitePawn2);
		gm.setPieceInvolveCamp(whitePawn3);
		gm.setPieceInvolveCamp(whitePawn4);
		gm.setPieceInvolveCamp(whitePawn5);
		gm.setPieceInvolveCamp(whitePawn6);
		gm.setPieceInvolveCamp(whitePawn7);
		gm.setPieceInvolveCamp(whitePawn8);
		//6-4.lcc에 기물과 위치 관계(=기물을 보드위에 놓음)
		gm.selectInitPosition("a1");
		gm.setOnPiece(whiteRook);
		gm.selectInitPosition("b1");
		gm.setOnPiece(whiteKnight);
		gm.selectInitPosition("c1");
		gm.setOnPiece(whiteBishop);
		gm.selectInitPosition("d1");
		gm.setOnPiece(whiteQueen);
		gm.selectInitPosition("e1");
		gm.setOnPiece(whiteKing);
		gm.selectInitPosition("f1");
		gm.setOnPiece(whiteBishop2);
		gm.selectInitPosition("g1");
		gm.setOnPiece(whiteKnight2);
		gm.selectInitPosition("h1");
		gm.setOnPiece(whiteRook2);
		//pawn
		gm.selectInitPosition("a2");
		gm.setOnPiece(whitePawn);
		gm.selectInitPosition("b2");
		gm.setOnPiece(whitePawn2);
		gm.selectInitPosition("c2");
		gm.setOnPiece(whitePawn3);
		gm.selectInitPosition("d2");
		gm.setOnPiece(whitePawn4);
		gm.selectInitPosition("e2");
		gm.setOnPiece(whitePawn5);
		gm.selectInitPosition("f2");
		gm.setOnPiece(whitePawn6);
		gm.selectInitPosition("g2");
		gm.setOnPiece(whitePawn7);
		gm.selectInitPosition("h2");
		gm.setOnPiece(whitePawn8);
		
		//흑 기물 셋팅
		gm.selectCamp(black);
		gm.setPieceInvolveCamp(blackKing);
		gm.setPieceInvolveCamp(blackRook);
		gm.setPieceInvolveCamp(blackRook2);
		gm.setPieceInvolveCamp(blackKnight);
		gm.setPieceInvolveCamp(blackKnight2);
		gm.setPieceInvolveCamp(blackBishop);
		gm.setPieceInvolveCamp(blackBishop2);
		gm.setPieceInvolveCamp(blackQueen);
		gm.setPieceInvolveCamp(blackPawn);
		gm.setPieceInvolveCamp(blackPawn2);
		gm.setPieceInvolveCamp(blackPawn3);
		gm.setPieceInvolveCamp(blackPawn4);
		gm.setPieceInvolveCamp(blackPawn5);
		gm.setPieceInvolveCamp(blackPawn6);
		gm.setPieceInvolveCamp(blackPawn7);
		gm.setPieceInvolveCamp(blackPawn8);
		gm.selectInitPosition("a8");
		gm.setOnPiece(blackRook);
		gm.selectInitPosition("b8");
		gm.setOnPiece(blackKnight);
		gm.selectInitPosition("c8");
		gm.setOnPiece(blackBishop);
		gm.selectInitPosition("d8");
		gm.setOnPiece(blackQueen);
		gm.selectInitPosition("e8");
		gm.setOnPiece(blackKing);
		gm.selectInitPosition("f8");
		gm.setOnPiece(blackBishop2);
		gm.selectInitPosition("g8");
		gm.setOnPiece(blackKnight2);
		gm.selectInitPosition("h8");
		gm.setOnPiece(blackRook2);
		//pawn
		gm.selectInitPosition("a7");
		gm.setOnPiece(blackPawn);
		gm.selectInitPosition("b7");
		gm.setOnPiece(blackPawn2);
		gm.selectInitPosition("c7");
		gm.setOnPiece(blackPawn3);
		gm.selectInitPosition("d7");
		gm.setOnPiece(blackPawn4);
		gm.selectInitPosition("e7");
		gm.setOnPiece(blackPawn5);
		gm.selectInitPosition("f7");
		gm.setOnPiece(blackPawn6);
		gm.selectInitPosition("g7");
		gm.setOnPiece(blackPawn7);
		gm.selectInitPosition("h7");
		gm.setOnPiece(blackPawn8);
		
		//게임 시작하기전 백이 선공으로 설정
		gm.setGoingFirstCamp();;
		return gm;
	}

}
