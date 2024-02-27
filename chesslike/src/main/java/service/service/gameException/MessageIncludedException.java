package service.gameException;

public abstract class MessageIncludedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	public MessageIncludedException() {
	}
	public abstract String getMessage();
}
