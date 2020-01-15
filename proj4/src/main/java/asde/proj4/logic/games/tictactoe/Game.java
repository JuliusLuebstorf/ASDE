package asde.proj4.logic.games.tictactoe;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
	public static final int WAITING = 0, RUNNING = 1, OVER = -1;
	public final int ID;
	private Grid grid;
	private int noPlayers = 0;
	private Player[] players = new Player[2];
	private Player[] observers;
	private int status;
	private int turn = ThreadLocalRandom.current().nextInt(noPlayers + 1);
	
	public Game(final int id, final boolean newGrid) {
		ID = id;
		
		if(newGrid)
			grid = new Grid();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(final Grid grid) {
		this.turn = (turn + 1) % noPlayers;
		this.grid = grid;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public void setPlayer1(final Player player1) throws Exception {
		if(players[0] != null)
			throw new Exception("Player1 already set");
		
		players[0] = player1;
		noPlayers = 1; 
	}
	
	public void setPlayer2(final Player player2) throws Exception {
		if(players[1] != null)
			throw new Exception("Player2 already set");
		
		players[1] = player2;
		noPlayers = 2;
	}
	
	public Player getCurrentPlayer() {
		return players[turn];
	}
	
	public Character getSymbolFromPlayerId(final String playerID) {
		if(players[0] != null && players[0].getId().equals(playerID))
			return 'X';
		else if(players[1] != null && players[1].getId().equals(playerID))
			return 'O';
		
		return null;
	}

	public Player[] getObservers() {
		return observers;
	}

	public void setObservers(final Player[] observers) {
		this.observers = observers;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		if(status != WAITING && status != RUNNING && status != OVER)
			throw new IllegalArgumentException("Invalid input " + status);
		
		this.status = status;
	}
	
	public String getStatusString() {
		switch(this.status) {
			case OVER: return "Over";
			case RUNNING: return "Running";
			case WAITING: return "Waiting";
			default: throw new IllegalArgumentException("Invalid input " + status);
		}
	}
}
