package service.chess.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import service.chess.gameparts.piece.ChessPiece;
import service.chess.gameparts.piece.moves.ChessMovesType;
import service.game.GameContext;
import service.game.GameManager;
import service.gameException.ExistsCampUnitException;
import service.gameException.NotExistsActiveMovesException;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class ChessGameManager extends GameManager {
	private List<ChessPiece> movedPiecesEachTurn;
	private int halfMovesCounter = 0;
	//�Ŵ������� ���갡 ����Ǹ� �⹰�� �˸���
	//�⹰�� ī��Ʈ �ϴٰ� Ư�� ���°� �Ǹ� �������� ��Ż?(���� ���?)
	
	public ChessGameManager() {
		// TODO Auto-generated constructor stub
		movedPiecesEachTurn = new ArrayList<ChessPiece>();
		gameState = "onGoing";
	}

	@Override
	public void move(String order) throws Exception{
		// TODO Auto-generated method stub
		Iterator<Moves> mi = selectedPieceMovesList.iterator();
		Position orderedPosition = getBoard().getPosition(order);
		
		//�ʱ�ȭ �ϰ� ����
		activeMoves = null;
		while(mi.hasNext()) {
			Moves possibleMoves = mi.next();
			
			if(order.equals(possibleMoves.getTo().getNotation())) {
				activeMoves = possibleMoves;
				
				//�׽�Ʈ��
				MovesType thisType = possibleMoves.getMovesType();
				if(thisType.equalsType(ChessMovesType.Move)) {
					System.out.println("move!");
				}
				if(thisType.equalsType(ChessMovesType.Attack)) {
					System.out.println("attack!");
				}
				if(thisType.equalsType(ChessMovesType.Castling)) {
					System.out.println("castling!");
				}
			}
		}
		if(activeMoves == null) {
			if(orderedPosition.getOnPiece() != null) {
				Piece orderedPositionPiece = orderedPosition.getOnPiece();
				if(getActiveCamp().equals(orderedPositionPiece.getCamp())) {
					throw new ExistsCampUnitException();
				}
			}
			throw new NotExistsActiveMovesException();
		}
		//���� ��ó��
		Position position = activeMoves.getFrom();
		ChessPiece chessPiece = (ChessPiece)position.getOnPiece();
		movedPiecesEachTurn.add(chessPiece);
		//����
		activeMoves.execute(getBoard());
		//���� ��ó��
		getActiveCamp().setCheckState(false);
		selectedPiece = null;
		selectedPieceMovesList.clear();
		
		//���� ���ʿ� �� ���� ������
		if(countOppositeCampUitsMoves() == 0) {
			getOppositeCamp().setCheckState(rm.testCheck(getOppositeCamp()));
			//���� ��
			if(getOppositeCamp().isCheckState()) {
				//üũ����Ʈ
				setGameState("Checkmate");
				winner = getActiveCamp().getName();
			}else {
				//�����ϸ���Ʈ
				setGameState("Stalemate");
			}
			
		}else {
			setGameState("onGoing");
			//������ ���� ��
			countTurn();
			//���Ŀ���� �˸��ϵ��� ����
			broker.notifyGameState();
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		//�ϴ��� select���� �ʾƼ� activeMoves�� ���������� undo����
		Position position = activeMoves.getFrom();
		ChessPiece selectedPiece = (ChessPiece)position.getOnPiece();
		registGameStateSub(selectedPiece);
		
		activeMoves.undo();
		setGameState("undo");
		
		//������ ���� ����
		countTurnReverse();
		//���Ŀ���� �˸��ϵ��� ����
		broker.notifyGameStateRollback();
	}

	//���õ� �⹰�� ������ �ึ�� ������
	public void showMovesTo() {
		Iterator<Moves> mi = selectedPieceMovesList.iterator();
		while(mi.hasNext()) {
			Moves thisMoves = mi.next();
			MovesType type = thisMoves.getMovesType();
			String MoveType = null;
			if(type.equalsType(ChessMovesType.Move)) {
				MoveType = "Move";
			}
			if(type.equalsType(ChessMovesType.Attack)) {
				MoveType = "Attack";
				
			}
			if(type.equalsType(ChessMovesType.Castling)) {
				MoveType = "Castling";
				
			}
			Position toPosition = thisMoves.getTo();
			System.out.println(toPosition.getNotation()+"("+MoveType+")");
		}
	}
	
	@Override
	protected void countTurn() {
		// TODO Auto-generated method stub
		//ü�������� ���� ���� �� �� ���� �״ٰ� ħ. ��¥ ���� ���� �� ���� ���� �� 1���� �״ٰ� ��
		halfMovesCounter += 1;
		if(halfMovesCounter == 2) {
			gameTurnCounter += 1;
			halfMovesCounter = 0;
		}
	}
	

	protected void countTurnReverse() {
		// TODO Auto-generated method stub
		halfMovesCounter -= 1;
		if(halfMovesCounter < 0) {
			gameTurnCounter -= 1;
			halfMovesCounter = 1;
		}
	}
	
	public List<ChessPiece> getMovedPieces(){
		return movedPiecesEachTurn;
	}
	
	public GameContext getGameContext() {
		// TODO Auto-generated method stub
		GameContext gameContext = new ChessGameContext();
		//���� �� ����
		gameContext.setBoardAixs(game.getBoard());
		//��ġ����=�⹰����=�⹰���ÿ���
		gameContext.setPositions(game.getBoard());
		//������ �⹰�� �ึ ��ġ����
		gameContext.setPossibleMovesPositions(selectedPieceMovesList);
		//������ �޼���
		gameContext.setMessage(message);
		gameContext.setGameState(gameState);
		return gameContext;
	}
}
