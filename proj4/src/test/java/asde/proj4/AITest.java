package asde.proj4;

import asde.proj4.presentation.tictactoe.util.MoveDTO;
import asde.proj4.presentation.tictactoe.util.GridDTO;

import java.util.Arrays;
import java.util.Scanner;

public class AITest {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final char[] array = new char[9];
		final char character = 'X';
		int pos;
		
		Arrays.fill(array, '-');
		
		do {
			final MoveDTO ma = MoveDTO.getArrayMove(new GridDTO(character, array));
			array[ma.getPosition()] = ma.getCharacter();
			
			System.out.println(new GridDTO(character, array).convertToGrid());
			
			array[pos = scanner.nextInt()] = character;
			
			System.out.println(new GridDTO(character, array).convertToGrid());
		} while(pos != -1);
		
		scanner.close();
	}
}
