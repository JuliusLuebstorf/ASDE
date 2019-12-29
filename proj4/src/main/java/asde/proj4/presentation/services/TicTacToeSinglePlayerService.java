package asde.proj4.presentation.services;

import asde.proj4.logic.games.tictactoe.Grid;
import asde.proj4.logic.games.tictactoe.Symbol;
import asde.proj4.logic.games.tictactoe.player.AI;
import asde.proj4.logic.games.tictactoe.player.Move;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeSinglePlayerService {
	public synchronized Move move(final Symbol symbol, final Grid grid) {
		return AI.bestMove(symbol, grid);
	}
}
