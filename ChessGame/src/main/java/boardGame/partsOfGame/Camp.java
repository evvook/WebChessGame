package boardGame.partsOfGame;

import boardGame.game.GameMediator;

public class Camp {
	private String name;
	private boolean active;
	private GameMediator gm;
	
	public Camp(String name,boolean active,GameMediator gm) {
		this.name = name;
		this.active = active;
		this.gm = gm;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean status) {
		active = status;
	}
}
