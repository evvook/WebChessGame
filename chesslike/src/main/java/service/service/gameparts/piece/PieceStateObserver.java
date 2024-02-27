package service.gameparts.piece;

//서브젝트는 Piece
public interface PieceStateObserver {
	public void notifyPieceState(Piece piece);
}
