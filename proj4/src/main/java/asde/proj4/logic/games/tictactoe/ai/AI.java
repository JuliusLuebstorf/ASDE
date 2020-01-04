package asde.proj4.logic.games.tictactoe.ai;

import asde.proj4.logic.games.tictactoe.Grid;
import asde.proj4.logic.games.tictactoe.Move;

public final class AI {
	private static int evaluationFunction(final char character, final Grid grid) {
		for(final Grid.Direction direction : Grid.Direction.values())
			for(int start = 0; start < (direction == Grid.Direction.DIAGONAL ? 2 : Grid.ROWS); start++)
				if(grid.victory(character, direction, start))
					return +10;
				else if(grid.victory(Grid.opponent(character), direction, start))
					return -10;
		
		return 0;
	}
	
	private static int minimax(final char character, final Grid grid, final int depth, final boolean max) {
		final int score = evaluationFunction(character, grid);
		
		if(score == 10 || score == -10)
			return score;
		
		if(!grid.emptySquareAvailable())
			return 0;
		
		int best = max ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++) {
				if(grid.get(row, column) == Grid.EMPTY) {
					grid.set(row, column, max ? character : Grid.opponent(character));
					
					best = max ? Math.max(best, minimax(character, grid, depth + 1, !max)) : Math.min(best, minimax(character, grid, depth + 1, !max));
					
					grid.set(row, column, Grid.EMPTY);
				}
			}
			
		return best;
	}
	
	public static Move bestMove(char character, final Grid grid) {
		character = Grid.opponent(character);
		int bestValue = Integer.MIN_VALUE;
		Move move = null;
		
		for(int row = 0; row < Grid.ROWS; row++)
			for(int column = 0; column < Grid.COLUMNS; column++) {
				if(grid.get(row, column) == Grid.EMPTY) {
					grid.set(row, column, character);
					
					int moveValue = minimax(character, grid, 0, false);
					
					grid.set(row, column, Grid.EMPTY);
					
					if(moveValue > bestValue) {
						move = new Move(row, column, character);
						bestValue = moveValue;
					}
				}
			}
		
		return move;
	}
}
