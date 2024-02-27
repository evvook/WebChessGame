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
	//å�ӿ��⸦ �̷�� ��� ����ϰ� �����ϴ� ��ü
	//��Ģ���� �����ؼ� ���� ��󳻴� å���� ������ �־ ���Ͷ�� �̸� ����
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
