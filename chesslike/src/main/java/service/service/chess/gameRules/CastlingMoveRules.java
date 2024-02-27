package service.chess.gameRules;

import service.chess.gameparts.piece.ChessPiece;
import service.chess.gameparts.piece.moves.ChessMovesType;
import service.gameRules.Rules;
import service.gameparts.Board;
import service.gameparts.Position;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;
import service.gameparts.piece.relativePosition.PositionEast;
import service.gameparts.piece.relativePosition.PositionWest;
import service.gameparts.piece.relativePosition.RelativePosition;

public class CastlingMoveRules extends Rules{
	private int distance;
	private ChessPiece rook;
	private RelativePosition direction;
	private Board chessBoard;
	
	public CastlingMoveRules(MovesType movesType, Board chessBoard) {
		// TODO Auto-generated constructor stub
		super(movesType);
		this.chessBoard = chessBoard;
	}

	@Override
	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		if(moves == null)
			return null;
		MovesType type = moves.getMovesType();
		if(type.equalsType(ChessMovesType.Castling)) {
			//킹이 안 움직였으면
			Position position = moves.getFrom();
			ChessPiece king = (ChessPiece)position.getOnPiece();
			if(king.isFirstMoves()) {
				
				setDistance(moves);
				//두칸 이동하는 거만 골라냄(from to 비교)
				if(Math.abs(distance) == 2) {
					try {
						setRook(moves);
					}catch(Exception e) {
						//e.printStackTrace();
						System.out.println(e.toString());
						//룩 생성에 실패하면, 수 생성 안 함
						return null;
					}
					//진영의 룩이 안 움직인 경우
					if(isRookUnmoved(moves)) {
						try {
							setDirection();
							//룩과 킹 사이가 비었는지 확인
							if(isBetweenEmpty(moves)) {
								if(hasNext()) {
									Rules nextRules = getNextRules();
									return nextRules.judge(moves);
								}else {
									return moves;
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println(e.toString());
							//거리 사이 확인 또는 기물 사이가 비었는지 확인하다가 예외가 발생한 경우
							//수 생성 안 함
							return null;
						}
					}
					
				}
			}
		}
		return null;
	}

	//이거 덕분에 블랭크 포지션이 필요 없음
	private boolean isBetweenEmpty(Moves moves) throws Exception {
		// TODO Auto-generated method stub
		Position from = moves.getFrom();
		Position to = rook.getPosition();
		int fromX = (int)from.getLetterX().charAt(0);
		int toX = (int)to.getLetterX().charAt(0);
		
		Position checkPosition = from;
		for(int i = 0; i < Math.abs(toX-fromX)-1; i++) {
			//킹사이드 절대값 3-1=2
			//퀸사이트 절대값 4-1=3
			checkPosition = direction.getNext(checkPosition);
			//중간에 뭔가가 있는 경우
			if(checkPosition.getOnPiece() != null) {
				return false;
			}
		}
		return true;
	}

	private boolean isRookUnmoved(Moves moves) {
		// TODO Auto-generated method stub
		return rook.isFirstMoves();
	}
	
	private void setDirection() {
		// TODO Auto-generated method stub
		if(distance > 0) {
			//king side castling
			direction = new PositionEast(chessBoard);
		}
		if(distance < 0) {
			//queen side castling
			direction = new PositionWest(chessBoard);
		}
	}	
	
	private void setRook(Moves moves) throws Exception {
		Position rookPosition = null;
		ChessPiece rookPositionPiece = null;
		Position kingPosition = moves.getFrom();
		ChessPiece king = (ChessPiece)kingPosition.getOnPiece();
		String kingsFile = king.getPosition().getLetterY();//세로축
		
		if(distance > 0) {
			//king side
			rookPosition = chessBoard.getPosition("h"+kingsFile);
			rookPositionPiece = (ChessPiece)rookPosition.getOnPiece();
			if(rookPositionPiece != null) {
				//룩 위치의 기물이 룩이고
				if("R".equals(rookPositionPiece.getRankNotation())) {
					
					//킹과 룩이 같은 진영이면
					if(king.getCamp().equals(rookPositionPiece.getCamp())){
						rook = rookPositionPiece;
					}
				}
			}
		}
		if(distance < 0) {
			rookPosition = chessBoard.getPosition("a"+kingsFile);
			rookPositionPiece = (ChessPiece)rookPosition.getOnPiece();
			//룩 위치의 기물이 룩이고
			if(rookPositionPiece != null) {
				if("R".equals(rookPositionPiece.getRankNotation())) {
					
					//킹과 룩이 같은 진영이면
					if(king.getCamp().equals(rookPositionPiece.getCamp())){
						rook = rookPositionPiece;
					}
				}
			}
		}
		
		if(rook == null) {
			throw new Exception("룩이 없음");//전용 예외 하나 만들어주자...
		}
	}
	
	private void setDistance(Moves moves) {
		// TODO Auto-generated method stub
		char fromX = moves.getFrom().getLetterX().charAt(0);
		char toX = moves.getTo().getLetterX().charAt(0);
		distance = (int)toX - (int)fromX;
	}


}
