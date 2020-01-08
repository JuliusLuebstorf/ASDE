package asde.proj4.presentation.tictactoe.util;

import asde.proj4.logic.games.tictactoe.Game;

public class GameDTO {
	private int gameID;
	private String currentPlayer;
	private String[] gridArray;
	private String[] players;
	private String status;
	
	public GameDTO() {
		
	}
	
	public GameDTO(final int gameID, final String currentPlayer, final String[] grid, final String status) {
		this(gameID, currentPlayer, grid, null, status);
	}
	
	public GameDTO(final int gameID, final String currentPlayer, final String[] grid, final String[] players, final String status) {
		this.gameID = gameID;
		this.currentPlayer = currentPlayer;
		this.gridArray = grid;
		this.players = players;
		this.status = status;
	}

	public int getGameID() {
		return gameID;
	}
	
	public void setGameID(final int gameID) {
		this.gameID = gameID;
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
	
	public String[] getPlayers() {
		return players;
	}
	
	public void setPlayers(final String[] players) {
		this.players = players;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(final String status) {
		this.status = status;
	}
	
	public static GameDTO convertToGameDTO(final Game game) {
		return new GameDTO(game.ID, game.getCurrentPlayer(), GridDTO.getArrayFromGrid(game.getGrid()), game.getPlayers(), game.getStatusString());
	}
}
