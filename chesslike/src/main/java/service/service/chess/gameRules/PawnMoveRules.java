package service.chess.gameRules;

import service.chess.gameparts.piece.ChessPiece;
import service.chess.gameparts.piece.ChessPieceType;
import service.chess.gameparts.piece.moves.ChessMovesType;
import service.gameRules.Rules;
import service.gameparts.Camp;
import service.gameparts.Position;
import service.gameparts.piece.PieceType;
import service.gameparts.piece.moves.Moves;
import service.gameparts.piece.moves.MovesType;

public class PawnMoveRules extends Rules{
	
	public PawnMoveRules(MovesType movesType) {
		super(movesType);
	}

	@Override
	public Moves judge(Moves moves) {
		// TODO Auto-generated method stub
		if(moves == null)
			return null;
		MovesType moveType = moves.getMovesType();
		PieceType pieceType = moves.getPieceType();
		//�̵��� ���
		if(pieceType.equalsType(ChessPieceType.Pawn)) {
			//�ܹ��� �̵�
			Moves pawnDirectionMoves = filterDirectionByCamp(moves);
			if(moveType.equalsType(ChessMovesType.Move)) {
				//System.out.println("�� �̵� �� ����!");
				//1. �����̸� Y����, �İ��̸� Y����
				//2. ù �������̸� �� ĭ ������
				Position position = moves.getFrom();
				ChessPiece selectedPiece = (ChessPiece)position.getOnPiece();
				//�ش� �⹰�� ù ��° ���� ���
				if(selectedPiece.isFirstMoves()) {
					if(hasNext()) {
						Rules nextRules = getNextRules();
						return nextRules.judge(pawnDirectionMoves);
					}else {
						return pawnDirectionMoves;
					}
				}else {
					//ù ���� �ƴ� ���
					//ù �� ĭ�� �����δ�.
					if(isBeginning(moves)) {
						if(hasNext()) {
							Rules nextRules = getNextRules();
							return nextRules.judge(pawnDirectionMoves);
						}else {
							return pawnDirectionMoves;
						}
					}
				}
			}
			if(moveType.equalsType(ChessMovesType.Attack)) {
				//�� ĭ�� ������
				if(isBeginning(moves)) {
					//�밢�����θ� ������
					Moves pawnAttackMove = filterDirectionDiagonal(pawnDirectionMoves);
					if(hasNext()) {
						Rules nextRules = getNextRules();
						return nextRules.judge(pawnAttackMove);
					}else {
						return pawnAttackMove;
					}
				}
			}
			return null;
		}
		//���� �ƴ� ���
		//å�� ����
		if(hasNext()) {
			Rules nextRules = getNextRules();
			return nextRules.judge(moves);
		}
		return moves;
	}

	private Moves filterDirectionByCamp(Moves moves) {
		char fromY = moves.getFrom().getLetterY().charAt(0);
		char toY = moves.getTo().getLetterY().charAt(0);
		int direction = (int)toY - (int)fromY;
		
		Camp camp = moves.getFrom().getOnPiece().getCamp();
		if(camp.isGoingFirst()) {
			if(direction>0) {
				return moves;
			}
		}else {
			if(direction<0) {
				return moves;
			}
		}
		return null;
	}
	
	private Moves filterDirectionDiagonal(Moves moves) {
		if(moves != null) {
			char fromX = moves.getFrom().getLetterX().charAt(0);
			char toX = moves.getTo().getLetterX().charAt(0);
			char fromY = moves.getFrom().getLetterY().charAt(0);
			char toY = moves.getTo().getLetterY().charAt(0);
			
			if(Math.abs((int)toX-(int)fromX)==1 && Math.abs((int)toY-(int)fromY)==1) {
				return moves;
			}
		}
		return null;
	}
	


}
