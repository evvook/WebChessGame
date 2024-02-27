package service.chess.gameRules;

import service.chess.gameparts.piece.moves.ChessMovesType;
import service.gameRules.MoveFilter;
import service.gameRules.Rules;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class ChessMoveFiilter implements MoveFilter {
	private Rules moveRules;
	private Rules atteckRules;
	private Rules castlingRules;
	
	@Override
	//책임연쇄를 이루는 룰즈를 등록하고 실행하는 객체
	//규칙으로 판정해서 수를 골라내는 책임을 가지고 있어서 필터라고 이름 붙임
	public void addRules(Rules rules) {
		// TODO Auto-generated method stub
		MovesType type = rules.getMovesType();
		if(type.equalsType(ChessMovesType.Move)) {
			if(moveRules == null) {
				moveRules = rules;
			}else {
				Rules lastRules = moveRules;
				while(lastRules.hasNext()){
					lastRules = lastRules.getNextRules();
				}
				lastRules.setNextRules(rules);
			}
		}
		if(type.equalsType(ChessMovesType.Attack)) {
			if(atteckRules == null) {
				atteckRules = rules;
			}else {
				Rules lastRules = atteckRules;
				while(lastRules.hasNext()){
					lastRules = lastRules.getNextRules();
				}
				lastRules.setNextRules(rules);
			}
		}
		if(type.equalsType(ChessMovesType.Castling)) {
			if(castlingRules == null) {
				castlingRules = rules;
			}else {
				Rules lastRules = castlingRules;
				while(lastRules.hasNext()) {
					lastRules = lastRules.getNextRules();
				}
				lastRules.setNextRules(rules);
			}
		}
	}

	@Override
	public Moves filter(Moves moves) {
		// TODO Auto-generated method stub
		MovesType type = moves.getMovesType();
		if(type.equalsType(ChessMovesType.Move)) {
			if(moveRules != null) {
				return moveRules.judge(moves);
			}
		}
		if(type.equalsType(ChessMovesType.Attack)) {
			if(atteckRules != null) {
				return atteckRules.judge(moves);
			}
		}
		if(type.equalsType(ChessMovesType.Castling)) {
			if(castlingRules != null) {
				return castlingRules.judge(moves);
			}
		}
		return null;
	}
}
