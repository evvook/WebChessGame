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
	//매니저에서 무브가 실행되면 기물에 알린다
	//기물은 카운트 하다가 특정 상태가 되면 주제에서 이탈?(구독 취소?)
	
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
		
		//초기화 하고 시작
		activeMoves = null;
		while(mi.hasNext()) {
			Moves possibleMoves = mi.next();
			
			if(order.equals(possibleMoves.getTo().getNotation())) {
				activeMoves = possibleMoves;
				
				//테스트용
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
		//실행 전처리
		Position position = activeMoves.getFrom();
		ChessPiece chessPiece = (ChessPiece)position.getOnPiece();
		movedPiecesEachTurn.add(chessPiece);
		//실행
		activeMoves.execute(getBoard());
		//실행 후처리
		getActiveCamp().setCheckState(false);
		selectedPiece = null;
		selectedPieceMovesList.clear();
		
		//다음 차례에 둘 수가 없으면
		if(countOppositeCampUitsMoves() == 0) {
			getOppositeCamp().setCheckState(rm.testCheck(getOppositeCamp()));
			//게임 끝
			if(getOppositeCamp().isCheckState()) {
				//체크메이트
				setGameState("Checkmate");
				winner = getActiveCamp().getName();
			}else {
				//스테일메이트
				setGameState("Stalemate");
			}
			
		}else {
			setGameState("onGoing");
			//게임의 턴을 셈
			countTurn();
			//브로커에게 알림하도록 전달
			broker.notifyGameState();
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		//일단은 select하지 않아서 activeMoves가 남아있으면 undo가능
		Position position = activeMoves.getFrom();
		ChessPiece selectedPiece = (ChessPiece)position.getOnPiece();
		registGameStateSub(selectedPiece);
		
		activeMoves.undo();
		setGameState("undo");
		
		//게임의 턴을 역산
		countTurnReverse();
		//브로커에게 알림하도록 전달
		broker.notifyGameStateRollback();
	}

	//선택된 기물의 가능한 행마를 보여줌
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
		//체스에서는 수를 뒀을 때 반 수를 뒀다고 침. 진짜 수는 서로 한 수씩 뒀을 때 1수를 뒀다고 함
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
}
