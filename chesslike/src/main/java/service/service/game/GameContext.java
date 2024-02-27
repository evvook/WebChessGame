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
	//ȭ������ ������ ���� ��Ȳ�� json���·� ����� ���� ��ü
	//������ �÷��� ���·� �����Ͽ� ������
	
	//���� �� ����
	protected Map<String,List<String>> boardAxis;
	//��ġ����=�⹰����=�⹰���ÿ���
	protected List<Map<String,Object>> positions;
	//������ �⹰�� �ึ ��ġ����
	protected List<Map<String,String>> movesPositions;
	//���ӻ���
	protected String gameState;
	//����
	protected String winner;
	
	protected String message;
	
	public void setBoardAixs(Board board) {
		boardAxis = new HashMap<String,List<String>>();
		boardAxis.put("RANK", board.getAxisX());
		boardAxis.put("FILE", board.getAxisY());
	}
	public void setPositions(Board board) {
		Map<String,String> campMap = null;//Ȱ��ȭ����
		Map<String,Object> pieceMap = null;//�����̼�,����
		Map<String,Object> positionMap = null;//�����̼�,�⹰
		positions = new ArrayList<Map<String,Object>>();//��ġ��
		
		//����
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
				positionMap = new HashMap<String,String>();//�����̼�
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
