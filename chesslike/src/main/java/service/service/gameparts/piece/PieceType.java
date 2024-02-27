package service.gameparts.piece;

public interface PieceType {
	public boolean equalsType(Object type);
	public boolean equalsType(PieceType type);
	public Object getPieceType();
	
	public String getRank();
	public String getNotation();
	public String getLordNoation();
}
