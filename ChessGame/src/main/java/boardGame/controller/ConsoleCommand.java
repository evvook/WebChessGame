package boardGame.controller;

import java.util.Scanner;

public class ConsoleCommand implements Controller{
	private Scanner sc;
	public ConsoleCommand() {
		if(sc == null) {
			sc = new Scanner(System.in);
		}
	}

	@Override
	public String takeInput() {
		// TODO Auto-generated method stub
		return sc.next();
	}

}
