package chess;

import java.util.ArrayList;
import java.util.List;

public class PointTo implements Movement {
	//특정 위치로 이동하는 경로
	@Override
	public List<Intersection> getPath(List<Cursor> cursors, Intersection current) {
		// TODO Auto-generated method stub
		List<Intersection> path = new ArrayList<Intersection>();
		
		Intersection position = current;
		try {
			for(Cursor cursor:cursors) {
				position = cursor.getPosition(position);
			}
			path.add(position);
		}catch(NullPointerException e){
		}
		return path;
	}

}
