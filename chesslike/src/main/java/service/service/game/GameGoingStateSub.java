package service.game;
//������Ʈ�� Piece
public interface GameGoingStateSub {
	public void notifyGameState(GameStateBroker broker);
	public void notifyGameStateRollback(GameStateBroker broker);
}