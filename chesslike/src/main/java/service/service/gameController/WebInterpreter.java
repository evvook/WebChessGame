package service.gameController;

import java.util.Map;

import service.game.GameEngine;
import service.game.GameInterface;

//�Է��� �м��ϰ� �м��� ���� �������̽��� ����� �����Ű�� ��ü
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
		//���� ���¿� ���� �������̽��� ��û
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
		
		//��ɾ ���� �������̽��� ��û
		if("restart".equals(command)) {
			gi = GameEngine.gameStart(this,input);
			gi.setMessage("����� �մϴ�.");
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
