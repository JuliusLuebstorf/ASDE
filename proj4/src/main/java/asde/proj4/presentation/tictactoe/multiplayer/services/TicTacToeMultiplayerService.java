package asde.proj4.presentation.tictactoe.multiplayer.services;

import asde.proj4.logic.games.tictactoe.Game;
import asde.proj4.presentation.tictactoe.util.GameDTO;
import asde.proj4.presentation.tictactoe.util.GridDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeMultiplayerService {
	private HashMap <Integer, Game> games = new HashMap <> ();
	
	private Game retrieve(final int gameID, final int status) {
		final Game game = games.get(gameID);
		
		if(game != null && game.getStatus() == status)
			return game;
		
		throw new IllegalArgumentException("Invalid game " + gameID + " " + status);
	}
	
	public synchronized void newGame() {
		final Game game = new Game();
		
		games.put(game.ID, game);
	}
	
	public synchronized void joinGame(final int gameID) {
		final Game game = retrieve(gameID, Game.WAITING);
		
		game.setStatus(Game.RUNNING);
	}
	
	public synchronized String[] getGrid(final int gameID) {
		return GridDTO.getArrayFromGrid(games.get(gameID).getGrid());
	}
	
	public synchronized void setGrid(final GameDTO gameDTO) {
		final Game game = retrieve(gameDTO.getGameID(), Game.RUNNING);
		
		game.setGrid(GridDTO.convertToGrid(gameDTO.getGridArray()), gameDTO.getCurrentPlayer());
	}
	
	public List <GameDTO> getWaitingGames() {
		final ArrayList <GameDTO> list = new ArrayList <> ();
		
		games.forEach((gameID, game) -> {
			if(game.getStatus() == Game.WAITING)
				list.add(GameDTO.convertToGameDTO(game));
		});
		
		list.add(new GameDTO(1, "a", new String[]{}, "cacca"));
		
		return list;
	}
	
	public synchronized void endGame(final int id) {
		games.remove(id).setStatus(Game.OVER);
	}
}
