package asde.proj4.presentation.tictactoe.util;

public class PlayerDTO {
	private char symbol;
	private String player;
	private String[] gridArray;
	
	public PlayerDTO() {
		
	}
	
	public PlayerDTO(final char symbol, final String player, final String[] gridArray) {
		this.symbol = symbol;
		this.player = player;
		this.gridArray = gridArray;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public void setSymbol(final char symbol) {
		this.symbol = symbol;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(final String player) {
		this.player = player;
	}

	public String[] getGridArray() {
		return gridArray;
	}

	public void setGridArray(String[] gridArray) {
		this.gridArray = gridArray;
	}
}
