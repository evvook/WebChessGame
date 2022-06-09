package boardGame.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import boardGame.move.AttackOn;
import boardGame.move.Move;
import boardGame.partsOfGame.Board;
import boardGame.partsOfGame.Camp;
import boardGame.partsOfGame.Piece;
import boardGame.partsOfGame.Position;

public class GameMediator {
	//mediator 같은 역할을 맡을 클래스
	//다음 클래스간에 연결을(메세지) 담당하여 결합을 약화시켜줌
	//	Cursor
	//		CursorMaker
	//		Cursor
	//	Game
	//		PieceAroundPosition
	//		LooseCouplingContainer
	//		PieceMaker
	//	Move
	//		MoveMaker
	//		Move
	//	PartOfGame
	//		Camp
	//		Piece
	//	Rules
	//		Rules
	
	private Board chessBoard;
	private Camp selectedCamp;
	private LooseCouplingContainer lcc;
	private PieceAroundPosition paPosition;
	private Move move;
	private Move rollback;
	private List<Move> moves;
	
	public GameMediator(Board chessBoard, LooseCouplingContainer lcc) {
		this.chessBoard = chessBoard;
		this.lcc = lcc;
		this.paPosition = new PieceAroundPosition(this);
	}
	
	//보드의 모든 위치를 순환하여 찾은 후 리턴하는 메서드
	public Set<Position> getAllBoardIntersections() {
		Iterator<Position> boardIterator = chessBoard.iterator();
		Set<Position> allIntersections = new HashSet<Position>();
		while(boardIterator.hasNext()) {
			allIntersections.add(boardIterator.next());
		}
		return allIntersections;
	}
	
	//gm이 바라보고있는 위치 리턴
	public Position getCenterPosition() {
		// TODO Auto-generated method stub
		return this.paPosition.getCenterPosition();
	}
	
	//gm이 바라보고있는 위치 리턴
	public Position getAroundPosition() {
		// TODO Auto-generated method stub
		return this.paPosition.getAroundPosition();
	}

	//gm이 바라볼 위치 설정
	public Position getIntersection(String letter) {
		// TODO Auto-generated method stub
		return chessBoard.getIntersection(letter);
	}
	
	//gm이 바라보고 있는 위치가 비어있는지 확인
	public boolean isCursoredPositionBlank() {
		// TODO Auto-generated method stub
		if(paPosition.isEmpty()) {
			return true;
		}else {
			return lcc.getPieceOnPosition(paPosition.getAroundPosition()) == null;
		}
	}
	
	//gm이 바라보고 있는 위치의 기물과 gm이 선택한 진영이 다른지 확인
	//예를 들어 기물의 수를 평가할 때, 평가할 기물이 속한 진영과 커서가 바라보는 위치의 기물의 진영이 다를 수 있다.
	//더 구체적으로 설명하자면, 자살수 방지 체크할 때도 사용된다고 보면 됨
	//해당 기물이 움직였을 때, 상대편 기물의 수를 수집해서 체크가 나는지 판정해야함
	public boolean isEnemy() {
		// TODO Auto-generated method stub
		Piece cursoredPiece = getPieceAroundPosition();
		
		if(lcc.containsPieceAssociateCamp1(cursoredPiece)) {
			//기물이 진영1에 속하고 gm이 선택한 진영과 다를때
			return !lcc.equalsCamp1();
		}
		if(lcc.containsPieceAssociateCamp2(cursoredPiece)) {
			//기물이 진영2에 속하고 gm이 선택한 진영과 다를때
			return !lcc.equalsCamp2();
		}
				
		//이건 개선의 여지가 있음
		//예외처리 필요
		//1. 위치에 말이 없는 경우
		//2. 말이 속한 진영이 없는 경우 정상 동작 하지 않음
		return false;
	}
	
