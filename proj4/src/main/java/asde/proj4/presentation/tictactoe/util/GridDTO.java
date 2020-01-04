package asde.proj4.presentation.tictactoe.util;

import asde.proj4.logic.games.tictactoe.Grid;

public class GridDTO {
	private char character;
	private String[] array;
	
	public GridDTO() {
		
	}
	
	public GridDTO(final char character, final String[] array) {
		this.character = character;
		this.array = array;
	}
		
	public char getCharacter() {
		return character;
	}

	public void setCharacter(final char character) {
		this.character = character;
	}

	public String[] getArray() {
		return array;
	}

	public void setArray(final String[] array) {
		this.array = array;
	}
	
	public Grid convertToGrid() {
		final Grid grid = new Grid();
		int index = 0;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++) {
				if(array[index].length() > 1)
					throw new IllegalArgumentException("Size of " + array[index] + " > 1");
				
				grid.set(row, column, array[index++].charAt(0));
			}
		
		return grid;
	}
}
