package service.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import service.gameparts.Board;
import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.Piece;
import service.gameparts.piece.moves.Moves;

public class GameContext {
	//화면으로 전달할 게임 상황을 json형태로 만들기 위한 객체
	//정보를 컬렉션 형태로 정리하여 전달함
	
	//보드 축 정보
	protected Map<String,List<String>> boardAxis;
	//위치정보=기물정보=기물선택여부
	protected List<Map<String,Object>> positions;
	//선택한 기물의 행마 위치정보
	protected List<Map<String,String>> movesPositions;
	//게임상태
	protected String gameState;
	//승자
	protected String winner;
	
	protected String message;
	
	public void setBoardAixs(Board board) {
		boardAxis = new HashMap<String,List<String>>();
		boardAxis.put("RANK", board.getAxisX());
		boardAxis.put("FILE", board.getAxisY());
	}
	public void setPositions(Board board) {
		Map<String,String> campMap = null;//활성화상태
		Map<String,Object> pieceMap = null;//노테이션,진영
		Map<String,Object> positionMap = null;//노테이션,기물
		positions = new ArrayList<Map<String,Object>>();//위치들
		
		//예시
		//camp.put("activeStatus","ACTIVE");
		//piece.put("notation","K");
		//piece.put("camp",camp);
		//position.put("notation","a1");
		//position.put("piece",piece);
		//positions.add(position);
		
		Iterator<Position> pi = board.getPositions().iterator();
		while(pi.hasNext()) {
			Position position = pi.next();
			Piece piece = position.getOnPiece();
			if(piece != null) {
				Camp camp = piece.getCamp();
				campMap = new HashMap<String,String>();
				campMap.put("name", camp.getName());
				if(camp.isActive()) {
					campMap.put("activeStatus", "ACTIVE");
				}else {
					campMap.put("activeStatus", "INACTIVE");
				}
				pieceMap = new HashMap<String,Object>();
				pieceMap.put("notation", piece.getRankNotation());
				pieceMap.put("camp", campMap);
				
				positionMap = new HashMap<String,Object>();
				positionMap.put("notation", position.getNotation());
				positionMap.put("onPiece", pieceMap);
			}else {
				positionMap = new HashMap<String,Object>();
				positionMap.put("notation", position.getNotation());
				positionMap.put("onPiece", "");
			}
			
			positions.add(positionMap);
		}
	}
	public void setPossibleMovesPositions(List<Moves> possibleMoves) {
		this.movesPositions =  new ArrayList<Map<String,String>>();
		
		Map<String,String> positionMap = null;
		Iterator<Moves> mi = possibleMoves.iterator();
		while(mi.hasNext()){
			Moves moves = mi.next();
			Position position = moves.getTo();
			if(position != null) {
				positionMap = new HashMap<String,String>();//노테이션
				positionMap.put("notation", position.getNotation());
				movesPositions.add(positionMap);
			}
		}
	}
	public void setGameState(String state) {
		this.gameState = state;
	}
	public void setGameWinner(String winner) {
		this.winner = winner;
	}
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}
	
	public Map<String,Object> getContext() {
		// TODO Auto-generated method stub
		Map<String,Object> context = new HashMap<String,Object>();
		context.put("boardAxis", boardAxis);
		context.put("positions", positions);
		context.put("movesPositions", movesPositions);
		if(gameState != null) {
			context.put("gameState", gameState);
			if(winner != null) {
				context.put("winner", winner);
			}
		}
		if(message != null) {
			context.put("message", message);
		}
		return context;
	}
}
