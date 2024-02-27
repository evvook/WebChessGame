package service.gameRules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import service.gameparts.Camp;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;

public abstract class RulesManager {
	public abstract MoveFilter getInstanceCommonRulesFilter();
	public abstract MoveFilter getInstanceCheckStateFilter();
	public abstract MoveFilter getInstanceSimpleRuleFilter();
	
	public boolean testCheck(Camp camp) {
		List<Moves> checkMoves = new ArrayList<Moves>();
		
		Piece load = camp.findLordUnit();
		List<Piece> oppositeUnitss = camp.getOppositeCamp().getActiveUnits();
		Iterator<Piece> pi = oppositeUnitss.iterator();
		//����� ��� Ȱ��ȭ ���ֿ� ���ؼ� ����
		MoveFilter filter = getInstanceSimpleRuleFilter();
		while(pi.hasNext()){
			Piece piece = pi.next();
			List<Moves> movesList = piece.getMoves();
			Iterator<Moves> mi = movesList.iterator();
			//�ش� ������ ��� ���� ���ؼ� ����
			while(mi.hasNext()){
				Moves oppositeUnitMoves = mi.next();
				Moves filteredMoves;
				filteredMoves = filter.filter(oppositeUnitMoves);
				if(filteredMoves != null) {
					//ŷ�� ���ݹ޴� ���
					//�ش���� ��ο� ŷ�� �ִٸ�(������ ���ͷ� �ɷ��� ���ݼ� �ܿ��� �ٸ� ������ ������ ��ġ�� ���� �� ����)
					if(load.equals(filteredMoves.getTo().getOnPiece())) {
						//üũ �� ����
						checkMoves.add(filteredMoves);
					}
				}
			}
		}
		//üũ ���� ���ٸ�
		if(checkMoves.size()!=0) {
			return true;
		}else {
			return false;
		}
	}
}
