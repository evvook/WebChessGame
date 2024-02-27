package service.game;

import java.util.Map;

import service.gameController.Interpreter;
import service.gameException.ExistsCampUnitException;
import service.gameException.NotActiveCampUnitException;
import service.gameException.NotExistisPieceException;
import service.gameException.NotExistsActiveMovesException;
import service.gameException.NotOnBoardException;

//외부와 메시지를 주고받을 인터페이스 객체
//외부와 게임매니저를 연결해준다
//인터프리터-인터페이스 간 연결
//인터페이스-게임매니저 간 연결
public class GameInterface {
	private Interpreter interpreter;
	private GameManager manager;
	
	public GameInterface(GameManager manager,Interpreter interpreter) {
		// TODO Auto-generated constructor stub
		this.manager = manager;
		this.interpreter = interpreter;
		this.interpreter.setGI(this);
	}
	
	
	public void interpret(Map<String,String> input) throws Exception {
		interpreter.execute(input);
	}
	
	public GameInterface takeExecutedGame() {
		// TODO Auto-generated method stub
		return interpreter.getGI();
	}

	public GameContext getGameContext() {
		// TODO Auto-generated method stub
		return this.manager.getGameContext();
	}
	
	public boolean isSelectedPiece(String command) {
		return manager.isSelectedPiece();
	}
	
	public boolean isSelectedPosition(String command){
		try {
			return manager.isSelectedPosition(command);
		} catch (NotOnBoardException e) {
			// TODO Auto-generated catch block
			setMessage(e.getLocalizedMessage());
			return false;
		}
	}
	
	//말 선택(위치)
	public void selectPiece(String positionLetter){
		try {
			this.manager.selectPiece(positionLetter);
		} catch (NotExistisPieceException e) {
			// TODO Auto-generated catch block
			setMessage(e.getLocalizedMessage());
		} catch (NotActiveCampUnitException e) {
			// TODO Auto-generated catch block
			setMessage(e.getLocalizedMessage());
		}
	}
	
	//말 선택 취소
	public void unselect() {
		manager.unselect();
	}
	
	//수를 둠(불가능한 수를 둘 경우 예외처리)
	public void move(String command) throws Exception {
		try {
			manager.move(command);
		}catch(ExistsCampUnitException e) {
			//활성화된 기물의 진형인 경우
			//선택취소
			unselect();
		}catch(NotExistsActiveMovesException e) {
			setMessage(e.getMessage());
		}catch(NotExistisPieceException e) {
			throw e;
		}
	}

	public void undo() {
		// TODO Auto-generated method stub
		if(manager.isExistsActiveMoves()) {
			manager.undo();
		}
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

	public void setGameState(String state) {
		manager.setGameState(state);
	}
	
	public void setMessage(String message) {
		manager.setMessage(message);
	}
	
	public void clearMessage() {
		manager.setMessage(null);
	}

	public boolean isPossibleToOrder(String key) {
		// TODO Auto-generated method stub
		return manager.getActiveCamp().isCampKey(key);
	}

	public void setActiveCampKey(String key) {
		// TODO Auto-generated method stub
		manager.getActiveCamp().setKey(key);
	}
	
	public void setOppositeCampKey(String key) {
		// TODO Auto-generated method stub
		manager.getOppositeCamp().setKey(key);
	}
}
