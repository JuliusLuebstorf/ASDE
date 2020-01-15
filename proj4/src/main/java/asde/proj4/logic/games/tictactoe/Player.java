package asde.proj4.logic.games.tictactoe;

public class Player {
	public static final int PLAYER = 1, OBSERVER = 0;
	private String id;
	private int type;
	
	public Player(final String id, final int type) {
		this.id = id;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(final int type) {
		if(type != PLAYER && type != OBSERVER)
			throw new IllegalArgumentException("Invalid input " + type);
		
		this.type = type;
	}
}
