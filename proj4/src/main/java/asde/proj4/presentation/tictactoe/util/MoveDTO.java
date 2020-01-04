package asde.proj4.presentation.tictactoe.util;

import asde.proj4.logic.games.tictactoe.Grid;
import asde.proj4.logic.games.tictactoe.player.AI;
import asde.proj4.logic.games.tictactoe.player.Move;

public class MoveDTO {
	private int position;
	private char character;
	
	public MoveDTO() {
		
	}
	
	public MoveDTO(final int position, final char character) {
		this.position = position;
		this.character = character;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(final int position) {
		this.position = position;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(final char character) {
		this.character = character;
	}
	
	public static MoveDTO getArrayMove(final GridDTO gridAdapter) {
		final Move move = AI.bestMove(gridAdapter.getCharacter(), gridAdapter.convertToGrid());
		
		return move != null ? new MoveDTO(move.COLUMN + (move.ROW * Grid.ROWS), move.CHARACTER) : null;
	}
}
