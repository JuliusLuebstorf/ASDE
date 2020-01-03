package asde.proj4.logic.games.tictactoe;

public enum Symbol {
	EMPTY(' '), CIRCLE('O'), CROSS('X');
	
	private final char character;
	
	private Symbol(final char character) {
		this.character = character;
	}
	
	public char getCharacter() {
		return character;
	}
	
	public final Symbol opposite() {
		switch(this) {
			case CIRCLE: return CROSS;
			case CROSS: return CIRCLE;
			default: return EMPTY;
		}
	}
	
	public static Symbol convert(final char character) {
		for(final Symbol symbol : Symbol.values())
			if(symbol.getCharacter() == character)
				return symbol;
		
		throw new IllegalArgumentException("Bad input: " + character);
	}
}
