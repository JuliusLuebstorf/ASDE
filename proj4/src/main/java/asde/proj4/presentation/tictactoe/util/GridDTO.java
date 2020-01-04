package asde.proj4.presentation.tictactoe.util;

import asde.proj4.logic.games.tictactoe.Grid;

public class GridDTO {
	private char character;
	private String[] gridArray;
	
	public GridDTO() {

	}
	
	public GridDTO(final char character, final String[] array) {
		this.character = character;
		this.gridArray = array;
	}
	
	public char getCharacter() {
		return character;
	}

	public void setCharacter(final char character) {
		this.character = character;
	}
	
	public String[] getGridArray() {
		return gridArray;
	}

	public void setGridArray(final String[] array) {
		this.gridArray = array;
	}
	
	public static Grid convertToGrid(final String[] array) {
		final Grid grid = new Grid();
		int index = 0;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS && index < array.length; column++) {
				if(array[index].length() > 1)
					throw new IllegalArgumentException("Size of " + array[index] + " > 1");
				
				grid.set(row, column, array[index].isEmpty() ? Grid.EMPTY : array[index].charAt(0));
				
				index++;
			}
		
		return grid;
	}
	
	public static String[] getArrayFromGrid(final Grid grid) {
		int index = 0;
		final String[] array = new String[Grid.SIZE];
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS && index < array.length; column++)
				array[index++] = grid.get(row, column) + "";
		
		return array;
	}
}
