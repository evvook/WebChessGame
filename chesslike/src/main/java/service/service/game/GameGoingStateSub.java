package service.game;
//서브젝트는 Piece
public interface GameGoingStateSub {
	public void notifyGameState(GameStateBroker broker);
	public void notifyGameStateRollback(GameStateBroker broker);
}