package asde.proj4.logic.games.tictactoe.player;

import asde.proj4.logic.games.tictactoe.Symbol;

public class Move {
	public final int ROW, COLUMN;
	public final Symbol SYMBOL;
	
	public Move(final int row, final int column, final Symbol symbol) {
		ROW = row;
		COLUMN = column;
		SYMBOL = symbol;
	}

	@Override
	public String toString() {
		return "Move [ROW=" + ROW + ", COLUMN=" + COLUMN + ", SYMBOL=" + SYMBOL + "]";
	}
}
