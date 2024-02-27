package service.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import service.chess.gameparts.piece.ChessPiece;
import service.gameException.NotActiveCampUnitException;
import service.gameException.NotExistisPieceException;
import service.gameException.NotOnBoardException;
import service.gameRules.MoveFilter;
import service.gameRules.RulesManager;
import service.gameparts.Board;
import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;

public abstract class GameManager implements GameGoingStatePub{
	protected List<Moves> selectedPieceMovesList;//���õ� �⹰�� ������ �ึ ����Ʈ
	protected MoveFilter filter;
	protected Game game;
	protected RulesManager rm;
	protected GameStateBroker broker;
	protected int gameTurnCounter;
	protected String gameState;
	protected String winner;
	
	protected Piece selectedPiece;//�ϴ� ������
	protected Moves activeMoves;//�ѹ��� ���� ������ ����
	
	protected String message;
	
	public GameManager() {
		selectedPieceMovesList = new ArrayList<Moves>();
		broker = new GameStateBroker(this);
	}

	public void setGame(GameSetter gs) {
		gs.setGame(this);
	}
	
	//�������� ����(���Ӽ��ͷκ��� ���� ���޹���)
	public void setGameInfo(Game game) {
		// TODO Auto-generated method stub
		this.game = game;
		registGameStateSub(getActiveCamp());
	}
	
