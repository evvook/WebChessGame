package boardGame.movement;

import java.util.ArrayList;
import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.partsOfGame.Position;

public class MoveOnPath implements Movement {

	@Override
	public List<Position> getPath(List<Cursor> cursors) {
		// TODO Auto-generated method stub
		List<Position> path = new ArrayList<Position>();
		try {
			Position point = null;
			for(Cursor cursor:cursors) {
				point = cursor.getPosition();
				if(point == null) {
					//만약 경로대로 이동하다가 보드 밖으로 넘어갈 경우
					//해당 경로는 불완전하므로 경로를 생성하지 않는다.
					path.clear();
					throw new Exception("Cursor is out of board");
				}
			}
			if(point != null) {
				path.add(point);
			}
		}catch(Exception e) {
			//System.out.println(e.toString());
		}
		return path;
	}

}
