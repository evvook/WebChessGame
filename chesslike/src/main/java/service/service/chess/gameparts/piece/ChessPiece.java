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
	private int relativeTurnCount;//����� �� ī��Ʈ ����. �������� ������ 0, ���� �����̸� 1, ��밡 �����̸� 2->0���� �ʱ�ȭ
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
			//���� Ÿ���� ����, to��ġ�� ������
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
			//������ ���
			this.broker = broker;
		}
		countRelativeTurn();
		//ó�� ��ϵǰ� ��� ���� 1 �����̴�
		//������� 0��� �� ������ ���� �������� ���� �����ߴٴ� ��
		if(relativeTurnCount==0) {
			//������� 2�� ���� ���� �� ���� ������ ���������� �������ش�.
			this.broker.removeGameStateSub(this);
			//�� ���� ������ firstMove�� false�� �ȴ�.
			hasMoved();
			//�� ���� ������ �̵� Ƚ���� �����Ѵ�.
			moveCount += 1;
			System.out.println("�⹰ �̵� Ƚ��: "+moveCount);
			System.out.println(this.getRankName());
		}
	}
	public void notifyGameStateRollback(GameStateBroker broker) {
		//�ѹ� ȣ��Ǹ� �ڽ��� ���̹Ƿ� �⹰�� �ٽ� ������ �� �ֵ��� �������� �����ش�.
		broker.removeGameStateSub(this);
		//��ī��Ʈ
		countRelativeTurnReverse();
		//���� ���������� 1
		//���� �������� �ǵɸ��� 0
		if(relativeTurnCount == 0) {
			if(moveCount > 0) {
				//������ ī��Ʈ ����
				moveCount -= 1;
			}
			System.out.println("�⹰ �̵� Ƚ��: "+moveCount);
			if(moveCount == 0) {
				//������ ī��Ʈ�� 0�̸� ���� �����̹Ƿ� ù �� ���°� �ȴ�.
				firstMove = true;
				System.out.println("ù �� ���·� ����");
			}
		}
	}

	private void hasMoved() {
		//���� �Ŵ���(������Ʈ)�� �������� ó���� �� ���� ������ ����Ұ�
		if(firstMove) {
			System.out.println("ù ���� ��");
		}
		firstMove = false;
	}
}
