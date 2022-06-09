package boardGame.exceptions;

public class NotExistsPositionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8277003208750906968L;

	public NotExistsPositionException() {
		super("Input letter does not exists on board");
	}
}
