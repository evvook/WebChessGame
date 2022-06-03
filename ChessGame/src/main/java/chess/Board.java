package chess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private List<String> axisX;
	private List<String> axisY;
	private Map<String,Intersection> intersections;
	
	public Board(List<String>axisX,List<String>axisY) {
		this.axisX = axisX;
		this.axisY = axisY;
		
		intersections = new HashMap<String,Intersection>();
		for(String letterX:axisX) {
			for(String letterY:axisY) {
				intersections.put(letterX+letterY, new Intersection(letterX,letterY));
			}
		}
	}
	
	//교차점을 리턴
	public Intersection getIntersection(String letter) {
		return intersections.get(letter);
	}
}
