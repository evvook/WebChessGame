package service.chess.gameparts.piece;

import java.util.Iterator;
import java.util.List;

import service.game.GameStateBroker;
import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class ChessPiece extends Piece {
	private boolean firstMove;
	private int relativeTurnCount;//상대적 턴 카운트 변수. 선택하지 않으면 0, 내가 움직이면 1, 상대가 움직이면 2->0으로 초기화
	private int moveCount;
	private String SpacialChar;
	
	public ChessPiece(PieceType type, List<Moves> moves) {
		super(type, moves);
		// TODO Auto-generated constructor stub
		this.firstMove = true;
		this.relativeTurnCount = 0;
		this.moveCount = 0;
	}
	
	public void setCamp(Camp camp) {
		ChessPieceType cpt = (ChessPieceType)getPieceType();
		if(camp.isGoingFirst()) {
			SpacialChar = cpt.getSpacialCharGoingFisrtCamp();
		}else{
			SpacialChar = cpt.getSpacialCharOppositeCamp();
		}
		super.setCamp(camp);
	}
	
	public String getSpecialChar() {
		return SpacialChar;
	}
	
	public boolean isFirstMoves() {
		// TODO Auto-generated method stub
		return firstMove;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	@Override
	public boolean existMoves(Moves moves, Position to) {
		// TODO Auto-generated method stub
		MovesType moveType = moves.getMovesType();
		return existMoves(moveType,to);
	}
	
	public boolean existMoves(MovesType type, Position to) {
		// TODO Auto-generated method stub
		Iterator<Moves> mi = this.moves.iterator();
		while(mi.hasNext()) {
			Moves m = mi.next();
			MovesType movesType = m.getMovesType();
			//무브 타입이 같고, to위치가 같은것
//			if((type == movesType.getMovesType()) && to.equals(m.getTo()))
			if((type.equalsType(movesType)) && to.equals(m.getTo()))
				return true;
		}
		return false;
	}

	public void countRelativeTurn() {
		if(relativeTurnCount<2) {
			relativeTurnCount += 1;
		}
		if(relativeTurnCount == 2) {
			relativeTurnCount = 0;
		}
	}
	
	public void countRelativeTurnReverse() {
		relativeTurnCount -= 1;
		if(relativeTurnCount < 0) {
			relativeTurnCount = 1;
		}
	}
	
	public void notifyGameState(GameStateBroker broker) {
		if(this.broker == null) {
			//없으면 등록
			this.broker = broker;
		}
		countRelativeTurn();
		//처음 등록되고 상대 턴은 1 상태이다
		//상대턴이 0라는 건 상대방이 말을 움직여서 수를 진행했다는 것
		if(relativeTurnCount==0) {
			//상대턴이 2번 지나 실제 한 턴이 지나면 옵저버에서 제거해준다.
			this.broker.removeGameStateSub(this);
			//한 턴이 지나면 firstMove는 false가 된다.
			hasMoved();
			//한 턴이 지나면 이동 횟수가 증가한다.
			moveCount += 1;
			System.out.println("기물 이동 횟수: "+moveCount);
			System.out.println(this.getRankName());
		}
	}
	public void notifyGameStateRollback(GameStateBroker broker) {
		//롤백 호출되면 자신의 턴이므로 기물을 다시 선택할 수 있도록 옵저버를 지워준다.
		broker.removeGameStateSub(this);
		//역카운트
		countRelativeTurnReverse();
		//내가 움직였을때 1
		//내가 움직임을 되될리면 0
		if(relativeTurnCount == 0) {
			if(moveCount > 0) {
				//움직임 카운트 차감
				moveCount -= 1;
			}
			System.out.println("기물 이동 횟수: "+moveCount);
			if(moveCount == 0) {
				//움직임 카운트가 0이면 최초 상태이므로 첫 수 상태가 된다.
				firstMove = true;
				System.out.println("첫 수 상태로 복원");
			}
		}
	}

	private void hasMoved() {
		//게임 매니저(서브젝트)의 옵저버로 처리할 수 있지 않은지 고려할것
		if(firstMove) {
			System.out.println("첫 수를 둠");
		}
		firstMove = false;
	}
}
