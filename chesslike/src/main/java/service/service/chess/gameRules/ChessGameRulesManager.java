package service.chess.gameRules;

import service.chess.gameparts.piece.moves.ChessMovesType;
import service.gameRules.MoveFilter;
import service.gameRules.ProhibitSuicideRules;
import service.gameRules.PieceCaptureingRules;
import service.gameRules.RulesManager;
import service.gameparts.Board;

public class ChessGameRulesManager extends RulesManager {
	private Board board;
	public ChessGameRulesManager(Board board) {
		this.board = board;
	}
	
	@Override
	public MoveFilter getInstanceCommonRulesFilter() {
		// TODO Auto-generated method stub
		MoveFilter filter = new ChessMoveFiilter();
		filter.addRules(new PawnMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new KnightMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new MovePathRulesTypeChess(ChessMovesType.getInstance(ChessMovesType.Move)));
		ProhibitSuicideRules chekcRules = new ProhibitSuicideRules(ChessMovesType.getInstance(ChessMovesType.Move),this);
		chekcRules.setBoard(board);
		filter.addRules(chekcRules);
		
		filter.addRules(new PawnMoveRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new KnightMoveRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new AttackPathRulesTypeChess(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new PieceCaptureingRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		chekcRules = new ProhibitSuicideRules(ChessMovesType.getInstance(ChessMovesType.Attack),this);
		chekcRules.setBoard(board);
		filter.addRules(chekcRules);
		
		filter.addRules(new CastlingAttackedRules(ChessMovesType.getInstance(ChessMovesType.Castling),this));
		filter.addRules(new CastlingMoveRules(ChessMovesType.getInstance(ChessMovesType.Castling),board));
		chekcRules = new ProhibitSuicideRules(ChessMovesType.getInstance(ChessMovesType.Castling),this);
		chekcRules.setBoard(board);
		filter.addRules(chekcRules);
		return filter;
	}
	public MoveFilter getInstanceCheckStateFilter() {
		// TODO Auto-generated method stub
		MoveFilter filter = new ChessMoveFiilter();
		filter = new ChessMoveFiilter();
		filter.addRules(new PawnMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new KnightMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new MovePathRulesTypeChess(ChessMovesType.getInstance(ChessMovesType.Move)));
		ProhibitSuicideRules chekcRules = new ProhibitSuicideRules(ChessMovesType.getInstance(ChessMovesType.Move),this);
		chekcRules.setBoard(board);
		filter.addRules(chekcRules);
		
		filter.addRules(new PawnMoveRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new KnightMoveRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new AttackPathRulesTypeChess(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new PieceCaptureingRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		chekcRules = new ProhibitSuicideRules(ChessMovesType.getInstance(ChessMovesType.Attack),this);
		chekcRules.setBoard(board);
		filter.addRules(chekcRules);
		return filter;
	}
	@Override
	public MoveFilter getInstanceSimpleRuleFilter() {
		// TODO Auto-generated method stub
		MoveFilter filter = new ChessMoveFiilter();
		filter = new ChessMoveFiilter();
		filter.addRules(new PawnMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new KnightMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new MovePathRulesTypeChess(ChessMovesType.getInstance(ChessMovesType.Move)));
		
		filter.addRules(new PawnMoveRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new KnightMoveRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new AttackPathRulesTypeChess(ChessMovesType.getInstance(ChessMovesType.Attack)));
		filter.addRules(new PieceCaptureingRules(ChessMovesType.getInstance(ChessMovesType.Attack)));
		return filter;
	}
	public MoveFilter getInstanceCastlingRulesFilter() {
		MoveFilter filter = new ChessMoveFiilter();
		filter = new ChessMoveFiilter();
		filter.addRules(new PawnMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new KnightMoveRules(ChessMovesType.getInstance(ChessMovesType.Move)));
		filter.addRules(new MovePathRulesTypeChess(ChessMovesType.getInstance(ChessMovesType.Move)));
		return filter;
	}

}
