package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import boardGame.controller.ConsoleCommand;
import boardGame.controller.Controller;

class testConsoleCommand {

	@Test
	void test() {
		Controller controller = new ConsoleCommand();
		assertEquals("HI", controller.takeInput());
		assertEquals("HI", controller.takeInput());
	}

}
