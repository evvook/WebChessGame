package chess;

import java.util.ArrayList;
import java.util.List;

public class AttackOnMaker implements MoveMaker {
	private Camp onSide;
	public AttackOnMaker(Camp onSide) {
		this.onSide = onSide;
	}

	@Override
	public List<Move> makeMove(Intersection from, List<Intersection> path) {
		List<Move> AttackOns = new ArrayList<Move>();
		for(Intersection to:path) {
			//조건에 따라 수를 만듬
			//to가 없으면 보드위가 아니므로 수를 생성하지 않는다.
			if(to == null){
				continue;
			}
			//이동할 위치에 다른 말이 있는 경우 이동할 수 없으므로 수를 생성하지 않는다.
			if(to.isBlank()) {
				continue;
			}
			//이동할 위치에 있는 말이 같은 자기 팀인 경우 수를 생성하지 않는다.
			if(onSide.checkBelongs(to.getPiece())) {
				continue;
			}
			AttackOns.add(new AttackOn(from,to));
		}
		return AttackOns;
	}

}
