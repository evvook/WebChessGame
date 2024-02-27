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
	protected List<Moves> selectedPieceMovesList;//선택된 기물이 전달한 행마 리스트
	protected MoveFilter filter;
	protected Game game;
	protected RulesManager rm;
	protected GameStateBroker broker;
	protected int gameTurnCounter;
	protected String gameState;
	protected String winner;
	
	protected Piece selectedPiece;//일단 만들어둠
	protected Moves activeMoves;//롤백을 위해 가지고 있음
	
	protected String message;
	
	public GameManager() {
		selectedPieceMovesList = new ArrayList<Moves>();
		broker = new GameStateBroker(this);
	}

	public void setGame(GameSetter gs) {
		gs.setGame(this);
	}
	
	//게임정보 세팅(게임세터로부터 정보 전달받음)
	public void setGameInfo(Game game) {
		// TODO Auto-generated method stub
		this.game = game;
		registGameStateSub(getActiveCamp());
	}
	
	//룰 매니저 등록(주입)
	public void setGameRulesManager(RulesManager rulesManage) {
		//필요한 규칙은 다 만들어 줌
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

	//게이머에게 명령을 받아 기물을 선택함
	public void selectPiece(String order) throws NotExistisPieceException,NotActiveCampUnitException{
		try {
			Position orderedPosition = getBoard().getPosition(order);
			Piece orderedPositionPiece = orderedPosition.getOnPiece();
			if(orderedPositionPiece == null) {
				throw new NotExistisPieceException();
			}
			if(!getActiveCamp().equals(orderedPositionPiece.getCamp())) {
				throw new NotActiveCampUnitException();//활성화된 진영 기물이 아님
				
			}
//		if(selectedPiece != null) {
//			throw new Exception("선택한 기물을 취소하고 다시 선택하세요.");
//		}
			selectedPiece = orderedPositionPiece;
			System.out.println(((ChessPiece)selectedPiece).getMoveCount()+"회 움직임");
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
		//행마를 비움
		//선택된 기물을 취소함
		removeGameStateSub(selectedPiece);
		selectedPiece = null;
		selectedPieceMovesList.clear();
		
		setGameState("unselect");
	}

	
	private void initFilter() {
		this.filter = rm.getInstanceCommonRulesFilter();
	}
	//기물의 행마를 게임룰에 따라 필터링해서 골라줌
	private void filterMoves(List<Moves> pieceMoves){
		selectedPieceMovesList.clear();
		//규칙에 맞게 기물의 행마를 필터링해서 보관함
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
	
	//게이머에게 명령을 받아 수를 둠
	public abstract void move(String order) throws Exception;
	
	public abstract void undo();
	
	//상대 기물이 움직일 수 있는 수를 카운트 해서 보여줌
	public int countOppositeCampUitsMoves(){
		int totalCount = 0;
		List<Piece> units = getOppositeCamp().getActiveUnits();
		Iterator<Piece> pi = units.iterator();
		while(pi.hasNext()) {
			Piece unit = pi.next();
			List<Moves> movesList = unit.getMoves();
			Iterator<Moves> mi = movesList.iterator();
			int pieceMovesCount = 0;

			//로깅을 위한 임시변수
			String campName = unit.getCamp().getName();
			String unitName = unit.getRankName();
			
			//매 유닛마다 필터 초기화
			MoveFilter filter = null;
			//판정을 위한 트릭
			//getOppositeCamp().setCheckState(true);
			//체크를 수행하기 위해 검사 상태로 만듬
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
		//보드 축 정보
		gameContext.setBoardAixs(game.getBoard());
		//위치정보=기물정보=기물선택여부
		gameContext.setPositions(game.getBoard());
		//선택한 기물의 행마 위치정보
		gameContext.setPossibleMovesPositions(selectedPieceMovesList);
		//절달할 메세지
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
