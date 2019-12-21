package asde.proj4.games.tictactoe;

public class Square {
	public static final int EMPTY = -1, CIRCLE = 0, CROSS = 1;
	private int value;
	
	public Square() {
		this(EMPTY);
	}
	
	public Square(final int value) {
		setValue(value);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(final int value) {
		if(value == EMPTY || value == CIRCLE || value == CROSS)
			this.value = value;
		else {
			System.err.println("Invalid value: " + value + "; setting to EMPTY...");
			
			this.value = EMPTY;
		}
	}
}
