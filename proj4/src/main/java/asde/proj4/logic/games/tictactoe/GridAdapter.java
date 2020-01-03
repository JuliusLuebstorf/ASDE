package asde.proj4.logic.games.tictactoe;

public class GridAdapter {
	private char[] array;
	
	public GridAdapter() {
		
	}

	public GridAdapter(char[] array) {
		
	}

	public char[] getArray() {
		return array;
	}

	public void setArray(final char[] array) {
		this.array = array;
	}
	
	public Grid convert() {
		final Grid grid = new Grid();
		
		for(int i = 0; i < array.length; i++)
			for(int row = 0; row < Grid.ROWS; row++)
				for(int column = 0; column < Grid.COLUMNS; column++)
					grid.set(row, column, Symbol.convert(array[i]));
		
		return grid;
	}
}
