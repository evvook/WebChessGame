package chess;

import java.util.List;

public interface MoveMaker {
	public List<Move> makeMove(Intersection from, List<Intersection> path);
}
