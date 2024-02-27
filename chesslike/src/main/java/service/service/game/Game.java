package service.game;

import java.util.ArrayList;
import java.util.List;

import service.gameparts.Board;
import service.gameparts.Camp;

public class Game {
	private Board board;
	private List<Camp> camps;
	
	public Game(Board board,Camp camp1, Camp camp2) {
		this.board = board;
		camps = new ArrayList<Camp>();
		camps.add(camp1);
		camps.add(camp2);
	}
	
	public Board getBoard(){
		return board;
	}
	
	public List<Camp> getCamps(){
		return camps;
	}
}
