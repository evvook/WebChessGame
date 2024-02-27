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
	private Camp camp;//�⹰�� ������ ������� �����Ǿ� ������ ���Թ޴´�.
	private boolean activeStatus;//�⹰�� ����ִ���?
	
	private List<PieceMoveObserver> moveObservers;//�⹰�� ��ġ�� �ٲ� �� ������ ������ ����Ʈ
	private List<PieceStateObserver> stateObservers;//�⹰�� ���°� �ٲ� �� ������ ������ ����Ʈ
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
	
	//�ʱ�ȭ�� ����?
	public void setPosition(Position to) {
		Position from = this.position;
		this.position = to;
		Iterator<PieceMoveObserver> oi = moveObservers.iterator();
		while(oi.hasNext()) {
			PieceMoveObserver o = oi.next();
			o.notifyPiecePosition(this);
		}
		//�˸� �۾��� ������ from�� ������ ����Ʈ���� ����
		removeMoveObserver(from);
		removeStateObserver(from);
		//�� ��ġ�� �������� To�� ������
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

	//from�� to�� ������ ���� moves��� �� �� �ִ�.
	//from�� this(�⹰)�� ��ġ�� �����Ƿ� �Ѱ��� �ʿ� ����
	//�� ���� �� ������ ��ӹ޾�(Ȯ���Ͽ�) ����
	public abstract boolean existMoves(Moves moves, Position to);
	
	public void setCamp(Camp camp) {
		this.camp = camp;
		this.registStateObserver(camp);
		camp.setUnit(this);
	}

	public Camp getCamp() {
		return camp;
	}

	//������ ����� ����
	public List<Moves> getAllMoves() {
		// TODO Auto-generated method stub
		return moves;
	}

	public void setActive(boolean status) {
		// TODO Auto-generated method stub
		
		this.activeStatus = status;//���� ��Ȱ
		Piece piece = this;
		
		stateObservers.forEach(new Consumer<PieceStateObserver>() {
			@Override
			public void accept(PieceStateObserver o) {
				// TODO Auto-generated method stub
				o.notifyPieceState(piece);
			}
		});
		//�⹰�� ������ �ִ� ��ġ�� ���� ������ �ʿ� ���� �� ����
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return activeStatus;
	}
	
	//�������� ������Ʈ�μ��� ���
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
