package service.gameException;

public class NotOnBoardException extends MessageIncludedException {
	private String letter;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3346149825409634506L;
	public NotOnBoardException(String letter) {
		this.letter = letter;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "보드에 존재하는 위치가 아닙니다.("+letter+")";
	}
}
