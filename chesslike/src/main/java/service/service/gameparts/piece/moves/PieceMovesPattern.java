package service.gameparts.piece.moves;

import java.util.List;

import service.gameparts.Position;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.relativePosition.RelativePosition;

public abstract class PieceMovesPattern{
	protected List<RelativePosition> relativePositons;
	protected MovesType moveType;
	protected PieceType pieceType;
	public PieceMovesPattern(PieceType pieceType, MovesType movesType, List<RelativePosition> relativePositons) {
		this.moveType = movesType;
		this.pieceType = pieceType;
		this.relativePositons = relativePositons;
	}
	public abstract PieceType getPieceType();
	public abstract MovesType getMoveType();
	public abstract List<Moves> getMovesInstances();
	public abstract Position makeMovesToPosition(Moves moves);
}
