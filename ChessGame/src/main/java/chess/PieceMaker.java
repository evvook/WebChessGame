package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceMaker {
	public static Piece makeKnight(Board chessBoard, Camp onSide) {
		
		Cursor[] move1 = {new UpRankCursor(chessBoard),new Quadrant1stCursor(chessBoard)};
		Cursor[] move2 = {new UpRankCursor(chessBoard),new Quadrant4thCursor(chessBoard)};
		Cursor[] move3 = {new LowFileCursor(chessBoard),new Quadrant4thCursor(chessBoard)};
		Cursor[] move4 = {new LowFileCursor(chessBoard),new Quadrant3rdCursor(chessBoard)};
		Cursor[] move5 = {new LowRankCursor(chessBoard),new Quadrant3rdCursor(chessBoard)};
		Cursor[] move6 = {new LowRankCursor(chessBoard),new Quadrant2ndCursor(chessBoard)};
		Cursor[] move7 = {new UpFileCursor(chessBoard),new Quadrant2ndCursor(chessBoard)};
		Cursor[] move8 = {new UpFileCursor(chessBoard),new Quadrant1stCursor(chessBoard)};
		
		Movement movement = new PointTo();
		MoveMaker moveOn = new MoveOnMaker();
		List<Capability> knightCapabilities = new ArrayList<Capability>();
		knightCapabilities.add(new Capability(Arrays.asList(move1), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move2), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move3), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move4), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move5), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move6), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move7), movement, moveOn));
		knightCapabilities.add(new Capability(Arrays.asList(move8), movement, moveOn));
		
		MoveMaker attackOn = new AttackOnMaker(onSide);
		knightCapabilities.add(new Capability(Arrays.asList(move1), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move2), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move3), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move4), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move5), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move6), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move7), movement, attackOn));
		knightCapabilities.add(new Capability(Arrays.asList(move8), movement, attackOn));
		
		Piece knight = new Piece("KNIGHT",knightCapabilities);
		onSide.setPiece(knight);
		
		return knight;
	}
}
