package service.game;

import java.util.ArrayList;
import java.util.List;

public class GameStateBroker {
	private List<GameGoingStateSub> gameStateSubs;
	public GameStateBroker(GameGoingStatePub gameStatePub) {
		gameStateSubs = new ArrayList<GameGoingStateSub>();
	}
	
	public void gatterObserversForNotify(GameGoingStateSub s) {
		gameStateSubs.add(s);
	}
	
	public void gatterObserversForNotifyRollback(GameGoingStateSub s) {
		gameStateSubs.add(s);
	}
	
	public void notifyGameState() {
		//�˸� ��� ������ ����Ʈ ����(�˸� ���� ���� ��Ұ� �־ �˸������ ������ �����Ƿ� ���� �߻����� ����)
		List<GameGoingStateSub> notifiedSubList = new ArrayList<GameGoingStateSub>();
		notifiedSubList.addAll(gameStateSubs);
		//�˸�
		for(GameGoingStateSub sub:notifiedSubList) {
			sub.notifyGameState(this);
		}
	}
	public void notifyGameStateRollback() {
		List<GameGoingStateSub> notifiedSubList = new ArrayList<GameGoingStateSub>();
		notifiedSubList.addAll(gameStateSubs);
		
		for(GameGoingStateSub sub:notifiedSubList) {
			sub.notifyGameStateRollback(this);
		}
	}
	
	public void registGameStateSub(GameGoingStateSub s) {
		gameStateSubs.add(s);
	};
	public void removeGameStateSub(GameGoingStateSub s) {
		gameStateSubs.remove(s);
	};
}
