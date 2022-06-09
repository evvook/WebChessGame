package boardGame.partsOfGame;

import java.util.List;

import boardGame.cursor.Cursor;
import boardGame.move.Move;
import boardGame.move.MoveMaker;
import boardGame.movement.Movement;

//기동. 기물이 가지고 있는 능력. 경로를 가지고 행마를 만들어냄
public class Maneuver {
	private List<Cursor> cursor;
	private Movement movement;
	private MoveMaker moveMaker;
	private List<Position> path;
	
	public Maneuver(List<Cursor> cursor,Movement movement,MoveMaker moveMaker) {
		this.moveMaker = moveMaker;
		this.cursor = cursor;
		this.movement = movement;
	}
	
	public void setPath(){
		//위치가 바뀔 때 마다 경로는 새로 만들어줘야 함
		if(path != null)
			path.clear();
		path = movement.getPath(cursor);
	}
	
	public List<Move> getMoves(){
		return moveMaker.makeMove(path);
	}
}
