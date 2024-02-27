package service.chess.gameparts.piece.moves;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import service.gameparts.piece.moves.MovesType;

enum Type {
	Move, Attack, Castling
}

public class ChessMovesType implements MovesType{
	public static final Type Move = Type.Move;
	public static final Type Attack = Type.Attack;
	public static final Type Castling = Type.Castling;
	
	static private List<ChessMovesType> cmtl = new ArrayList<ChessMovesType>();
	private Type type;
	public ChessMovesType(Type type) {
		this.type = type;
	}

	public static MovesType getInstance(Type type) {
		// TODO Auto-generated method stub
		ChessMovesType cmt = null;
		Iterator<ChessMovesType> mti = cmtl.iterator();
		while(mti.hasNext()) {
			cmt = mti.next();
			if(cmt.equalsType(type)) {
				return cmt;
			}
		}
		cmt = new ChessMovesType(type);
		cmtl.add(cmt);
		return cmt;
	}
	
	public boolean equals(MovesType type) {
		return type.equalsType(this.type);
	}

	@Override
	public boolean equalsType(Object type) {
		// TODO Auto-generated method stub
		return this.type.equals(type);
	}

	@Override
	public Object getMovesType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public boolean equalsType(MovesType type) {
		// TODO Auto-generated method stub
		return this.type.equals(type.getMovesType());
	}
}
