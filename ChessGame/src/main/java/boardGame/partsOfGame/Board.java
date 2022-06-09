package boardGame.partsOfGame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Board implements Iterable<Position>{
	private List<String> axisX;
	private List<String> axisY;
	private Map<String,Position> intersections;
	
	public List<String> getAxisX(){
		return axisX;
	}
	public List<String> getAxisY(){
		return axisY;
	}
	
	public Board(List<String>axisX,List<String>axisY) {
		this.axisX = axisX;
		this.axisY = axisY;
		
		intersections = new HashMap<String,Position>();
		for(String letterX:axisX) {
			for(String letterY:axisY) {
				intersections.put(letterX+letterY, new Position(letterX,letterY));
			}
		}
	}
	
	//교차점을 리턴
	public Position getIntersection(String letter) {
		return intersections.get(letter);
	}
	
	@Override
	public Iterator<Position> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Position>() {
			private Iterator<String> iterator = intersections.keySet().iterator(); 
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return iterator.hasNext();
			}

			@Override
			public Position next() {
				// TODO Auto-generated method stub
				return intersections.get(iterator.next());
			}
		};
	}
}
