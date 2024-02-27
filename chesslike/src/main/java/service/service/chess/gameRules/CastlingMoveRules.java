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
			//ŷ�� �� ����������
			Position position = moves.getFrom();
			ChessPiece king = (ChessPiece)position.getOnPiece();
			if(king.isFirstMoves()) {
				
				setDistance(moves);
				//��ĭ �̵��ϴ� �Ÿ� ���(from to ��)
				if(Math.abs(distance) == 2) {
					try {
						setRook(moves);
					}catch(Exception e) {
						//e.printStackTrace();
						System.out.println(e.toString());
						//�� ������ �����ϸ�, �� ���� �� ��
						return null;
					}
					//������ ���� �� ������ ���
					if(isRookUnmoved(moves)) {
						try {
							setDirection();
							//��� ŷ ���̰� ������� Ȯ��
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
							//�Ÿ� ���� Ȯ�� �Ǵ� �⹰ ���̰� ������� Ȯ���ϴٰ� ���ܰ� �߻��� ���
							//�� ���� �� ��
							return null;
						}
					}
					
				}
			}
		}
		return null;
	}

	//�̰� ���п� ��ũ �������� �ʿ� ����
	private boolean isBetweenEmpty(Moves moves) throws Exception {
		// TODO Auto-generated method stub
		Position from = moves.getFrom();
		Position to = rook.getPosition();
		int fromX = (int)from.getLetterX().charAt(0);
		int toX = (int)to.getLetterX().charAt(0);
		
		Position checkPosition = from;
		for(int i = 0; i < Math.abs(toX-fromX)-1; i++) {
			//ŷ���̵� ���밪 3-1=2
			//������Ʈ ���밪 4-1=3
			checkPosition = direction.getNext(checkPosition);
			//�߰��� ������ �ִ� ���
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
		String kingsFile = king.getPosition().getLetterY();//������
		
		if(distance > 0) {
			//king side
			rookPosition = chessBoard.getPosition("h"+kingsFile);
			rookPositionPiece = (ChessPiece)rookPosition.getOnPiece();
			if(rookPositionPiece != null) {
				//�� ��ġ�� �⹰�� ���̰�
				if("R".equals(rookPositionPiece.getRankNotation())) {
					
					//ŷ�� ���� ���� �����̸�
					if(king.getCamp().equals(rookPositionPiece.getCamp())){
						rook = rookPositionPiece;
					}
				}
			}
		}
		if(distance < 0) {
			rookPosition = chessBoard.getPosition("a"+kingsFile);
			rookPositionPiece = (ChessPiece)rookPosition.getOnPiece();
			//�� ��ġ�� �⹰�� ���̰�
			if(rookPositionPiece != null) {
				if("R".equals(rookPositionPiece.getRankNotation())) {
					
					//ŷ�� ���� ���� �����̸�
					if(king.getCamp().equals(rookPositionPiece.getCamp())){
						rook = rookPositionPiece;
					}
				}
			}
		}
		
		if(rook == null) {
			throw new Exception("���� ����");//���� ���� �ϳ� ���������...
		}
	}
	
	private void setDistance(Moves moves) {
		// TODO Auto-generated method stub
		char fromX = moves.getFrom().getLetterX().charAt(0);
		char toX = moves.getTo().getLetterX().charAt(0);
		distance = (int)toX - (int)fromX;
	}


}
