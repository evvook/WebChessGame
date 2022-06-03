package chess;

import java.util.List;

public class Capability {
	List<Cursor> cursors;
	Movement movement;
	MoveMaker moveMaker;
	
	public Capability(List<Cursor> cursors,Movement movement,MoveMaker moveMaker) {
		this.cursors = cursors;
		this.movement = movement;
		this.moveMaker = moveMaker;
	}
	
	public List<Move> makeMove(Intersection current){
		List<Intersection> path = movement.getPath(cursors, current);
		List<Move> moves = moveMaker.makeMove(current, path);
		return moves;
	}
}
