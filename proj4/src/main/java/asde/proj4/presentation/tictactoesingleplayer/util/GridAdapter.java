package asde.proj4.presentation.tictactoesingleplayer.util;

import asde.proj4.logic.games.tictactoe.Grid;

public class GridAdapter {
	private char character;
	private char[] array;
	
	public GridAdapter() {
		
	}
	
	public GridAdapter(final char character, final char[] array) {
		this.character = character;
		this.array = array;
	}
		
	public char getCharacter() {
		return character;
	}

	public void setCharacter(final char character) {
		this.character = character;
	}

	public char[] getArray() {
		return array;
	}

	public void setArray(final char[] array) {
		this.array = array;
	}
	
	public Grid convertToGrid() {
		final Grid grid = new Grid();
		int index = 0;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++)
				grid.set(row, column, array[index++]);
		
		return grid;
	}
}
