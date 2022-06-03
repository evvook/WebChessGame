package chess;

import java.util.List;

public abstract interface Movement {
	public List<Intersection> getPath(List<Cursor> cursors, Intersection current);
}
