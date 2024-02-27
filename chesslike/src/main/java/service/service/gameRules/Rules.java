package service.gameRules;

import service.gameparts.Position;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

//책임연쇄 패턴 적용
public abstract class Rules {
	private Rules nextRules;
	private MovesType movesType;
	
	public Rules(MovesType movesType) {
		// TODO Auto-generated constructor stub
		this.movesType = movesType;
	}
	//책임연쇄 패턴 관련 메서드
	public void setNextRules(Rules rules){
		this.nextRules = rules;
	}
	public boolean hasNext() {
		return nextRules != null;
	}
	public Rules getNextRules() {
		return nextRules;
	}
	public abstract Moves judge(Moves moves);
	
	//룰 관련 메서드
	public MovesType getMovesType() {
		// TODO Auto-generated method stub
		return movesType;
	}
	
	protected boolean isBeginning(Moves moves) {
		// TODO Auto-generated method stub
		Position from = moves.getFrom();
		Position to = moves.getTo();
		int fromX = (int)from.getLetterX().charAt(0);
		int toX = (int)to.getLetterX().charAt(0);
		int fromY = (int)from.getLetterY().charAt(0);
		int toY = (int)to.getLetterY().charAt(0);
		
		int resultX = Math.abs(toX-fromX);
		int resultY = Math.abs(toY-fromY);
		
		if(resultX == 1 && resultY == 0) {
			return true;
		}
		if(resultX == 0 && resultY == 1) {
			return true;
		}
		if(resultX == 1 && resultY == 1) {
			return true;
		}
		return false;
	}
}