	//�� �Ŵ��� ���(����)
	public void setGameRulesManager(RulesManager rulesManage) {
		//�ʿ��� ��Ģ�� �� ����� ��
		this.rm = rulesManage;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Board getBoard() {
		return game.getBoard();
	}
	
	public Camp getActiveCamp() {
		List<Camp>camps = game.getCamps();
		for(Camp camp:camps) {
			if(camp.isActive()) {
				return camp;
			}
		}
		return null;
	}
	
	public Camp getOppositeCamp() {
		if(getActiveCamp()!=null) {
			return getActiveCamp().getOppositeCamp();
		}
		return null;
	}

	//���̸ӿ��� ����� �޾� �⹰�� ������
	public void selectPiece(String order) throws NotExistisPieceException,NotActiveCampUnitException{
		try {
			Position orderedPosition = getBoard().getPosition(order);
			Piece orderedPositionPiece = orderedPosition.getOnPiece();
			if(orderedPositionPiece == null) {
				throw new NotExistisPieceException();
			}
			if(!getActiveCamp().equals(orderedPositionPiece.getCamp())) {
				throw new NotActiveCampUnitException();//Ȱ��ȭ�� ���� �⹰�� �ƴ�
				
			}
//		if(selectedPiece != null) {
//			throw new Exception("������ �⹰�� ����ϰ� �ٽ� �����ϼ���.");
//		}
			selectedPiece = orderedPositionPiece;
			System.out.println(((ChessPiece)selectedPiece).getMoveCount()+"ȸ ������");
			initFilter();
			filterMoves(selectedPiece.getMoves());
			registGameStateSub(selectedPiece);
			
			setGameState("onGoing");
			
			if(selectedPieceMovesList.size() == 0) {
				unselect();
			}
		}catch(NotOnBoardException e) {
			e.printStackTrace();
		}
	}

	public void unselect() {
		//�ึ�� ���
		//���õ� �⹰�� �����
		removeGameStateSub(selectedPiece);
		selectedPiece = null;
		selectedPieceMovesList.clear();
		
		setGameState("unselect");
	}

	
	private void initFilter() {
		this.filter = rm.getInstanceCommonRulesFilter();
	}
	//�⹰�� �ึ�� ���ӷ꿡 ���� ���͸��ؼ� �����
	private void filterMoves(List<Moves> pieceMoves){
		selectedPieceMovesList.clear();
		//��Ģ�� �°� �⹰�� �ึ�� ���͸��ؼ� ������
		Moves filteredMoves = null;
		for(Moves moves:pieceMoves) {
			if(moves.getTo()!=null) {
				//test(pieceMoves);
				filteredMoves = filter.filter(moves);
				if(filteredMoves != null) {
					selectedPieceMovesList.add(filteredMoves);
				}
			}
		}
	}	
	
	//���̸ӿ��� ����� �޾� ���� ��
	public abstract void move(String order) throws Exception;
	
	public abstract void undo();
	
	//��� �⹰�� ������ �� �ִ� ���� ī��Ʈ �ؼ� ������
	public int countOppositeCampUitsMoves(){
		int totalCount = 0;
		List<Piece> units = getOppositeCamp().getActiveUnits();
		Iterator<Piece> pi = units.iterator();
		while(pi.hasNext()) {
			Piece unit = pi.next();
			List<Moves> movesList = unit.getMoves();
			Iterator<Moves> mi = movesList.iterator();
			int pieceMovesCount = 0;

			//�α��� ���� �ӽú���
			String campName = unit.getCamp().getName();
			String unitName = unit.getRankName();
			
			//�� ���ָ��� ���� �ʱ�ȭ
			MoveFilter filter = null;
			//������ ���� Ʈ��
			//getOppositeCamp().setCheckState(true);
			//üũ�� �����ϱ� ���� �˻� ���·� ����
			getOppositeCamp().setTestState(true);
			if(getOppositeCamp().isTestState()) {
				filter = rm.getInstanceCheckStateFilter();
			}else {
				filter = rm.getInstanceCommonRulesFilter();
			}
			while(mi.hasNext()) {
				Moves moves = mi.next();
				if(filter.filter(moves) != null) {
					++pieceMovesCount;
					//System.out.println(moves.getTo().getNotation());
					
				}
			}
			System.out.println(campName+"_"+unitName+"_("+pieceMovesCount+")");
			totalCount += pieceMovesCount;
		}
		return totalCount;
	}	
	
	public void showActiveCampUnitsInfo() {
		// TODO Auto-generated method stub
		Iterator<Piece> pi = getActiveCamp().getActiveUnits().iterator();
		while(pi.hasNext()) {
			Piece piece = pi.next();
			String positionLetter = null;
			if(piece.getPosition()!=null) {
				positionLetter = piece.getPosition().getNotation();
			}
			System.out.println(piece.getRankName()+"("+positionLetter+")");
		}
	}

	public void showOppositeCampUnitsInfo() {
		// TODO Auto-generated method stub
		Iterator<Piece> pi = getActiveCamp().getOppositeCamp().getActiveUnits().iterator();
		while(pi.hasNext()) {
			Piece piece = pi.next();
			String positionLetter = null;
			if(piece.getPosition()!=null) {
				positionLetter = piece.getPosition().getNotation();
			}
			System.out.println(piece.getRankName()+"("+positionLetter+")");
		}
	}

	public void showAllUnitsInfo() {
		// TODO Auto-generated method stub
		Iterator<Position> pi = getBoard().getPositions().iterator();
		while(pi.hasNext()) {
			Position position = pi.next();
			String pieceLetter = null;
			if(position.getOnPiece()!=null) {
				pieceLetter = position.getOnPiece().getRankName();
				System.out.println(position.getNotation()+"("+pieceLetter+")");
			}
		}
	}

	protected abstract void countTurn();
	
	protected abstract void countTurnReverse();

	public void registGameStateSub(GameGoingStateSub s) {
		broker.registGameStateSub(s);
	}
	public void removeGameStateSub(GameGoingStateSub s) {
		broker.removeGameStateSub(s);
	}

	public GameContext getGameContext() {
		// TODO Auto-generated method stub
		GameContext gameContext = new GameContext();
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

	public boolean isSelectedPiece() {
		// TODO Auto-generated method stub
		return selectedPiece != null;
	}

	public boolean isSelectedPosition(String command) throws NotOnBoardException {
		// TODO Auto-generated method stub
		return getBoard().getPosition(command).equals(selectedPiece.getPosition());
	}
	
	public boolean isExistsActiveMoves() {
		return activeMoves != null;
	}

	public void setGameState(String state) {
		// TODO Auto-generated method stub
		gameState = state;
	}
}
