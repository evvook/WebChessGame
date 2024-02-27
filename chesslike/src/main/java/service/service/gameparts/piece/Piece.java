package service.gameparts.piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import service.chess.gameparts.piece.ChessPieceType;
import service.game.GameGoingStateSub;
import service.game.GameStateBroker;
import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.moves.Moves;

public abstract class Piece implements GameGoingStateSub{
	private PieceType pieceType;
	private Position position;
	protected List<Moves> moves;
	private Camp camp;//기물은 진영과 상관없이 생성되어 진영을 주입받는다.
	private boolean activeStatus;//기물이 살아있는지?
	
	private List<PieceMoveObserver> moveObservers;//기물의 위치가 바뀔 때 반응할 옵저버 리스트
	private List<PieceStateObserver> stateObservers;//기물의 상태가 바뀔 때 반응할 옵저버 리스트
	protected GameStateBroker broker;
	
	public Piece(PieceType type, List<Moves> moves) {
		this.pieceType = type;
		this.moves = moves;
		this.activeStatus = true;
		
		moveObservers = new ArrayList<PieceMoveObserver>();
		stateObservers = new ArrayList<PieceStateObserver>();
	}
	
	public boolean isLord() {
		return pieceType.equalsType(ChessPieceType.King);
		
	}
	
	public String getRankName() {
		return pieceType.getRank();
	}

	public String getRankNotation() {
		return pieceType.getNotation();
	}

	public PieceType getPieceType() {
		return pieceType;
	}
	
	//초기화도 포함?
	public void setPosition(Position to) {
		Position from = this.position;
		this.position = to;
		Iterator<PieceMoveObserver> oi = moveObservers.iterator();
		while(oi.hasNext()) {
			PieceMoveObserver o = oi.next();
			o.notifyPiecePosition(this);
		}
		//알림 작업이 끝나면 from을 옵저버 리스트에서 제거
		removeMoveObserver(from);
		removeStateObserver(from);
		//그 위치를 기준으로 To를 설정함
		setMoves();
	}

	public Position getPosition() {
		return position;
	}

	public void setMoves() {
		for(Moves moves:moves) {
			moves.setTo();
		}
	}

	public List<Moves> getMoves(){
		List<Moves> activeMoves = new ArrayList<Moves>();
		Iterator<Moves> mi = moves.iterator();
		while(mi.hasNext()) {
			Moves moves = mi.next();
			if(moves.getTo() != null) {
				activeMoves.add(moves);
			}
		}
		return activeMoves;
	}

	//from과 to가 같으면 같은 moves라고 볼 수 있다.
	//from은 this(기물)의 위치와 같으므로 넘겨줄 필요 없음
	//그 외의 상세 조건은 상속받아(확장하여) 구현
	public abstract boolean existMoves(Moves moves, Position to);
	
	public void setCamp(Camp camp) {
		this.camp = camp;
		this.registStateObserver(camp);
		camp.setUnit(this);
	}

	public Camp getCamp() {
		return camp;
	}

	//옵저버 등록을 위해
	public List<Moves> getAllMoves() {
		// TODO Auto-generated method stub
		return moves;
	}

	public void setActive(boolean status) {
		// TODO Auto-generated method stub
		
		this.activeStatus = status;//상태 부활
		Piece piece = this;
		
		stateObservers.forEach(new Consumer<PieceStateObserver>() {
			@Override
			public void accept(PieceStateObserver o) {
				// TODO Auto-generated method stub
				o.notifyPieceState(piece);
			}
		});
		//기물이 가지고 있던 위치는 굳이 삭제할 필요 없을 것 같음
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return activeStatus;
	}
	
	//옵저버의 서브젝트로서의 기능
	public void registMoveObserver(PieceMoveObserver o) {
		moveObservers.add(o);
	}
	public void removeMoveObserver(PieceMoveObserver o) {
		moveObservers.remove(o);
	}
	public void registStateObserver(PieceStateObserver o) {
		stateObservers.add(o);
	}
	public void removeStateObserver(PieceStateObserver o) {
		stateObservers.remove(o);
	}
	
	public GameStateBroker getGameStateBroker(){
		return this.broker;
	}
}
