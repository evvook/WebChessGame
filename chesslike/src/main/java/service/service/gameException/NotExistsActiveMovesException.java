package service.gameException;

public class NotExistsActiveMovesException extends MessageIncludedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	public NotExistsActiveMovesException() {
	}
	public String getMessage() {
		return "������ �� ���� ��ġ �Դϴ�.";
	}
}
