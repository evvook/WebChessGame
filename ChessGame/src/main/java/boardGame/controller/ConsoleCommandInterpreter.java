package boardGame.controller;

import java.util.Iterator;
import java.util.List;

import boardGame.game.GameInterface;

public class ConsoleCommandInterpreter implements Interpreter {
	private GameInterface gi;
	private String message;
	private int mode;
	private static final int MODE_NORMAL = 0;
	private static final int MODE_SELECT = 1;
	private static final int MODE_MOVE = 2;
	
	public void setGI(GameInterface gi) {
		this.gi = gi;
		this.mode = MODE_NORMAL;
	}
	@Override
	public void execute(String command) throws Exception{
		// TODO Auto-generated method stub
		this.message = null;
		if("camp".equals(command)) {
			this.message = gi.getCampNameThisTurn();
			
		}else if("pieces".equals(command)) {
			showList(gi.getCampUnit());
			
		}else if("others".equals(command)) {
			showList(gi.getOppositeCampUnit());
			
		}else if("mode".equals(command)) {
			if(this.mode == MODE_NORMAL) {
				this.message = "normal mode";
			}
			if(this.mode == MODE_SELECT) {
				this.message = "select mode";
			}
			if(this.mode == MODE_MOVE) {
				this.message = "move mode";
			}
		}else if(this.mode == MODE_NORMAL) {
			if("close".equals(command)) {
				throw new Exception("close");
			}else if("select".equals(command)) {
				this.mode = MODE_SELECT;
			}
 
		}else if(this.mode == MODE_SELECT) {
			try {
				if("cancel".equals(command)) {
					this.mode = MODE_NORMAL;
				}else {
						gi.selectPiecePosition(command);
						this.mode = MODE_MOVE;
					
				}
			}catch (Exception e) {
				// TODO: handle exception
				this.mode = MODE_SELECT;
				throw new Exception();
			}
		}else if(this.mode == MODE_MOVE) {
			try {
				if("unselect".equals(command)) {
					gi.unselect();
					this.mode = MODE_SELECT;
					
				}else if("moves".equals(command)){
					showList(gi.getPieceMoves());
					
				}else {
					gi.move(command);
					this.mode = MODE_NORMAL;
				}
			}catch (Exception e) {
				this.mode = MODE_MOVE;
				throw new Exception();
			}
		}
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
	
	public void showList(List<String> list) {
		Iterator<String> il = list.iterator();
		while(il.hasNext()) {
			System.out.println(il.next());
		}
	}
}
