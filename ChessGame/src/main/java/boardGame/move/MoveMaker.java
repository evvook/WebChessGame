package boardGame.move;

import java.util.List;

import boardGame.partsOfGame.Position;

public interface MoveMaker {
	public List<Move> makeMove(List<Position> path);
}
