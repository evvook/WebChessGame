package chess;

import java.util.ArrayList;
import java.util.List;

public class Camp {
	private List<Piece> pieces;
	
	public Camp() {
		pieces = new ArrayList<Piece>();
	}
	
	public void setPiece(Piece piece) {
		pieces.add(piece);
	}
	
	public boolean checkBelongs(Piece piece) {
		for(Piece belongs:pieces) {
			if(belongs.equals(piece)) {
				return true;
			}
		}
		return false;
	}
}
