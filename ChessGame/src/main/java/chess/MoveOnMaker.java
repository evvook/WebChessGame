package chess;

import java.util.ArrayList;
import java.util.List;

public class MoveOnMaker implements MoveMaker {
	public List<Move> makeMove(Intersection from, List<Intersection> path){
		List<Move> moveOns = new ArrayList<Move>();
		for(Intersection to:path) {
			//조건에 따라 수를 만듬
			//to가 없으면 보드위가 아니므로 수를 생성하지 않는다.
			if(to == null){
				continue;
			}
			//이동할 위치에 다른 말이 있는 경우 이동할 수 없으므로 수를 생성하지 않는다.
			if(!to.isBlank()) {
				continue;
			}
			moveOns.add(new MoveOn(from,to));
		}
		return moveOns;
	}
}
