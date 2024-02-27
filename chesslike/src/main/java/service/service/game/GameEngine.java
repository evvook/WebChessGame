package service.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.chess.game.ChessGameManager;
import service.chess.game.ChessGameSetter;
import service.chess.game.ChessGameSetterMocker;
import service.gameController.Interpreter;
import service.gameController.WebInterpreter;

//������ �������� ������ ���࿡ �����Ǵ� ���Ӽ���, ���ӸŴ����� ����� ���ο� ������ �����ϰ�
//�ܺο� ������ ���� �������̽��� ���������͸� �����Ͽ� ��ȯ�Ѵ�
//��� ���ο� ������ ������ ���� �������̽��� ����ȴ�
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
			throw new Exception("���� ���� ����");
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
