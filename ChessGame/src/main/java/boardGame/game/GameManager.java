package boardGame.game;

import java.util.Iterator;
import java.util.List;

import boardGame.exceptions.BlankPositionException;
import boardGame.exceptions.ImpossibleMoveException;
import boardGame.exceptions.NotExistsPositionException;

public class GameManager {
	private GameMediator gm;
	public GameManager(GameMediator gm) {
		// TODO Auto-generated constructor stub
		this.gm = gm;
	}
	//게임 그 자체를 관리
	//턴과 턴을 수행할 진영관리(진영 활성화)
	//게임 미디에이터터의 상태 관리
	//인터페이스의 요청이 타당한지 검사
	
	public void changeActiveCamp() {
		gm.changeActiveCamp();
	}
	
	public String getTurnedCampName() {
		// TODO Auto-generated method stub
		return gm.getActiveCampName();
	}

	public List<String> getCampUnit() {
		// TODO Auto-generated method stub
		return gm.getActiveCampUnitsInfo();
	}
	
	public List<String> getOppositeCampUnit() {
		// TODO Auto-generated method stub
		return gm.getOppositeCampUnitsInfo();
	}

	public void unselect() {
		// TODO Auto-generated method stub
		gm.clearPosition();
	}
	
	//활성화된 진영의 말 선택(위치로)
	public void selectPiecePosition(String positionLetter) throws Exception {
		// TODO Auto-generated method stub
		try {
			//입력일 잘못 된 경우
			//1. 보드에 존재하는 위치가 아님
			if(gm.findPosition(positionLetter) == null) {
				throw new NotExistsPositionException();
			}
			//보드위에 존재하는 위치인 경우 가리킴
			gm.selectPosition(positionLetter);
			
			//2. 말이 존재하는 위치가 아님
			if(gm.isCursoredPositionBlank()) {
				throw new BlankPositionException();
			}
			//3. 진영에 속하는 말이 아님
			if(gm.isEnemy()) {
				throw new Exception("This is not your camp's unit");
			}
			
			//4. 진영에 속하는 말인 경우 수를 생성
			//수를 생성하기전에는 항상 전에 생성된 수를 비워줌.
			gm.clearMoves();
			Iterator<String> si = gm.getMoveToLetter().iterator();
			while(si.hasNext()) {
				String moveToLetter = si.next();
				//예외가 발생하더라도 나머지 수에 대해서 모두 실시
				try {
					if(moveToLetter!=null) {
						gatherMoves(moveToLetter);
					}
				}catch (Exception e) {
					// TODO: handle exception
					throw new Exception(e);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
	}

	//활성화된 진영의 말 선택(계급으로)
	public void selectPieceRank(String rank) {
		// TODO Auto-generated method stub
		try {
			//입력일 잘못 된 경우
			//1. 존재하는 계급이 아님
			//2. 보드위에 남아있는 계급의 말이 없음
			//그 외 고려사항
			//1. 존재하는 계급의 말이 2개 이상인 경우
			//체스 기보법에 의하면
			//존재하는 모든 동일 계급의 말을 모두 후보로 둠
			//모든 후보의 행마를 고려함
			//그중 하나를 택해 수를 둠
			//수를 가진 말이 이동하는 것으로 함
			//하나 이상인 경우 출발위치 기록
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<String> getSelectedPieceMoves() {
		// TODO Auto-generated method stub
		return gm.getMoveInfo();
	}
	
	public void move(String toLetter) {
		// TODO Auto-generated method stub
		try {
			//입력일 잘못 된 경우
			//1. 보드에 존재하는 위치가 아님
			if(gm.findPosition(toLetter) == null) {
				throw new NotExistsPositionException();
			}
			//2. 기물의 가능 행마 중에 없는 위치
			if(!gm.isPossibleMove(toLetter)){
				throw new ImpossibleMoveException();
			}
			gm.move(toLetter);
			gm.changeActiveCamp();//공수 교대
			//턴관리, 기보관리 추가
		}catch(NotExistsPositionException e) {
			System.out.println(e.toString());
		}catch(ImpossibleMoveException e) {
			System.out.println(e.toString());
		}catch (Exception e) {
			gm.undo();
			e.printStackTrace();
		}
	}

	public Boolean isCheck() {
		// TODO Auto-generated method stub
		return gm.isChecked();
	}
	
	public void gatherMoves(String toLetter) throws Exception {
		try {
			//입력일 잘못 된 경우
			//1. 보드에 존재하는 위치가 아님
			if(gm.findPosition(toLetter) == null) {
				throw new NotExistsPositionException();
			}
			//2. 기물의 가능 행마 중에 없는 위치
			if(!gm.isPossibleMove(toLetter)){
				throw new ImpossibleMoveException();
			}
			//가상의 수를 움직이며 체크가 아닌 수만 수집한다.
			gm.gatherMoves(toLetter);
			if(!gm.isChecked()) {
				gm.addMove();
			}
			gm.undo();
		}catch (Exception e) {
			throw new Exception(e.toString());
		}
	}
	
//	private void simpleTest(String test) {
//		Piece testPiece = gm.findPiece(test);
//		if(testPiece!=null) {
//			System.out.println("simpleTest: "+testPiece.getRank()+"("+test+")");
//		}else {
//			System.out.println("simpleTest: "+test);
//		}
//	}
	
}
