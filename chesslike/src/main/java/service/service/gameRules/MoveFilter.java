package service.gameRules;

import service.gameparts.piece.moves.Moves;

public interface MoveFilter {
	
	public void addRules(Rules rules);
	
	public Moves filter(Moves moves);
}
