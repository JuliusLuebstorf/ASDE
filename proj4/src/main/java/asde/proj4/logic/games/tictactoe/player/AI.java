package asde.proj4.logic.games.tictactoe.player;

import asde.proj4.logic.games.tictactoe.Grid;
import asde.proj4.logic.games.tictactoe.Symbol;

public final class AI {
	private static int evaluationFunction(final Symbol symbol, final Grid grid) {
		for(final Grid.Direction direction : Grid.Direction.values())
			for(int start = 0; start < (direction == Grid.Direction.DIAGONAL ? 2 : Grid.ROWS); start++)
				if(grid.victory(symbol, direction, start))
					return +10;
				else if(grid.victory(symbol.opposite(), direction, start))
					return -10;
		
		return 0;
	}
	
	private static int minimax(final Symbol symbol, final Grid grid, final int depth, final boolean max) {
		final int score = evaluationFunction(symbol, grid);
		
		if(score == 10 || score == -10)
			return score;
		
		if(!grid.emptySquareAvailable())
			return 0;
		
		int best = max ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++) {
				if(grid.get(row, column) == Symbol.EMPTY) {
					grid.set(row, column, max ? symbol : symbol.opposite());
					
					best = max ? Math.max(best, minimax(symbol, grid, depth + 1, !max)) : Math.min(best, minimax(symbol, grid, depth + 1, !max));
					
					grid.set(row, column, Symbol.EMPTY);
				}
			}
			
		return best;
	}
	
	public static Move bestMove(final Symbol symbol, final Grid grid) {
		int bestValue = Integer.MIN_VALUE;
		Move move = null;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++) {
				if(grid.get(row, column) == Symbol.EMPTY) {
					grid.set(row, column, symbol);
					
					int moveValue = minimax(symbol, grid, 0, false);
					
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
