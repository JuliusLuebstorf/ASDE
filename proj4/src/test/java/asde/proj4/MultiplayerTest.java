package asde.proj4;

import asde.proj4.logic.games.tictactoe.Game;
import asde.proj4.logic.games.tictactoe.Grid;
import java.util.Scanner;

public class MultiplayerTest {
	public static void main(String[] args) {
		final Grid grid = new Grid();
		final Game game = new Game();
		final Scanner scanner = new Scanner(System.in);
		
		while(true) {
			final int command = scanner.nextInt();
			
			if(command == -1)
				break;
			else if(command == 0)
				game.setStatus(Game.OVER);
			else if(command == 2)
				game.setStatus(Game.RUNNING);
			else if(command == 3)
				game.setGrid(grid);
			else if(command == 4)
				game.setGrid(null);
		}
		
		scanner.close();
	}
}
