package asde.proj4.presentation.tictactoe.util;

import asde.proj4.logic.games.tictactoe.Grid;
import asde.proj4.logic.games.tictactoe.Move;
import asde.proj4.logic.games.tictactoe.ai.AI;

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
	
	public static MoveDTO getArrayMove(final GridDTO gridDTO) {
		final Move move = AI.bestMove(gridDTO.getCharacter(), GridDTO.convertToGrid(gridDTO.getGridArray()));
		
		return move != null ? new MoveDTO(move.COLUMN + (move.ROW * Grid.ROWS), move.CHARACTER) : null;
	}

	@Override
	public String toString() {
		return "MoveDTO [position=" + position + ", character=" + character + "]";
	}
}
