package service.game;

import java.util.Map;

import service.gameController.Interpreter;
import service.gameException.ExistsCampUnitException;
import service.gameException.NotActiveCampUnitException;
import service.gameException.NotExistisPieceException;
import service.gameException.NotExistsActiveMovesException;
import service.gameException.NotOnBoardException;

//�ܺο� �޽����� �ְ���� �������̽� ��ü
//�ܺο� ���ӸŴ����� �������ش�
//����������-�������̽� �� ����
//�������̽�-���ӸŴ��� �� ����
public class GameInterface {
	private Interpreter interpreter;
	private GameManager manager;
	
	public GameInterface(GameManager manager,Interpreter interpreter) {
		// TODO Auto-generated constructor stub
		this.manager = manager;
		this.interpreter = interpreter;
		this.interpreter.setGI(this);
	}
	
	
	public void interpret(Map<String,String> input) throws Exception {
		interpreter.execute(input);
	}
	
	public GameInterface takeExecutedGame() {
		// TODO Auto-generated method stub
		return interpreter.getGI();
	}

	public GameContext getGameContext() {
		// TODO Auto-generated method stub
		return this.manager.getGameContext();
	}
	
	public boolean isSelectedPiece(String command) {
		return manager.isSelectedPiece();
	}
	
	public boolean isSelectedPosition(String command){
		try {
			return manager.isSelectedPosition(command);
		} catch (NotOnBoardException e) {
			// TODO Auto-generated catch block
			setMessage(e.getLocalizedMessage());
			return false;
		}
	}
	
	//�� ����(��ġ)
	public void selectPiece(String positionLetter){
		try {
			this.manager.selectPiece(positionLetter);
		} catch (NotExistisPieceException e) {
			// TODO Auto-generated catch block
			setMessage(e.getLocalizedMessage());
		} catch (NotActiveCampUnitException e) {
			// TODO Auto-generated catch block
			setMessage(e.getLocalizedMessage());
		}
	}
	
	//�� ���� ���
	public void unselect() {
		manager.unselect();
	}
	
	//���� ��(�Ұ����� ���� �� ��� ����ó��)
	public void move(String command) throws Exception {
		try {
			manager.move(command);
		}catch(ExistsCampUnitException e) {
			//Ȱ��ȭ�� �⹰�� ������ ���
			//�������
			unselect();
		}catch(NotExistsActiveMovesException e) {
			setMessage(e.getMessage());
		}catch(NotExistisPieceException e) {
			throw e;
		}
	}

	public void undo() {
		// TODO Auto-generated method stub
		if(manager.isExistsActiveMoves()) {
			manager.undo();
		}
	}	
	//���º� ��û
	public void requestDraw() {
		
	}
	
	//�Ͻ�����
	public void pauseGame() {
		
	}
	
	//�Ͻ����� ǰ
	public void unpauseGame() {
		
	}
	
	//���ο� ���� ����
	public void openGame() {
		
	}
	
	//���� ����
	public void closeGame() {
		
	}

	public void setGameState(String state) {
		manager.setGameState(state);
	}
	
	public void setMessage(String message) {
		manager.setMessage(message);
	}
	
	public void clearMessage() {
		manager.setMessage(null);
	}

	public boolean isPossibleToOrder(String key) {
		// TODO Auto-generated method stub
		return manager.getActiveCamp().isCampKey(key);
	}

	public void setActiveCampKey(String key) {
		// TODO Auto-generated method stub
		manager.getActiveCamp().setKey(key);
	}
	
	public void setOppositeCampKey(String key) {
		// TODO Auto-generated method stub
		manager.getOppositeCamp().setKey(key);
	}
}
