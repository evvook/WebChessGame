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
		//알림 대상 구독자 리스트 생성(알림 도중 구독 취소가 있어도 알림대상은 변함이 없으므로 오류 발생하지 않음)
		List<GameGoingStateSub> notifiedSubList = new ArrayList<GameGoingStateSub>();
		notifiedSubList.addAll(gameStateSubs);
		//알림
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
