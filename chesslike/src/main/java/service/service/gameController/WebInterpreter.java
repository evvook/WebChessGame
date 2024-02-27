package service.gameController;

import java.util.Map;

import service.game.GameEngine;
import service.game.GameInterface;

//입력을 분석하고 분석에 따라 인터페이스에 명령을 실행시키는 객체
public class WebInterpreter implements Interpreter{
	private GameInterface gi;
	private String message;
	@Override
	public void setGI(GameInterface gi) {
		this.gi = gi;
	}

	@Override
	public void execute(String command) throws Exception {
		// TODO Auto-generated method stub
		//게임 상태에 따라 인터페이스에 요청
		if(gi.isSelectedPiece(command)) {
			if(gi.isSelectedPosition(command)) {
				gi.unselect();
			}else {
				gi.move(command);
			}
		}else {
			gi.selectPiece(command);
		}
	}


	@Override
	public void execute(Map<String, String> input) throws Exception {
		// TODO Auto-generated method stub
		String positionLetter = input.get("positionLetter");
		String command = input.get("command");
		String gameState = input.get("gameState");
		
		//명령어에 따라 인터페이스에 요청
		if("restart".equals(command)) {
			gi = GameEngine.gameStart(this,input);
			gi.setMessage("재시작 합니다.");
			gi.setGameState("restart");
		}else if("undo".equals(command)) {
			gi.undo();
		}else if(positionLetter != null && "onGoing".equals(gameState)) {
			execute(positionLetter);
		}
	}	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

//	@Override
//	public GameContext getGameContext() {
//		// TODO Auto-generated method stub
//		return gi.getGameInfo();
//	}

	@Override
	public GameInterface getGI() {
		// TODO Auto-generated method stub
		return gi;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		gi.setMessage(message);
	}
}