	//위치의 말의 랭크를 반환
	public String getPieceRankOnPosition(Position position) {
		try {
			Piece piece = lcc.getPieceOnPosition(position);
			if(piece == null)
				throw new Exception("There are any pieces.");
			return lcc.getPieceOnPosition(position).getRank();
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	//기물 위치의 문자를 반환
	public String getPositionLetterOnPiece(Piece piece) {
		Position position = lcc.getPositionOnPiece(piece);
		if(position == null) {
			return null;
		}
		return position.getLetter();
	}
	
	//gm이 바라보는 위치의 기물 반환
	public Piece getPieceCenterPosition() {
		return lcc.getPieceOnPosition(paPosition.getCenterPosition());
	}
	public Piece getPieceAroundPosition() {
		return lcc.getPieceOnPosition(paPosition.getAroundPosition());
	}
	
	//gm이 바라보고 있는 기물의 위치를 넘겨받은 위치로 변경함
	public void changePiecePosition(Position to) {
		lcc.changePiecePosition(getPieceCenterPosition(), to);
	}
	
	//넘겨받은 기물의 위치로 gm이 바라보는 위치 변경
	public void curseredPositionOnPiece(Piece piece) {
		selectPosition(getPositionLetterOnPiece(piece));
	}
	
	public void initCursoredPosition() {
		paPosition.init();
	}
	
	public void clearPosition() {
		paPosition.clear();
	}
	
	public Piece removePiece(Position position) {
		return lcc.removePiece(position);
	}
	
	public void restorePiece(Position position,Piece piece) {
		lcc.restorePiece(position, piece);
	}
	
	//선택한 진영 반환
	public Camp getSelectedCamp() {
		return selectedCamp;
	}
	
	//선택한 진영의 상대 진영을 전달
	public Camp getOppositCamp() {
		return lcc.getOppositeCamp(selectedCamp);
	}
	
	
	
	//lcc와 협업
	//lcc에 진영 설정
	public void setCamps(Camp camp1, Camp camp2) {
		// TODO Auto-generated method stub
		lcc.setOppositeRelationCamps(camp1, camp2);
	}
	//진영에 기물을 소속시킴
	public void setPieceInvolveCamp(Piece piece) {
		// TODO Auto-generated method stub
		lcc.involveInCamp(piece);
	}
	//위치에 기물을 놓음
	public void setOnPiece(Piece piece) {
		lcc.setOnPiece(piece);
	}
	
	
	
	//GM의 인터페이스의 메서드
	//위치 선택
	
	//초기화시 사용
	public void selectInitPosition(String letter) {
		paPosition.setInitPosition(findPosition(letter));
	}
	
	//초기화가 아닌 경우 사용
	public void selectPosition(String letter) {
		paPosition.setPosition(findPosition(letter));
	}
	
	public void setAroundPosition(Position position) {
		paPosition.setAroundPosition(position);
	}
	
	public boolean isMoveAround() {
		return paPosition.isMovedAraound();
	}
	
	public Position findPosition(String letter) {
		return chessBoard.getIntersection(letter);
	}
	
	public Piece findPiece(String letter) {
		return lcc.getPieceOnPosition(findPosition(letter));
	}
	
	//진영 선택
	public void selectCamp(Camp camp) {
		selectedCamp = camp;
	}
	
	public void changeActiveCamp() {
		lcc.changeActiveCamp();
	}

	public String getActiveCampName() {
		//현재 선택된 진영이 활성화 되어있지 않다면
		if(!selectedCamp.isActive())
			selectedCamp = lcc.getOppositeCamp(selectedCamp);//진영을 전환하고 리턴
		return selectedCamp.getName();
	}

	public List<String> getCampUnitsInfo(Camp camp) {
		List<String> unitsData = new ArrayList<String>();
		List<Piece> activeCampUnits = lcc.getPiecesInvolvedCamp(camp);
		
		Iterator<Piece> pi = activeCampUnits.iterator();
		while(pi.hasNext()){
			Piece unit = pi.next();
			String rank = unit.getRank();
			String positionLetter = getPositionLetterOnPiece(unit);
			String data = camp.getName()+"_"+rank+"("+(positionLetter==null?"captured":positionLetter)+")";
			unitsData.add(data);
		}
		
		return unitsData;
	}
	
	public List<String> getActiveCampUnitsInfo() {
		// TODO Auto-generated method stub
		//현재 선택된 진영이 활성화 되어있지 않다면
		if(!selectedCamp.isActive())
			selectedCamp = lcc.getOppositeCamp(selectedCamp);//진영을 전환하고 리턴
		
		return getCampUnitsInfo(selectedCamp);
	}
	
	public List<String> getOppositeCampUnitsInfo() {
		return getCampUnitsInfo(getOppositCamp());
	}

	public List<Move> getMove(){
		Piece cursoredPiece = getPieceCenterPosition();
		return cursoredPiece.getMoves();
	}
	
	public List<String> getMoveToLetter(){
		List<Move> moves = getMove();
		List<String> movesTo = new ArrayList<String>();
		Iterator<Move> moveIterator = moves.iterator();
		while(moveIterator.hasNext()) {
			Move move = moveIterator.next();
			movesTo.add(move.getToPositionLetter());
		}
		return movesTo;
	}
	
	public List<String> getMoveInfo() {
		// TODO Auto-generated method stub
		String rank = getPieceCenterPosition().getRank();
		
		List<Move> moves = this.moves;
		List<String> movesData = new ArrayList<String>();
		if(this.moves == null) {
			return movesData;
		}
		Iterator<Move> moveIterator = moves.iterator();
		while(moveIterator.hasNext()) {
			String moveTo = moveIterator.next().getToPositionLetter();
			movesData.add(moveTo+"("+rank+")");
		}
		return movesData;
	}
	
	public boolean isPossibleMove(String toLetter) {
		// TODO Auto-generated method stub
		boolean result = false;
		List<Move> moves = getMove();
		Iterator<Move> moveIterator = moves.iterator();
		while(moveIterator.hasNext()) {
			Move move = moveIterator.next();
			if(toLetter.equals(move.getToPositionLetter())) {
				result = true;
			}
		}
		return result;
	}
	
	public void gatherMoves(String toLetter) {
		if(this.moves == null) {
			this.moves = new ArrayList<Move>();
		}
		List<Move> moves = getMove();
		Iterator<Move> moveIterator = moves.iterator();
		while(moveIterator.hasNext()) {
			Move move = moveIterator.next();
			if(toLetter.equals(move.getToPositionLetter())) {
				this.move = move;
				this.rollback = move.move();
				clearPosition();
			}
		}
	}
	
	public void move(String toLetter) {
		try {
			if(this.moves == null)
				throw new Exception("Abnormal call move");
			List<Move> moves = this.moves;
			Iterator<Move> moveIterator = moves.iterator();
			while(moveIterator.hasNext()) {
				Move move = moveIterator.next();
				if(toLetter.equals(move.getToPositionLetter())) {
					move.move();
					clearPosition();
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("moveReal: "+e.toString());
		}
	}
	
//	public void helpDebug(Position to) {
//		Position from = paPosition.getCenterPosition();
//		Piece onPiece = lcc.getPieceOnPosition(from);
//		Camp onCamp = lcc.getPieceAssociatedCamp(onPiece);
//		String other = getPieceRankOnPosition(to);
//		if(other != null) {
//			System.out.println(onCamp.getName()+"_"+onPiece.getRank()+" from:"+from.getLetter()+" to:"+to.getLetter()+"("+other+")");
//		}else {
//			System.out.println(onCamp.getName()+"_"+onPiece.getRank()+" from:"+from.getLetter()+" to:"+to.getLetter());
//		}
//	}
	
	public boolean isChecked() {
		
		//상대의 모든 말을 순회하며 공격수를 모은다.
		List<Move> attacks = new ArrayList<Move>();
		List<Piece> oppositeCampUnits = lcc.getPiecesInvolvedCamp(getOppositCamp());
		Iterator<Piece> pi = oppositeCampUnits.iterator();
		selectedCamp = getOppositCamp();
		while(pi.hasNext()){
			Piece piece = pi.next();
			if(getPositionLetterOnPiece(piece)==null)
				continue;
			selectPosition(getPositionLetterOnPiece(piece));
			List<Move> allMove = getMove();
			
			Iterator<Move> mi = allMove.iterator();
			while(mi.hasNext()) {
				Move move = mi.next();
				if(move.getClass() == AttackOn.class) {
					attacks.add(move);
				}
					
			}
		}
		selectedCamp = getOppositCamp();
		if(attacks.size()==0) {
			return false;
		}
		
		//진영의 킹을 찾아라
		List<Piece> myCampUnits = lcc.getPiecesInvolvedCamp(selectedCamp);
		Iterator<Piece> mpi = myCampUnits.iterator();
		Piece king = null;
		while(mpi.hasNext()){
			Piece piece = mpi.next();
			if("KING".equals(piece.getRank())) {
				king = piece;
				break;
			}
		}
		
		//공격수 중에 진영의 킹이 있는 경우
		Iterator<Move> mi = attacks.iterator();
		while(mi.hasNext()){
			if(mi.next().getToPositionLetter().equals(getPositionLetterOnPiece(king))) {
				return true;
			}
		}
		return false;
	}
	
	public void undo() {
		// TODO Auto-generated method stub
		try {
			if(rollback == null) {
				throw new Exception("rollback is null");
			}
			rollback.move();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}

	public void addMove() {
		// TODO Auto-generated method stub
		moves.add(move);
	}
	public void clearMoves() {
		if(moves != null) {
			moves.clear();
			move = null;
			rollback = null;
		}
	}

	public boolean isGoingFirst() {
		// TODO Auto-generated method stub
		return lcc.isGoingFirst();
	}

	public void setGoingFirstCamp() {
		// TODO Auto-generated method stub
		if(!isGoingFirst())
			selectedCamp = lcc.getOppositeCamp(selectedCamp);
	}
}
