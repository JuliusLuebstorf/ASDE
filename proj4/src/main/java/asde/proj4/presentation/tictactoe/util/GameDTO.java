package asde.proj4.presentation.tictactoe.util;

import java.util.LinkedList;
import java.util.List;
import asde.proj4.logic.games.tictactoe.Game;
import asde.proj4.logic.games.tictactoe.Player;

public class GameDTO {
	private int gameID;
	private int currentPlayerType;
	private String currentPlayer;
	private String gameStatus;
	private String[] gridArray;
	private List <String> players;
	private List <Integer> types;
	
	public GameDTO() {
		
	}
	
	public GameDTO(final int gameID, final int currentPlayerType, final String currentPlayer, final String gameStatus, final String[] gridArray) {
		this(gameID, currentPlayerType, currentPlayer, gameStatus, gridArray, null, null);
	}
	
	public GameDTO(final int gameID, final int currentPlayerType, final String currentPlayer, final String gameStatus, final String[] gridArray, final List <String> players, final List <Integer> types) {
		this.gameID = gameID;
		this.currentPlayerType = currentPlayerType;
		this.currentPlayer = currentPlayer;
		this.gridArray = gridArray;
		this.gameStatus = gameStatus;
		this.players = players;
		this.types = types;
	}

	public int getGameID() {
		return gameID;
	}
	
	public void setGameID(final int gameID) {
		this.gameID = gameID;
	}
	
	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(final String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getCurrentPlayerType() {
		return currentPlayerType;
	}

	public void setCurrentPlayerType(int currentPlayerType) {
		this.currentPlayerType = currentPlayerType;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(final String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public String[] getGridArray() {
		return gridArray;
	}
	
	public void setGridArray(final String[] grid) {
		this.gridArray = grid;
	}
	
	public List <String> getPlayers() {
		return players;
	}
	
	public void setPlayers(final List <String> players) {
		this.players = players;
	}
	
	public List <Integer> getTypes() {
		return types;
	}

	public void setTypes(final List <Integer> types) {
		this.types = types;
	}

	public void setPlayers(final List <String> players, final List <Integer> types) {
		if(players.size() != types.size())
			throw new IllegalArgumentException("Invalid input " + players + "\t" + types);
		
		this.players = players;
	}
	
	public static GameDTO convertToGameDTO(final Game game) {
		final GameDTO gameDTO = new GameDTO();
		final LinkedList <String> players = new LinkedList <> ();
		final LinkedList <Integer> playerTypes = new LinkedList <> ();
		
		gameDTO.setGameID(game.ID);
		gameDTO.setGameStatus(game.getStatusString());
		gameDTO.setCurrentPlayerType(game.getCurrentPlayer().getType());
		gameDTO.setCurrentPlayer(game.getCurrentPlayer().getId());
		gameDTO.setGridArray(GridDTO.getArrayFromGrid(game.getGrid()));
		gameDTO.setPlayers(players, playerTypes);
		
		for(final Player player : game.getPlayers())
			if(player != null) {
				players.add(player.getId());
				playerTypes.add(player.getType());
			}
		
		return gameDTO;
	}
}
