package chess;

public class MoveOn extends Move {

	public MoveOn(Intersection from,Intersection to) {
		this.from = from;
		this.to = to;
	}
	@Override
	public void move() {
		to.setPiece(from.getPiece());
		from.setBlank();
	}
}
