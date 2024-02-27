package service.gameController;

import java.util.Map;

import service.game.GameInterface;

public interface Interpreter {
	public void setGI(GameInterface gi);
	public void execute(Map<String, String> input) throws Exception;
	public void execute(String input) throws Exception;
	public String getMessage();
	public GameInterface getGI();
	public void setMessage(String message);
}
