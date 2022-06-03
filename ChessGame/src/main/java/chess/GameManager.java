package chess;

public class GameManager {
	private Board board;
	private Camp camp1;
	private Camp camp2;
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Camp getCamp1() {
		return camp1;
	}
	public void setCamp1(Camp camp1) {
		this.camp1 = camp1;
	}
	public Camp getCamp2() {
		return camp2;
	}
	public void setCamp2(Camp camp2) {
		this.camp2 = camp2;
	}
}
