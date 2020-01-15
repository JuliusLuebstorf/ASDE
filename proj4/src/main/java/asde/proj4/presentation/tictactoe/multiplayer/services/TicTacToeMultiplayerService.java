package asde.proj4.presentation.tictactoe.multiplayer.services;

import asde.proj4.logic.games.tictactoe.Game;
import asde.proj4.logic.games.tictactoe.Player;
import asde.proj4.presentation.tictactoe.util.GameDTO;
import asde.proj4.presentation.tictactoe.util.GridDTO;
import asde.proj4.presentation.tictactoe.util.PlayerDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeMultiplayerService {
	private static int ids = 1;
	
	private HashMap <Integer, Game> games = new HashMap <> ();
	
	private Game retrieve(final int gameID) {
		final Game game = games.get(gameID);
		
		if(game != null)
			return game;
		
		throw new IllegalArgumentException("Invalid game " + gameID);
	}
	
	public synchronized void newGame(final String player) {
		final Game game = new Game(ids++, true);
		
		try {
			game.setPlayer1(new Player(player, Player.PLAYER));
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		
		game.setStatus(Game.WAITING);
		games.put(game.ID, game);
	}
	
	public synchronized boolean joinGame(final GameDTO gameDTO) {
		final Game game = retrieve(gameDTO.getGameID());
		final Player[] players = game.getPlayers();
		
		if(!gameDTO.getCurrentPlayer().equals(players[0].getId()) && players[1] == null) {
			try {
				game.setPlayer2(new Player(gameDTO.getCurrentPlayer(), Player.PLAYER));
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
			
			game.setStatus(Game.RUNNING);
			
			return true;
		}
		
		return false;
	}
	
	public synchronized String[] getGrid(final int gameID) {
		return GridDTO.getArrayFromGrid(games.get(gameID).getGrid());
	}
	
	public synchronized String move(final GameDTO gameDTO) {
		final Game game = retrieve(gameDTO.getGameID());
		
		game.setGrid(GridDTO.convertToGrid(gameDTO.getGridArray()));
		
		return game.getCurrentPlayer().getId();
	}
	
	public synchronized List <GameDTO> getGames() {
		final ArrayList <GameDTO> list = new ArrayList <> ();
		
		games.forEach((gameID, game) -> list.add(GameDTO.convertToGameDTO(game)));
		
		return list;
	}
	
	public synchronized void endGame(final int gameID, final String user) {
		final Game game = retrieve(gameID);
		boolean flag = false;
		
		for(final Player player : game.getPlayers())
			if(player.getId().equals(user)) {
				flag = true;
		
				break;
			}
		
		if(flag)
			games.remove(gameID).setStatus(Game.OVER);
	}
	
	public synchronized PlayerDTO get(final GameDTO gameDTO) {
		final Game game = retrieve(gameDTO.getGameID());
		
		return new PlayerDTO(game.getSymbolFromPlayerId(gameDTO.getCurrentPlayer()), game.getCurrentPlayer().getId(), GridDTO.getArrayFromGrid(game.getGrid()));
	}
	
	public synchronized GameDTO update(final int gameID) {
		final Game game = retrieve(gameID);
		
		return GameDTO.convertToGameDTO(game);
	}
	
	public synchronized boolean isRunning(final int gameID) {
		final Game game = games.get(gameID);
		
		return game == null ? false : game.getStatus() == Game.RUNNING;
	}
}
