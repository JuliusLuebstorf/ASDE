package asde.proj4.logic.games.tictactoe;

public class Grid {
	public static final int COLUMNS = 3, ROWS = 3, SIZE = ROWS * COLUMNS, NO_DIAGONALS = 2;
	public static enum Direction { ROW, COLUMN, DIAGONAL };
	public static final char EMPTY = '-', CIRCLE = 'O', CROSS = 'X';
	private final char[][] grid = new char[ROWS][COLUMNS];
	
	public Grid() {
		for(int row = 0; row < ROWS; row++)
			for(int column = 0; column < COLUMNS; column++)
				grid[row][column] = EMPTY;
	}
	
	public char[][] getGrid() {
		return grid;
	}
	
	private boolean validIndexes(final int row, final int column) {
		return row >= 0 && row < ROWS && column >= 0 && column < COLUMNS;
	}
	
	private boolean validCharacter(final char character) {
		return character == EMPTY || character == CIRCLE || character == CROSS;
	}
	
	public char get(final int row, final int column) {
		if(!validIndexes(row, column))
			throw new IllegalArgumentException("Invalid indexes: " + row + " " + column);
		
		return grid[row][column];
	}
	
	public void set(final int row, final int column, final char character) {
		if(!validIndexes(row, column) || !validCharacter(character))
			throw new IllegalArgumentException("Invalid input: " + row + " " + column + " " + character);
		
		grid[row][column] = character;
	}
	
	public boolean emptySquareAvailable() {
		for(int row = 0; row < ROWS; row++)
			for(int column = 0; column < COLUMNS; column++)
				if(grid[row][column]== EMPTY)
					return true;
		
		return false;
	}
	
	public boolean victory(final char character, final Direction direction, final int start) {
		if(((direction == Direction.ROW || direction == Direction.COLUMN) && (start < 0 || start >= ROWS)) || (direction == Direction.DIAGONAL && (start < 0 || start >= NO_DIAGONALS)))
			throw new IllegalArgumentException("Invalid parameters: " + character + " " + direction + " " + start);
		
		int row = direction == Direction.ROW ? start : 0, column = direction == Direction.COLUMN ? start : (direction == Direction.DIAGONAL && start == 1) ? COLUMNS - 1 : 0;
		
		while(validIndexes(row, column)) {
			if(grid[row][column] != character)
				return false;
			
			row = direction == Direction.ROW ? row : row + 1;
			column = direction == Direction.COLUMN ? column : (direction == Direction.DIAGONAL && start == 1) ? column - 1 : column + 1;
		}
		
		return true;
	}
	
	public static char opponent(final char character) {
		switch(character) {
			case CIRCLE: return CROSS;
			case CROSS: return CIRCLE;
			default: return EMPTY;
		}
	}
	
	@Override
	public String toString() {
		String string = "";
		
		for(int row = 0; row < ROWS; row++) {
			for(int column = 0; column < COLUMNS; column++)
				string += grid[row][column];
				
			string += '\n';
		}
		
		return string;
	}
}
