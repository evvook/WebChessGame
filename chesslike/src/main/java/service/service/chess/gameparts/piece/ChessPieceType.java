package service.chess.gameparts.piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import service.gameparts.piece.PieceType;

enum Type{
	King,Queen,Rook,Bishop,Knight,Pawn
}

public class ChessPieceType implements PieceType{
	public static final Type King = Type.King;
	public static final Type Queen = Type.Queen;
	public static final Type Rook = Type.Rook;
	public static final Type Bishop = Type.Bishop;
	public static final Type Knight = Type.Knight;
	public static final Type Pawn = Type.Pawn;
	
	static private List<ChessPieceType> cptl = new ArrayList<ChessPieceType>();
	private Type type;
	private String rank;
	private String notation;
	private Map<String,String> goingFirstCampPieceSC;
	private Map<String,String> oppositeCampPieceSC;

	public ChessPieceType(Type type) {
		this.type = type;
		
		goingFirstCampPieceSC = new HashMap<String, String>();
		oppositeCampPieceSC = new HashMap<String, String>();
		if(type == ChessPieceType.King) {
			rank = "King";
			notation = "K";
			goingFirstCampPieceSC.put(notation, "♔");
			oppositeCampPieceSC.put(notation, "♚");
		}
		if(type == ChessPieceType.Queen) {
			rank = "Queen";
			notation = "Q";
			goingFirstCampPieceSC.put(notation, "♕");
			oppositeCampPieceSC.put(notation, "♛");
		}
		if(type == ChessPieceType.Rook) {
			rank = "Rook";
			notation = "R";
			goingFirstCampPieceSC.put(notation, "♖");
			oppositeCampPieceSC.put(notation, "♜");
		}
		if(type == ChessPieceType.Bishop) {
			rank = "Bishop";
			notation = "B";
			goingFirstCampPieceSC.put(notation, "♗");
			oppositeCampPieceSC.put(notation, "♝");
		}
		if(type == ChessPieceType.Knight) {
			rank = "Knight";
			notation = "N";
			goingFirstCampPieceSC.put(notation, "♘");
			oppositeCampPieceSC.put(notation, "♞");
		}
		if(type == ChessPieceType.Pawn) {
			rank = "Pawn";
			notation = "P";
			goingFirstCampPieceSC.put(notation, "♙");
			oppositeCampPieceSC.put(notation, "♟");
		}
	}
	
	public static PieceType getInstance(Type type) {
		// TODO Auto-generated method stub
		ChessPieceType crt = null;
		Iterator<ChessPieceType> cri = cptl.iterator();
		while(cri.hasNext()) {
			crt = cri.next();
			if(crt.equalsType(type)) {
				return crt;
			}
		}
		return new ChessPieceType(type);
	}
	
	public boolean equals(PieceType type) {
		return type.equalsType(this.type);
	}
	
	public boolean equalsType(Object type) {
		// TODO Auto-generated method stub
		return this.type.equals(type);
	}
	@Override
	public boolean equalsType(PieceType type) {
		// TODO Auto-generated method stub
		return type.equals(type.getPieceType());
	}
	@Override
	public Object getPieceType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String getRank() {
		// TODO Auto-generated method stub
		return rank;
	}
	@Override
	public String getNotation() {
		// TODO Auto-generated method stub
		return notation;
	}
	@Override
	public String getLordNoation() {
		// TODO Auto-generated method stub
		return getInstance(Type.King).getNotation();
	}
	public String getSpacialCharGoingFisrtCamp() {
		return goingFirstCampPieceSC.get(notation);
	}
	public String getSpacialCharOppositeCamp() {
		return oppositeCampPieceSC.get(notation);
	}
}
