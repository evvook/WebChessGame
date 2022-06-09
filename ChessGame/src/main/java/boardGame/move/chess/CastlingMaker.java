package boardGame.move.chess;

import java.util.ArrayList;
import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.move.Move;
import boardGame.move.MoveMaker;
import boardGame.partsOfGame.Camp;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.Position;
import boardGame.partsOfGame.chess.ChessPiece;

public class CastlingMaker implements MoveMaker {
	private Camp onSide;
	private Cursor castlingSideCursor;
	private Cursor oppositeSideCursor;
	
	public CastlingMaker(Camp onSide, Cursor castlingSideCursor,Cursor oppositeSideCursor) {
		this.onSide = onSide;
		this.castlingSideCursor = castlingSideCursor;
		this.oppositeSideCursor = oppositeSideCursor;
	}
	
	@Override
	public List<Move> makeMove(Position from, List<Position> path) {
		// TODO Auto-generated method stub
		//킹이 한 번도 움직이지 않을 것
		ChessPiece king = (ChessPiece)from.getPiece();
		if(king.isMoved()) {
			return new ArrayList<Move>();//빈 리스트 리턴
		}
		//룩이 한 번도 움직이지 않을 것
		//1.룩이 위치에 없는 경우
		//룩 위치를 구함(보드 끝까지 이동)
		Position cursoredPosition = from;
		while(true) {
			if(castlingSideCursor.getPosition(cursoredPosition)==null) {
				break;
			}else {
				cursoredPosition = castlingSideCursor.getPosition(cursoredPosition);
			}
		}
		//1-1.위치에 기물이 없는 경우
		if(cursoredPosition.isBlank()) {
			return new ArrayList<Move>();//빈 리스트 리턴
		}
		//1-2.위치의 기물이 팀의 룩이 아닌 경우
		Piece cursoredPiece = cursoredPosition.getPiece();
		//1-2-1. 팀의 기물이 아닌 경우
		if(!onSide.checkBelongs(cursoredPiece)) {
			return new ArrayList<Move>();//빈 리스트 리턴
		}
		//1-2-2. 룩이 아닌 경우
		if(!"ROOK".equals(cursoredPiece.getRank())) {
			return new ArrayList<Move>();//빈 리스트 리턴
		}
		//1-3. 룩이 움직인 경우
		ChessPiece rook = (ChessPiece)cursoredPiece;
		if(rook.isMoved()) {
			return new ArrayList<Move>();//빈 리스트 리턴
		}
		
		//킹과 룩 사이에 아무 것 도 없을 것
		int executeCount = 0;
		cursoredPosition = from;
		while(true) {
			//룩에 도달할 때 까지 이동
			cursoredPosition = castlingSideCursor.getPosition(cursoredPosition);
			if(rook.equals((ChessPiece)cursoredPosition.getPiece())) {
				break;
			}else {
				//킹과 룩 사이에 기물이 있는 경우
				if(!cursoredPosition.isBlank()) {
					return new ArrayList<Move>();//빈 리스트 리턴
				}else {
					executeCount += 1;
				}
			}
		}
		
		//캐슬링 수 생성
		List<Move> castling = new ArrayList<Move>();
		Position kingTo = path.get(path.size()-1);
		Position RookTo = this.oppositeSideCursor.getPosition(kingTo);
		castling.add(new Castling(from, kingTo, rook.getOnPosition(), RookTo));
		
		return castling;
	}

}
