package boardGame.game;

import java.util.List;

public class GameInterface {
	//게임 수행을 위한 명령 모음
	GameManager manager;
	public GameInterface(GameManager manager) {
		// TODO Auto-generated constructor stub
		this.manager = manager;
	}

	//지금이 누구 차례임(진영명 보여줌)
	public String getCampNameThisTurn() {
		return manager.getTurnedCampName();
	}
	
	//선택 가능한 말 보여줌(말 계급과 위치 보여줌)
	public List<String> getCampUnit() {
		return manager.getCampUnit();
	}
	
	public List<String> getOppositeCampUnit() {
		return manager.getOppositeCampUnit();
	}
	
	//말 선택(위치)
	public void selectPiecePosition(String positionLetter) throws Exception{
		manager.selectPiecePosition(positionLetter);
	}
	
	//말 선택(계급)
	public void selectPieceRank(String rank) {
		manager.selectPieceRank(rank);
	}
	
	//선택한 말의 가능 수 보여줘
	public List<String> getPieceMoves() {
		return manager.getSelectedPieceMoves();
	}
	
	//말 선택 취소
	public void unselect() {
		manager.unselect();
	}
	
	//수를 둠(불가능한 수를 둘 경우 예외처리)
	public void move(String letter) {
		manager.move(letter);
	}
	
	//무승부 요청
	public void requestDraw() {
		
	}
	
	//일시정지
	public void pauseGame() {
		
	}
	
	//일시정지 품
	public void unpauseGame() {
		
	}
	
	//새로운 게임 시작
	public void openGame() {
		
	}
	
	//게임 종료
	public void closeGame() {
		
	}
}
