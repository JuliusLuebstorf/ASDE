package asde.proj4.logic.games.tictactoe;

public enum Symbol {
	EMPTY, CIRCLE, CROSS;
	
	public final Symbol opposite() {
		switch(this) {
			case CIRCLE: return CROSS;
			case CROSS: return CIRCLE;
			default: return EMPTY;
		}
	}
}
