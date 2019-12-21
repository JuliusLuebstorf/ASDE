package asde.proj4.games.tictactoe;

public class Grid {
	public static final int COLUMNS = 3, ROWS = 3, SIZE = ROWS * COLUMNS;
	private final Square[][] grid = new Square[ROWS][COLUMNS];
	
	public Grid() {
		
	}
	
	public Square[][] getGrid() {
		return grid;
	}
	
	private boolean validIndexes(final int row, final int column) {
		return row >= 0 && row < ROWS && column >= 0 && column < COLUMNS;
	}
	
	public Square getSquare(final int row, final int column) {
		if(!validIndexes(row, column)) {
			System.err.println("Invalid indexes: " + row + " " + column + "; returning null...");
			
			return null;
		}
		
		return grid[row][column];
	}
	
	public void setSquare(final int row, final int column, final Square square) {
		if(!validIndexes(row, column)) {
			System.err.println("Invalid indexes: " + row + " " + column + "; returning...");
			
			return;
		}
		
		grid[row][column] = square;
	}
}
