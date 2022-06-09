package boardGame.game.chess.pieceMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.cursor.CursorMaker;
import boardGame.game.GameMediator;
import boardGame.move.AttackOnMaker;
import boardGame.move.MoveMaker;
import boardGame.move.MoveOnMaker;
import boardGame.movement.MoveToDirection;
import boardGame.movement.Movement;
import boardGame.partsOfGame.Maneuver;
import boardGame.rules.AttackRulePieceOpposite;
import boardGame.rules.AttackRulePieceSameSide;
import boardGame.rules.AttackRulePostionBlank;
import boardGame.rules.Judgement;
import boardGame.rules.MoveRulePostionBlank;
import boardGame.rules.Rules;

public class RookMaker extends ChessPieceMaker {

	public RookMaker(CursorMaker cursorMaker, GameMediator gm) {
		super(cursorMaker, gm);
		// TODO Auto-generated constructor stub
		this.rank = "ROOK";
	}

	@Override
	protected List<Maneuver> makeMoveOn() {
		// TODO Auto-generated method stub
		List<Maneuver> maneuvers = new ArrayList<Maneuver>();
		
		Cursor[] cursor1 = {cursorMaker.makeUpFileCursor()};
		Cursor[] cursor3 = {cursorMaker.makeUpRankCursor()};
		Cursor[] cursor5 = {cursorMaker.makeLowFileCursor()};
		Cursor[] cursor7 = {cursorMaker.makeLowRankCursor()};
		
		Movement movement = new MoveToDirection();
		
		Rules rules = new MoveRulePostionBlank(new Judgement(gm));
		
		MoveMaker moveMaker = new MoveOnMaker(gm,rules);
		
		maneuvers.add(new Maneuver(Arrays.asList(cursor1),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor3),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor5),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor7),movement,moveMaker));
		
		return maneuvers;
	}

	@Override
	protected List<Maneuver> makeAttackOn() {
		// TODO Auto-generated method stub
		List<Maneuver> maneuvers = new ArrayList<Maneuver>();
		
		Cursor[] cursor1 = {cursorMaker.makeUpFileCursor()};
		Cursor[] cursor3 = {cursorMaker.makeUpRankCursor()};
		Cursor[] cursor5 = {cursorMaker.makeLowFileCursor()};
		Cursor[] cursor7 = {cursorMaker.makeLowRankCursor()};
		
		Movement movement = new MoveToDirection();
		
		Rules rules = new AttackRulePostionBlank(new AttackRulePieceSameSide(new AttackRulePieceOpposite(new Judgement(gm))));
		
		MoveMaker moveMaker = new AttackOnMaker(gm,rules);
		
		maneuvers.add(new Maneuver(Arrays.asList(cursor1),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor3),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor5),movement,moveMaker));
		maneuvers.add(new Maneuver(Arrays.asList(cursor7),movement,moveMaker));
		
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
