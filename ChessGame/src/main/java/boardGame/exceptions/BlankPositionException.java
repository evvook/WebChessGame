package boardGame.exceptions;

public class BlankPositionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7488696338762451444L;

	public BlankPositionException() {
		super("This position is blank");
	}
}
