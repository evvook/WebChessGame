package service.gameException;

public class NotExistisPieceException extends MessageIncludedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "위치에 기물이 없습니다.";
	}
}
