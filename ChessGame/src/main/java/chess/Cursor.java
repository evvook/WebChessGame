package chess;

public abstract class Cursor {
	protected Board board;
	public Cursor(Board board) {
		this.board = board;
	}
	public abstract Intersection getPosition(Intersection position);
}
