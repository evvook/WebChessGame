package boardGame.movement;

import java.util.ArrayList;
import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.partsOfGame.Position;

public class MoveToDirection implements Movement {

	@Override
	public List<Position> getPath(List<Cursor> cursors) {
		// TODO Auto-generated method stub
		//예외처리 할 것
		Cursor direction = cursors.get(0);
		
		List<Position> path = new ArrayList<Position>();
		//보드를 벗어나기 전 까지 경로를 생성한다.
		try {
			while(true) {
				Position point = direction.getPosition();
				if(point == null) {
					throw new Exception("Cursor is out of board");
				}
				path.add(point);
			}
			
		}catch (Exception e) {
			//System.out.println(e.toString());
		}
		return path;
	}

}
