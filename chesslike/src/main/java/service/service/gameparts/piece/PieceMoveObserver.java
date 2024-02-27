package service.gameparts.piece;

//서브젝트는 Piece
public interface PieceMoveObserver {
	public void notifyPiecePosition(Piece piece);
}
