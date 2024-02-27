package service.gameException;

public class NotExistsMoveToException extends MessageIncludedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "행마를 생성할 수 있는 위치가 없습니다.";
	}
}
