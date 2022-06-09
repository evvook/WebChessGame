package boardGame.game;

import boardGame.controller.Controller;
import boardGame.controller.Interpreter;

public class Game {
	private Controller controller;
	private Interpreter interpreter;
	
	public Game(Controller controller,Interpreter interpreter) {
		this.controller = controller;
		this.interpreter = interpreter;
	}
	
	public void setGame(GameSetter gs) {
		this.interpreter.setGI(new GameInterface(new GameManager(gs.setGame())));
	}
	
	public void startGame() {
		//해석기(게임인터페이스)
		//메세지 관리자(해석기)
		String message = "Start Game";
		while(true) {
			//메세지 관리자
			if(message != null) {
				System.out.println(message);
			}
			try {
				//해설기-입력받음
				//해석기-실행
				String input = controller.takeInput();
				interpreter.execute(input);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if("java.lang.Exception: close".equals(e.toString())) {
					System.out.println("Close Game");
					break;
				}else {
					System.out.println(e.toString());
				}
			}finally {
				message = interpreter.getMessage();
			}
		}
	}
}
