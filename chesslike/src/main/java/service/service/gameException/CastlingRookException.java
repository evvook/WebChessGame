package service.gameException;

public class CastlingRookException extends MessageIncludedException {
	private String position;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	public CastlingRookException(String position) {
		this.position = position;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "캐슬링을 하기 위한 룩이 위치에 없습니다.("+position+")";
	}
}
