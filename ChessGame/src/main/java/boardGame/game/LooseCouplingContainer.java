package boardGame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import boardGame.partsOfGame.Camp;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.Position;

public class LooseCouplingContainer {
	private GameMediator gm;
	//간접적으로 의존관계를 만들어줄 컬렉션
	private Map<Camp,Camp> relationOppositeCamp;
	private Map<Piece,Camp> relationPieceAssociateCamp1;
	private Map<Piece,Camp> relationPieceAssociateCamp2;
	private Map<Position,Piece> relationPieceOnPosition;
	private Map<Position,Piece> virtualRelationPieceOnPosition;
	private Camp goingFirstCamp;
	
	public void setGameManager(GameMediator gm) {
		this.gm = gm;
		setBoardPosition();
		relationPieceAssociateCamp1 = new HashMap<Piece, Camp>();
		relationPieceAssociateCamp2 = new HashMap<Piece, Camp>();
	}
	
	//느슨한관계 객체의 세터들
	public void setBoardPosition() {
		Set<Position> allIntersections = gm.getAllBoardIntersections();
		if(relationPieceOnPosition == null) {
			relationPieceOnPosition = new HashMap<Position, Piece>();
			virtualRelationPieceOnPosition = new HashMap<Position, Piece>();
		}
		
		Iterator<Position> iterator = allIntersections.iterator();
		while(iterator.hasNext()){
			relationPieceOnPosition.put(iterator.next(), null);
		}
	}
	
	public void setOppositeRelationCamps(Camp camp1, Camp camp2) {
		if(relationOppositeCamp == null)
			relationOppositeCamp = new HashMap<Camp, Camp>();
		
		relationOppositeCamp.put(camp1, camp2);
		relationOppositeCamp.put(camp2, camp1);
		goingFirstCamp = camp1;
	}
	
