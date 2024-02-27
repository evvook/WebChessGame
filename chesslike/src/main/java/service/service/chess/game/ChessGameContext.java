package service.chess.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import service.chess.gameparts.piece.ChessPiece;
import service.game.GameContext;
import service.gameparts.Board;
import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.Piece;

public class ChessGameContext extends GameContext {

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
				
				ChessPiece chessPiece = (ChessPiece)piece;
				pieceMap.put("specialChar", chessPiece.getSpecialChar());
				
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
	
}
