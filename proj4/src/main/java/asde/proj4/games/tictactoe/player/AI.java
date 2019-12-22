package asde.proj4.games.tictactoe.player;

import asde.proj4.games.tictactoe.Grid;
import asde.proj4.games.tictactoe.Symbol;

public class AI {
	private final Symbol symbol;
	private final Grid grid;
	
	public AI(final Symbol symbol, final Grid grid) {
		this.symbol = symbol;
		this.grid = grid;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	private int evaluationFunction() {
		for(final Grid.Direction direction : Grid.Direction.values())
			for(int start = 0; start < (direction == Grid.Direction.DIAGONAL ? 2 : Grid.ROWS); start++)
				if(grid.victory(symbol, direction, start))
					return +10;
				else if(grid.victory(symbol.opposite(), direction, start))
					return -10;
		
		return 0;
	}
	
	private int minimax(final int depth, final boolean max) {
		final int score = evaluationFunction();
		
		if(score == 10 || score == -10)
			return score;
		
		if(!grid.emptySquareAvailable())
			return 0;
		
		int best = max ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++) {
				if(grid.get(row, column) == Symbol.EMPTY) {
					grid.set(row, column, max ? symbol : symbol.opposite());
					
					best = max ? Math.max(best, minimax(depth + 1, !max)) : Math.min(best, minimax(depth + 1, !max));
					
					grid.set(row, column, Symbol.EMPTY);
				}
			}
			
		return best;
	}
	
	public Move bestMove() {
		int bestValue = Integer.MIN_VALUE;
		Move move = null;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++) {
				if(grid.get(row, column) == Symbol.EMPTY) {
					grid.set(row, column, symbol);
					
					int moveValue = minimax(0, false);
					
					grid.set(row, column, Symbol.EMPTY);
					
					if(moveValue > bestValue) {
						move = new Move(row, column, symbol);
						bestValue = moveValue;
					}
				}
			}
		
		return move;
	}
}
