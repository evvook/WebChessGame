package service.gameException;

public class NotActiveCampUnitException extends MessageIncludedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	public NotActiveCampUnitException() {
	}
	public String getMessage() {
		return "현재 턴에 선택할 수 없는 진영 기물입니다.";
	}
}
