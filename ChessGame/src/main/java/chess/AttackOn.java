package chess;

public class AttackOn extends Move {

	public AttackOn(Intersection from,Intersection to) {
		this.from = from;
		this.to = to;
	}
	@Override
	public void move() {
		to.setPiece(from.getPiece());
		from.setBlank();
	}

}
