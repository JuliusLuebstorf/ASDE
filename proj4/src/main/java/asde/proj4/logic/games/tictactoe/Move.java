package asde.proj4.logic.games.tictactoe;

public class Move {
	public final int ROW, COLUMN;
	public final char CHARACTER;
	
	public Move(final int row, final int column, final char character) {
		ROW = row;
		COLUMN = column;
		CHARACTER = character;
	}

	@Override
	public String toString() {
		return "Move [ROW=" + ROW + ", COLUMN=" + COLUMN + ", CHARACTER=" + CHARACTER + "]";
	}
}
