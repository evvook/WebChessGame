package service.gameparts;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import service.gameException.NotOnBoardException;

public class Board {
	private List<String> AxisX;
	private List<String> AxisY;
	private Map<String,Position> positions;
	public Board(List<String> AxisX, List<String> AxisY) {
		this.AxisX = AxisX;
		this.AxisY = AxisY;
		//포지션 생성
		positions = new HashMap<String, Position>();
		Iterator<String> x = AxisX.iterator();
		while(x.hasNext()) {
			String letterX = x.next();
			Iterator<String> y = AxisY.iterator();
			while(y.hasNext()) {
				String letterY = y.next();
				positions.put(letterX+letterY, new Position(letterX,letterY));
			}
		}
	}
	public List<String> getAxisX(){
		return this.AxisX;
	}
	public List<String> getAxisY(){
		return this.AxisY;
	}
	
	public Position getPosition(String positionLetter) throws NotOnBoardException {
		//입력이 포지션 중에 있는지 확인
		if(!positions.containsKey(positionLetter))
			throw new NotOnBoardException(positionLetter);
		return positions.get(positionLetter);
	}
	
	public Collection<Position> getPositions(){
		Collection<Position> pl = positions.values();
		return pl;
	}

}
