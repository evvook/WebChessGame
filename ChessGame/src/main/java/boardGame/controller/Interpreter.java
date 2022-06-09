package boardGame.controller;

import boardGame.game.GameInterface;

public interface Interpreter {
	public void setGI(GameInterface gi);
	public void execute(String command) throws Exception;
	public String getMessage();
}
