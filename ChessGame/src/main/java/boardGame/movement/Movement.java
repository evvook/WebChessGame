package boardGame.movement;

import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.partsOfGame.Position;

public abstract interface Movement {
	public List<Position> getPath(List<Cursor> cursors);
}
