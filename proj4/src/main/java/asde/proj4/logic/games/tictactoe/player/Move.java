package asde.proj4.logic.games.tictactoe.player;

import asde.proj4.logic.games.tictactoe.Grid;
import asde.proj4.logic.games.tictactoe.Symbol;

public class Move {
	public int index;
	public char character;
	
	public Move() {
		
	}
	
	public Move(final int index, final char character) {
		this.index = index;
		this.character = character;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(final int index) {
		this.index = index;
	}
	
	public char getCharacter() {
		return character;
	}

	public void setCharacter(final char character) {
		this.character = character;
	}

	public static Move getMove(final int row, final int column, final Symbol symbol) {
		final Move move = new Move();
		
		move.setIndex(row + (column * Grid.ROWS));
		move.setCharacter(symbol.getCharacter());
		
		if(row >= 0 && row < Grid.ROWS && column >= 0 && column < Grid.COLUMNS)
			throw new IllegalArgumentException("Invalid indexes: " + row + " " + column);
		
		return move;
	}
	
	
	@Override
	public String toString() {
		return "Move [index=" + index + ", character=" + character + "]";
	}
}
