package asde.proj4.logic.games.tictactoe;

import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {
	public static int games;
	public static final int WAITING = 1, RUNNING = 2, OVER = 0;
	public final int ID;
	private Grid grid;
	private volatile int status;
	private String[] players = new String[2];
	private int turn = ThreadLocalRandom.current().nextInt(players.length);
	private Thread timer;
	
	public Game() {
		ID = games++;
		
		setStatus(WAITING);
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(final Grid grid, final String player) {
		if(timer.isAlive() && players[turn++ % 2].equals(player)) {
			this.grid = grid;
			
			resetTimer();
		}
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(final int status) {
		if(status != WAITING && status != RUNNING && status != OVER)
			throw new IllegalArgumentException("Invalid status: " + status);
		
		this.status = status;
		
		if(status == RUNNING)
			resetTimer();
	}

	public String[] getPlayers() {
		return players;
	}
	
	public void setPlayers(final String player1, final String player2) {
		players[0] = player1;
		players[1] = player2;
	}
	
	private void resetTimer() {
		(timer  = new Thread(this)).start();
	}
	
	public String getCurrentPlayer() {
		return players[turn];
	}
	
	public String getStatusString() {
		switch(status) {
			case WAITING: return "Waiting";
			case RUNNING: return "Running";
			case OVER: return "Over";
			default: System.err.println("Fatal error"); System.exit(-1); return "";
		}
	}
	
	@Override
	public void run() {
		int counter = 30;
		
		while(counter-- > 30 && status == RUNNING)
			try {
				Thread.sleep(1000);
			} catch (final InterruptedException exception) {
				exception.printStackTrace();
			}
	}
}
