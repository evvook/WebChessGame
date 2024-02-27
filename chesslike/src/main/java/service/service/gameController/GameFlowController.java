package service.gameController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import service.game.GameEngine;
import service.game.GameContext;
import service.game.GameInterface;

//입력을 받아서 게임을 생성 및 진행하는 객체
public class GameFlowController{
	GameInterface gameInterface = null;
	String mode = null;
	int numOfUser = 0;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getNumOfUser() {
		return numOfUser;
	}

	public void setNumOfUser(int numOfUser) {
		this.numOfUser = numOfUser;
	}

	@SuppressWarnings("unchecked")
	public String run(String input) throws Exception {
		Map<String,String> data = new HashMap<String,String>();
		//입력을 json객체 형태로 가공
		Gson gson = new Gson();
		data = gson.fromJson(input,data.getClass());
		String jsonString = "{\"result\":\"test\"}";
		
		System.out.println("command : "+data.get("command"));
		System.out.println("selected position : "+data.get("positionLetter"));
		System.out.println("gameState : "+data.get("gameState"));
		this.mode = data.get("mode");
		
		GameContext gameContext = null;
		if("start".equals(data.get("command"))) {
			if("battle".equals(data.get("mode"))) {
				if("makeGame".equals(data.get("play"))) {
					gameInterface = GameEngine.gameStart(null,data);
					numOfUser = 1;
					
					gameInterface.setActiveCampKey(data.get("key"));
					gameInterface.setMessage("게임 시작 합니다.");
					gameInterface.setGameState("start");
					gameContext = gameInterface.getGameContext();
					jsonString = gson.toJson(gameContext.getContext());
					
				}else if("joinGame".equals(data.get("play"))) {
					GameFlowController gfc = GameControllerContainer.getController(data.get("oppositeKey"));
					gameInterface = gfc.gameInterface;
					gfc.numOfUser = 2;
					numOfUser = 2;
					
					gameInterface.setOppositeCampKey(data.get("key"));
					gameInterface.setMessage("게임 시작 합니다.");
					gameInterface.setGameState("start");
					gameContext = gameInterface.getGameContext();
					jsonString = gson.toJson(gameContext.getContext());
				}
			}else {
				
				gameInterface = GameEngine.gameStart(null,data);
				gameInterface.setMessage("게임 시작 합니다.");
				gameInterface.setGameState("start");
				gameContext = gameInterface.getGameContext();
				jsonString = gson.toJson(gameContext.getContext());
			}
		}else if("load".equals(data.get("command"))){
			if(gameInterface != null) {
				gameInterface = gameInterface.takeExecutedGame();
				gameInterface.setGameState("onGoing");
				gameInterface.clearMessage();
				gameContext = gameInterface.getGameContext();
				jsonString = gson.toJson(gameContext.getContext());
			}
		}else {
			try {
				gameInterface.setMessage(null);//메세지 초기화
				if("battle".equals(data.get("mode"))) {
					//현재 턴에 입력이 가능한지 확인하여 반환
					if(gameInterface.isPossibleToOrder(data.get("key"))) {
						gameInterface.interpret(data);
						gameInterface = gameInterface.takeExecutedGame();
						gameContext = gameInterface.getGameContext();
					}else {
						//차례가 아님
						gameInterface.setMessage("차례가 아닙니다.");
						gameInterface.setGameState("onGoing");
						gameInterface = gameInterface.takeExecutedGame();
						gameContext = gameInterface.getGameContext();
					}
				}else {
					
					//혼자하는 퍼즐인 경우
					gameInterface.interpret(data);
					gameInterface = gameInterface.takeExecutedGame();
					gameContext = gameInterface.getGameContext();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonString = gson.toJson(gameContext.getContext());
			//System.out.println(jsonString);
			
		}
		return jsonString;
	}
}
