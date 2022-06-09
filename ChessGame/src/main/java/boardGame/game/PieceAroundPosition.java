package boardGame.game;

import boardGame.partsOfGame.Position;

//행마를 수월하게 생성하기 위해 두 위치를 갖는 객체
public class PieceAroundPosition {
	private Position from;
	private Position to;
	private GameMediator gm;
	
	public PieceAroundPosition(GameMediator gm) {
		this.gm = gm;
	}
	
	//포지션 셋트
	public void setPosition(Position position) {
		try {
			if(gm.findPiece(position.getLetter())==null)
				throw new Exception("There is not piece");
			from = position;
			to = position;
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("PieceAroundPostion.setPosition: "+e.toString());
		}
	}
	
	public Position getCenterPosition() {
		return from;
	}
	
	public Position getAroundPosition() {
		return to;
	}
	
	public void setAroundPosition(Position position) {
		//원래라면, from이 없는 경우 예외 발생해야 함
		to = position;
	}
	
	public void init() {
		to = from;
	}
	public void clear() {
		from = null;
		to = null;
	}
	
	public boolean isMovedAraound() {
		return !from.equals(to);
	}
	
	public boolean isEmpty() {
		return null == from;
	}

	//초기화시 사용
	public void setInitPosition(Position position) {
		// TODO Auto-generated method stub
		from = position;
		to = position;
	}
}