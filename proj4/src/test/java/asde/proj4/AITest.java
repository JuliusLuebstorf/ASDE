package asde.proj4;

import asde.proj4.presentation.tictactoesingleplayer.util.GridAdapter;
import asde.proj4.presentation.tictactoesingleplayer.util.MoveAdapter;
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
			final MoveAdapter ma = MoveAdapter.getArrayMove(new GridAdapter(character, array));
			array[ma.getPosition()] = ma.getCharacter();
			
			System.out.println(new GridAdapter(character, array).convertToGrid());
			
			array[pos = scanner.nextInt()] = character;
			
			System.out.println(new GridAdapter(character, array).convertToGrid());
		} while(pos != -1);
		
		scanner.close();
	}
}
