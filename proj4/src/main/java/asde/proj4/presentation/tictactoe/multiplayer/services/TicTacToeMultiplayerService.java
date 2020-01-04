package asde.proj4.presentation.tictactoe.multiplayer.services;

import asde.proj4.logic.games.tictactoe.Game;
import asde.proj4.presentation.tictactoe.util.GridDTO;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeMultiplayerService {
	private HashMap <Integer, Game> games = new HashMap <> ();
	
	public synchronized void newGame() {
		final Game game = new Game();
		
		games.put(game.ID, game);
	}
	
	public synchronized String[] getGrid(final int id) {
		if(!games.containsKey(id))
			throw new IllegalArgumentException("Game not present: " + id);
		
		return GridDTO.getArrayFromGrid(games.get(id).getGrid());
	}
	
	public synchronized void setGrid(final GridDTO gridDTO) {
		games.get(gridDTO.getGameID()).setGrid(GridDTO.convertToGrid(gridDTO.getArray()));
	}
	
	public synchronized void endGame(final int id) {
		if(!games.containsKey(id))
			throw new IllegalArgumentException("Game not present: " + id);
		
		games.remove(id).setStatus(Game.OVER);
	}
}
