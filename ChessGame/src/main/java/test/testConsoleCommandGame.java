package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import boardGame.controller.ConsoleCommand;
import boardGame.controller.ConsoleCommandInterpreter;
import boardGame.controller.Controller;
import boardGame.controller.Interpreter;
import boardGame.game.Game;
import boardGame.game.GameSetter;
import boardGame.game.chess.ChessGameSetter;

class testConsoleCommandGame {

	@Test
	void test() {
		Controller chessGameController = new ConsoleCommand();
		Interpreter chessGameInterpreter = new ConsoleCommandInterpreter();
		GameSetter chessGameSetter = new ChessGameSetter();
		Game chessGame = new Game(chessGameController, chessGameInterpreter);
		chessGame.setGame(chessGameSetter);
		chessGame.startGame();
	}

}
