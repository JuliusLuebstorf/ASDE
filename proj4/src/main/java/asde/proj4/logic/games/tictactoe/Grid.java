package asde.proj4.logic.games.tictactoe;

public class Grid {
	public static final int COLUMNS = 3, ROWS = 3, SIZE = ROWS * COLUMNS, NO_DIAGONALS = 2;
	public static enum Direction { ROW, COLUMN, DIAGONAL };
	private final Symbol[][] grid = new Symbol[ROWS][COLUMNS];
	
	public Grid() {
		for(int row = 0; row < ROWS; row++)
			for(int column = 0; column < COLUMNS; column++)
				grid[row][column] = Symbol.EMPTY;
	}
	
	public Symbol[][] getGrid() {
		return grid;
	}
	
	private boolean validIndexes(final int row, final int column) {
		return row >= 0 && row < ROWS && column >= 0 && column < COLUMNS;
	}
	
	public Symbol get(final int row, final int column) {
		if(!validIndexes(row, column))
			throw new IllegalArgumentException("Invalid indexes: " + row + " " + column);
		
		return grid[row][column];
	}
	
	public void set(final int row, final int column, final Symbol symbol) {
		if(!validIndexes(row, column))
			throw new IllegalArgumentException("Invalid indexes: " + row + " " + column);
		
		grid[row][column] = symbol;
	}
	
	public boolean emptySquareAvailable() {
		for(int row = 0; row < ROWS; row++)
			for(int column = 0; column < COLUMNS; column++)
				if(grid[row][column]== Symbol.EMPTY)
					return true;
		
		return false;
	}
	
	public boolean victory(final Symbol symbol, final Direction direction, final int start) {
		if(((direction == Direction.ROW || direction == Direction.COLUMN) && (start < 0 || start >= ROWS)) || (direction == Direction.DIAGONAL && (start < 0 || start >= NO_DIAGONALS)))
			throw new IllegalArgumentException("Invalid parameters: " + symbol + " " + direction + " " + start);
		
		int row = direction == Direction.ROW ? start : 0, column = direction == Direction.COLUMN ? start : (direction == Direction.DIAGONAL && start == 1) ? COLUMNS - 1 : 0;
		
		while(validIndexes(row, column)) {
			if(grid[row][column] != symbol)
				return false;
			
			row = direction == Direction.ROW ? row : row + 1;
			column = direction == Direction.COLUMN ? column : (direction == Direction.DIAGONAL && start == 1) ? column - 1 : column + 1;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		String string = "";
		
		for(int row = 0; row < ROWS; row++) {
			for(int column = 0; column < COLUMNS; column++)
				switch(grid[row][column]) {
					case CIRCLE: string += 'o'; break;
					case CROSS: string += 'x'; break;
					case EMPTY: string += ' '; break;
				}
				
			string += '\n';
		}
		
		return string;
	}
}
