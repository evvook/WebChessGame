package chess;

import java.util.ArrayList;
import java.util.List;

public class StraightOn implements Movement {
	//특정 방향으로 계속 이동하는 경로
	@Override
	public List<Intersection> getPath(List<Cursor> cursors, Intersection current) {
		// TODO Auto-generated method stub
		List<Intersection> path = new ArrayList<Intersection>();
		
		Cursor cursor = cursors.get(0);
		Intersection position = current;
		
		while(cursor.getPosition(position)!=null) {
			try {
				position = cursor.getPosition(position);
				path.add(position);
			} catch(NullPointerException e) {
			}
		}

		return path;
	}

}
