package boardGame.game.chess.pieceMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.cursor.CursorMaker;
import boardGame.game.GameMediator;
import boardGame.move.MoveMaker;
import boardGame.move.chess.KnightAttackOnMaker;
import boardGame.move.chess.KnightMoveOnMaker;
import boardGame.movement.MoveOnPath;
import boardGame.movement.Movement;
import boardGame.partsOfGame.Maneuver;
import boardGame.rules.AttackRulePieceOpposite;
import boardGame.rules.AttackRulePieceSameSide;
import boardGame.rules.AttackRulePostionBlank;
import boardGame.rules.Judgement;
import boardGame.rules.MoveRulePostionBlank;
import boardGame.rules.Rules;

public class KnightMaker extends ChessPieceMaker {

	public KnightMaker(CursorMaker cursorMaker, GameMediator gm) {
		super(cursorMaker, gm);
		// TODO Auto-generated constructor stub
		this.rank = "KNIGHT";
	}

	@Override
	protected List<Maneuver> makeMoveOn() {
		// TODO Auto-generated method stub
		List<Maneuver> maneuvers = new ArrayList<Maneuver>();
		
		Cursor[] cursor1 = {cursorMaker.makeUpFileCursor(),cursorMaker.makeQuadrant2ndCursor()};
		Cursor[] cursor2 = {cursorMaker.makeUpFileCursor(),cursorMaker.makeQuadrant1stCursor()};
		Cursor[] cursor3 = {cursorMaker.makeUpRankCursor(),cursorMaker.makeQuadrant1stCursor()};
		Cursor[] cursor4 = {cursorMaker.makeUpRankCursor(),cursorMaker.makeQuadrant4thCursor()};
		Cursor[] cursor5 = {cursorMaker.makeLowFileCursor(),cursorMaker.makeQuadrant4thCursor()};
		Cursor[] cursor6 = {cursorMaker.makeLowFileCursor(),cursorMaker.makeQuadrant3rdCursor()};
		Cursor[] cursor7 = {cursorMaker.makeLowRankCursor(),cursorMaker.makeQuadrant3rdCursor()};
		Cursor[] cursor8 = {cursorMaker.makeLowRankCursor(),cursorMaker.makeQuadrant2ndCursor()};
		
		Movement movement = new MoveOnPath();
		
		Rules rules = new MoveRulePostionBlank(new Judgement(gm));
		
		MoveMaker moveMaker = new KnightMoveOnMaker(gm,rules);
		
		maneuvers.add(new Maneuver(Arrays.asList(cursor1),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor2),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor3),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor4),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor5),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor6),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor7),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor8),movement,moveMaker));
		
		return maneuvers;
	}

	@Override
	protected List<Maneuver> makeAttackOn() {
		// TODO Auto-generated method stub
		List<Maneuver> maneuvers = new ArrayList<Maneuver>();
		
		Cursor[] cursor1 = {cursorMaker.makeUpFileCursor(),cursorMaker.makeQuadrant2ndCursor()};
		Cursor[] cursor2 = {cursorMaker.makeUpFileCursor(),cursorMaker.makeQuadrant1stCursor()};
		Cursor[] cursor3 = {cursorMaker.makeUpRankCursor(),cursorMaker.makeQuadrant1stCursor()};
		Cursor[] cursor4 = {cursorMaker.makeUpRankCursor(),cursorMaker.makeQuadrant4thCursor()};
		Cursor[] cursor5 = {cursorMaker.makeLowFileCursor(),cursorMaker.makeQuadrant4thCursor()};
		Cursor[] cursor6 = {cursorMaker.makeLowFileCursor(),cursorMaker.makeQuadrant3rdCursor()};
		Cursor[] cursor7 = {cursorMaker.makeLowRankCursor(),cursorMaker.makeQuadrant3rdCursor()};
		Cursor[] cursor8 = {cursorMaker.makeLowRankCursor(),cursorMaker.makeQuadrant2ndCursor()};
		
		Movement movement = new MoveOnPath();
		
		Rules rules = new AttackRulePostionBlank(new AttackRulePieceSameSide(new AttackRulePieceOpposite(new Judgement(gm))));
		
		MoveMaker moveMaker = new KnightAttackOnMaker(gm,rules);
		
		maneuvers.add(new Maneuver(Arrays.asList(cursor1),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor2),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor3),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor4),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor5),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor6),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor7),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor8),movement,moveMaker));
		
		return maneuvers;
	}

	@Override
	protected List<Maneuver> makeCastling() {
		// TODO Auto-generated method stub
		return new ArrayList<Maneuver>();
	}

	@Override
	protected List<Maneuver> makePromotion() {
		// TODO Auto-generated method stub
		return new ArrayList<Maneuver>();
	}

	@Override
	protected List<Maneuver> makeEmpassant() {
		// TODO Auto-generated method stub
		return new ArrayList<Maneuver>();
	}

}
