package service.gameException;

public class NotActiveCampUnitException extends MessageIncludedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	public NotActiveCampUnitException() {
	}
	public String getMessage() {
		return "���� �Ͽ� ������ �� ���� ���� �⹰�Դϴ�.";
	}
}
