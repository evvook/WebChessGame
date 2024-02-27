package service.gameparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import service.game.GameGoingStateSub;
import service.game.GameStateBroker;
import service.gameparts.piece.Piece;
import service.gameparts.piece.PieceStateObserver;

public class Camp implements PieceStateObserver, GameGoingStateSub{
	private String name;
	private boolean active;
	private boolean goingFirst;
	private boolean checkState;
	private List<Piece> activeUnits;
	private List<Piece> inactiveUnits;
	private Camp oppostieCamp;
	private boolean testState;
	
	protected GameStateBroker broker;
	
	private String campKey;

	public Camp(String name, boolean goingFirst) {
		this.name = name;
		this.activeUnits = new ArrayList<Piece>();
		this.inactiveUnits = new ArrayList<Piece>();
		this.goingFirst = goingFirst;
		this.active = goingFirst;
		this.checkState = false;
		this.testState = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isCheckState() {
		return checkState;
	}

	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}

	public void setTestState(boolean testState) {
		this.testState = testState;
	}
	
	public boolean isTestState() {
		// TODO Auto-generated method stub
		return testState;
	}	
	
	public void setUnit(Piece piece) {
		// TODO Auto-generated method stub
		activeUnits.add(piece);
	}

	public List<Piece> getActiveUnits(){
		return activeUnits;
	}
	
	public Piece findActiveUnit(String pieceNototaion, String piecePosition) {
		// TODO Auto-generated method stub
		Iterator<Piece> pi = activeUnits.iterator();
		while(pi.hasNext()) {
			Piece unit = pi.next();
			if(pieceNototaion.equals(unit.getRankNotation())) {
				if(piecePosition.equals(unit.getPosition().getNotation())) {
					return unit;
				}
			}
		}
		return null;
	}
	
	public List<Piece> findActiveUnit(String pieceNototaion) {
		// TODO Auto-generated method stub
		List<Piece> units = new ArrayList<Piece>();
		Iterator<Piece> pi = activeUnits.iterator();
		while(pi.hasNext()) {
			Piece unit = pi.next();
			if(pieceNototaion.equals(unit.getRankNotation())) {
				units.add(unit);
			}
		}
		return units;
	}

	public void setOppositeCamp(Camp camp) {
		oppostieCamp = camp;
	}
	
	public Camp getOppositeCamp() {
		return oppostieCamp;
	}

	public void setActive() {
		active = true;
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		//게임 매니저의 옵저버로 처리 할 수 있는지 고려(기물(구현체)과 마찬가지)
		return active;
	}
	
	public boolean isGoingFirst() {
		// TODO Auto-generated method stub
		return goingFirst;
	}

	@Override
	public void notifyPieceState(Piece piece) {
		// TODO Auto-generated method stub
		//알림에 따라 활성화-비활성화 기물 컬렉션을 옮김
		if(piece.isActive()) {
			if(inactiveUnits.remove(piece)) {
				activeUnits.add(piece);
				piece.getPosition().setOnPiece(piece);
			}else {
				//예외처리
			}
		}else {
			if(activeUnits.remove(piece)) {
				inactiveUnits.add(piece);
			}else {
				//예외처리
			}
		}
	}

	@Override
	public void notifyGameState(GameStateBroker broker) {
		// TODO Auto-generated method stub
		if(this.broker == null) {
			this.broker = broker;
		}
		//활성화->비활성화
		active = false;
		this.broker.removeGameStateSub(this);
		
		//반대 진영 활성화 및 옵저버 등록
		oppostieCamp.setActive();
		this.broker.registGameStateSub(oppostieCamp);
	}

	@Override
	public void notifyGameStateRollback(GameStateBroker broker) {
		// TODO Auto-generated method stub
		notifyGameState(broker);
	}

	public Piece findLordUnit() {
		// TODO Auto-generated method stub
		Piece lord = null;
		Iterator<Piece> pi = activeUnits.iterator();
		while(pi.hasNext()) {
			Piece piece = pi.next();
			if(piece.isLord()) {
				lord = piece;
			}
		}
		return lord;
	}

	public boolean isCampKey(String key) {
		// TODO Auto-generated method stub
		return key.equals(campKey);
	}

	public void setKey(String key) {
		// TODO Auto-generated method stub
		campKey = key;
	}
}
