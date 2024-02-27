package service.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.chess.game.ChessGameManager;
import service.chess.game.ChessGameSetter;
import service.chess.game.ChessGameSetterMocker;
import service.gameController.Interpreter;
import service.gameController.WebInterpreter;

//게임의 직접적인 생성과 진행에 연관되는 게임세터, 게임매니저를 만들어 새로운 게임을 생성하고
//외부와 소통할 게임 인터페이스에 인터프리터를 셋팅하여 반환한다
//모든 새로운 게임은 별도의 게임 인터페이스에 연결된다
public class GameEngine {
	//List<GameInterface> giList = new ArrayList<GameInterface>();
	public static GameInterface gameStart(Interpreter inf,Map<String,String> input) throws Exception {
		if(inf == null) {
			inf = new WebInterpreter();
		}
		
		GameSetter gs = null;
		if("battle".equals(input.get("mode"))) {
			gs = new ChessGameSetter();
		}else if("explanation".equals(input.get("mode"))) {
			gs = new ChessGameSetterMocker();
		}
		
		if(gs == null) {
			throw new Exception("게임 셋팅 실패");
		}
		
		GameManager gm = new ChessGameManager();
		gm.setGame(gs);
		return new GameInterface(gm,inf);
		//giList.add(gi);
	}
	
	public static int getGIListSize() {
		return 1;
	}
}
