package asde.proj4.logic.games.tictactoe;

public class Game implements Runnable {
	public static int games;
	public static final int WAITING = 1, RUNNING = 2, OVER = 0;
	public final int ID;
	private Grid grid;
	private volatile int status;
	private Thread timer;
	
	public Game() {
		ID = games++;
		
		setStatus(WAITING);
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(final int status) {
		if(status != WAITING && status != RUNNING && status != OVER)
			throw new IllegalArgumentException("Invalid status: " + status);
		
		this.status = status;
		
		if(status == RUNNING)
			(timer  = new Thread(this)).start();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(final Grid grid) {
		if(timer.isAlive())
			this.grid = grid;
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
