package boardGame.partsOfGame;

import java.util.ArrayList;
import java.util.List;

import boardGame.game.GameMediator;
import boardGame.move.Move;

public class Piece {
	protected GameMediator gm;
	protected String rank;//계급
	protected List<Maneuver> maneuvers;
	
	public Piece(GameMediator gm, String rank, List<Maneuver> maneuvers) {
		this.gm = gm;
		this.rank = rank;
		this.maneuvers = maneuvers;
	}
	
	public String getRank() {
		return this.rank;
	}

	public void setPath() {
		// TODO Auto-generated method stub
		//경로는 현재 위치만 있으면 만들 수 있으니까
		//경로를 생성하기 전에 기물의 위치를 잡아줌
		gm.selectPosition(gm.getPositionLetterOnPiece(this));
		for(Maneuver m : maneuvers) {
			//this의 위치로 커서 이동
			m.setPath();
			//경로 생성을 마치면 위치를 초기화해줌
			gm.initCursoredPosition();
		}
	}
	
	public List<Move> getMoves(){
		List<Move> availableMoves = new ArrayList<Move>();
		for(Maneuver m:maneuvers) {
			availableMoves.addAll(m.getMoves());
			//수 생성을 마치면 위치를 초기화해줌
			gm.initCursoredPosition();
		}
		return availableMoves;
	}
}
