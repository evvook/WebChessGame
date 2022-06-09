package boardGame.cursor;

import boardGame.game.GameMediator;

public class CursorMaker {
	private GameMediator gm;
	public CursorMaker(GameMediator gm) {
		this.gm = gm;
	}
	public Cursor makeUpRankCursor() {
		return new UpRankCursor(this.gm);
	}
	public Cursor makeLowRankCursor() {
		return new LowRankCursor(this.gm);
	}
	public Cursor makeUpFileCursor() {
		return new UpFileCursor(this.gm);
	}
	public Cursor makeLowFileCursor() {
		return new LowFileCursor(this.gm);
	}
	public Cursor makeQuadrant1stCursor() {
		return new Quadrant1stCursor(this.gm);
	}
	public Cursor makeQuadrant2ndCursor() {
		return new Quadrant2ndCursor(this.gm);
	}
	public Cursor makeQuadrant3rdCursor() {
		return new Quadrant3rdCursor(this.gm);
	}
	public Cursor makeQuadrant4thCursor() {
		return new Quadrant4thCursor(this.gm);
	}
	
	public Cursor makeOppositeCursor(Cursor cursor) {
		if(cursor.getClass().equals(UpRankCursor.class)) {
			return new LowRankCursor(this.gm);
		}
		if(cursor.getClass().equals(LowRankCursor.class)) {
			return new UpRankCursor(this.gm);
		}
		if(cursor.getClass().equals(UpFileCursor.class)) {
			return new LowFileCursor(this.gm);
		}
		if(cursor.getClass().equals(LowFileCursor.class)) {
			return new UpFileCursor(this.gm);
		}
		if(cursor.getClass().equals(Quadrant1stCursor.class)) {
			return new Quadrant3rdCursor(this.gm);
		}
		if(cursor.getClass().equals(Quadrant2ndCursor.class)) {
			return new Quadrant4thCursor(this.gm);
		}
		if(cursor.getClass().equals(Quadrant3rdCursor.class)) {
			return new Quadrant1stCursor(this.gm);
		}
		if(cursor.getClass().equals(Quadrant4thCursor.class)) {
			return new Quadrant2ndCursor(this.gm);
		}
		return null;
	}
}
