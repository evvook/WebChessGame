package boardGame.exceptions;

public class ImpossibleMoveException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8986536294238475675L;

	public ImpossibleMoveException() {
		super("That's not possible move position");
	}
}