	public void involveInCamp(Piece piece) {
		try {
			if(gm.getSelectedCamp() == null) {
				throw new Exception("Select a camp, a piece will involve in");
			}
			//1번 컨테이너에 일치하는 값(진영)이 있는 경우 1번 컨테이너에 값을 넣어준다.
			if(relationPieceAssociateCamp1.containsValue(gm.getSelectedCamp())) {
				relationPieceAssociateCamp1.put(piece, gm.getSelectedCamp());
			}
			//2번 컨테이너에 일치하는 값(진영)이 있는 경우 2번 컨테이너에 값을 넣어준다.
			else if(relationPieceAssociateCamp2.containsValue(gm.getSelectedCamp())) {
				relationPieceAssociateCamp2.put(piece, gm.getSelectedCamp());
			}
			//1번 컨테이너가 빈 경우 1번 컨테이너에 넣어준다.
			else if(relationPieceAssociateCamp1.isEmpty()){
				relationPieceAssociateCamp1.put(piece, gm.getSelectedCamp());
			}
			//2번 컨테이너가 빈 경우 2번 컨테이너에 넣어준다.
			else if(relationPieceAssociateCamp2.isEmpty()) {
				relationPieceAssociateCamp2.put(piece, gm.getSelectedCamp());
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}
	
	//위치의 말을 반환
	public Piece getPieceOnPosition(Position position) {
		// TODO Auto-generated method stub
		return relationPieceOnPosition.get(position);
	}
	
	//기물의 위치를 반환
	public Position getPositionOnPiece(Piece piece) {
		for(Position position:relationPieceOnPosition.keySet()) {
			if(piece==null)
				return null;
			if(piece.equals(relationPieceOnPosition.get(position)))
				return position;
		}
		return null;
	}

	//말을 특정 위치에 둠
	public void setOnPiece(Piece piece) {
		try {
			if(!relationPieceAssociateCamp1.containsKey(piece)
					&&!relationPieceAssociateCamp2.containsKey(piece))
				throw new Exception("This piece does not belongs any camp.");
					
			relationPieceOnPosition.put(gm.getAroundPosition(), piece);
			piece.setPath();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//말의 위치를 변경함
	public void changePiecePosition(Piece piece, Position to) {
		// TODO Auto-generated method stub
		//변경된 위치 설정
		gm.setAroundPosition(to);
		//위치-말 관계 재조정(해당 위치의 말 제거)
		if(getPositionOnPiece(piece) != null) {
			removePiece(getPositionOnPiece(piece));
		}
		//말을 위치에 둠
		setOnPiece(piece);
	}
	
	//위치에 해당하는 말 제거
	public Piece removePiece(Position position) {
		Piece removedPiece = relationPieceOnPosition.get(position);
		relationPieceOnPosition.put(position,null);
		return removedPiece;
	}
	
	public void restorePiece(Position position,Piece piece) {
		relationPieceOnPosition.put(position, piece);
	}
	
	public Camp getOppositeCamp(Camp selectedCamp) {
		// TODO Auto-generated method stub
		return relationOppositeCamp.get(selectedCamp);
	}
	
	//좀더 생각해볼 필요가 있음
	//말이 진영1에 속하는지 확인
	public boolean containsPieceAssociateCamp1(Piece piece) {
		// TODO Auto-generated method stub
		return relationPieceAssociateCamp1.containsKey(piece);
	}
	//진영이 진영1인지 확인
	public boolean equalsCamp1() {
		// TODO Auto-generated method stub
		return relationPieceAssociateCamp1.containsValue(gm.getSelectedCamp());
	}
	//말이 진영2에 속하는지확인
	public boolean containsPieceAssociateCamp2(Piece piece) {
		// TODO Auto-generated method stub
		return relationPieceAssociateCamp2.containsKey(piece);
	}
	//진영이 진영2인지 확인
	public boolean equalsCamp2() {
		// TODO Auto-generated method stub
		return relationPieceAssociateCamp2.containsValue(gm.getSelectedCamp());
	}
	
	public Camp getPieceAssociatedCamp(Piece piece) {
		if(containsPieceAssociateCamp1(piece))
			return relationPieceAssociateCamp1.get(piece);
		if(containsPieceAssociateCamp2(piece))
			return relationPieceAssociateCamp2.get(piece);
		return null;
	}
	
	public void changeActiveCamp() {
		Camp camp = gm.getSelectedCamp();
		camp.setActive(false);
		Camp oppositeCampe = relationOppositeCamp.get(camp);
		oppositeCampe.setActive(true);
		gm.selectCamp(oppositeCampe);
	}

	public List<Piece> getPiecesInvolvedCamp(Camp camp) {
		// TODO Auto-generated method stub
		List<Piece> rList = new ArrayList<Piece>();
		Iterator<Piece> pi = null;
		if(relationPieceAssociateCamp1.containsValue(camp)) {
			pi = relationPieceAssociateCamp1.keySet().iterator();
		}
		if(relationPieceAssociateCamp2.containsValue(camp)) {
			pi = relationPieceAssociateCamp2.keySet().iterator();
		}
		while(pi.hasNext()) {
			rList.add(pi.next());
		}
		return rList;
	}
	
	//가상객체 관련
	public void makeVirtualPiecePosition() {
		//가상 객체 복사
		for(Position key:relationPieceOnPosition.keySet()) {
			virtualRelationPieceOnPosition.put(key, relationPieceOnPosition.get(key));
		}
	}
	public void changeRelationVirtualPiecePosition(Position position, Piece piece) {
		Position piecePosition = null;
		for(Position key:virtualRelationPieceOnPosition.keySet()) {
			if(piece.equals(virtualRelationPieceOnPosition.get(key))){
				piecePosition = key;
			}
		}
		virtualRelationPieceOnPosition.put(piecePosition, null);
		virtualRelationPieceOnPosition.put(position, piece);
	}
	
	public void switchVirtualAndRealPiecePistion() {
		Map<Position,Piece> temp = relationPieceOnPosition;
		relationPieceOnPosition = virtualRelationPieceOnPosition;
		virtualRelationPieceOnPosition = temp;
	}

	public boolean isGoingFirst() {
		// TODO Auto-generated method stub
		return goingFirstCamp.equals(gm.getSelectedCamp());
	}
}
