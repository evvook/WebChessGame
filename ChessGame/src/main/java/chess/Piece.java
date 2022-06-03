package chess;

import java.util.ArrayList;
import java.util.List;

public class Piece {
	private String rank;//계급
	private List<Capability> capablities;
	
	public Piece(String rank,List<Capability> capablities) {
		this.rank = rank;
		this.capablities = capablities;
	}
	
	public List<Move> getMoves(Intersection current){
		List<Move> moves = new ArrayList<Move>();
		for(Capability capability:capablities) {
			moves.addAll(capability.makeMove(current));
		}
		return moves;
	}
}
