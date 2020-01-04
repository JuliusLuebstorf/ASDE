package asde.proj4;

import asde.proj4.presentation.tictactoe.util.MoveDTO;
import asde.proj4.logic.games.tictactoe.Grid;
import asde.proj4.presentation.tictactoe.util.GridDTO;
import java.util.Scanner;

public class AITest {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final String[] array = new String[9];
		final char character = 'X';
		int pos;
		
		for(int i = 0; i < array.length; i++)
			array[i] = Grid.EMPTY + "";
		
		do {
			array[pos = scanner.nextInt()] = character + "";
			
			System.out.println(GridDTO.convertToGrid(array));
			
			final MoveDTO ma = MoveDTO.getArrayMove(new GridDTO(character, array));
			array[ma.getPosition()] = ma.getCharacter() + "";
			
			System.out.println(GridDTO.convertToGrid(array));
		} while(pos != -1);
		
		scanner.close();
	}
}
