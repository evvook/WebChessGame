package service.game;

public interface GameGoingStatePub {
	public void registGameStateSub(GameGoingStateSub s);
	public void removeGameStateSub(GameGoingStateSub s);
}
