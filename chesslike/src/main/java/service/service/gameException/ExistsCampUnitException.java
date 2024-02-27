package service.gameException;

public class ExistsCampUnitException extends MessageIncludedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "위치에 같은 진영 기물이 있습니다.";
	}
}
