package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import game.GameEngine;
import game.GameContext;
import game.GameInterface;

class testGameMake1 {

	@Test
	void test() {
		Gson gson = new Gson();
		GameInterface gameInterface = GameEngine.gameStart();
		GameContext gameInfo = gameInterface.getGameInfo();
		Map<String,Object>info = gameInfo.getInfo();
		String jsonString = gson.toJson(info);
		System.out.println(jsonString);
	}

}
