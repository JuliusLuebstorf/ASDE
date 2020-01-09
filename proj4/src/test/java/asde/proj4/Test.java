package asde.proj4;

import static org.junit.jupiter.api.Assertions.*;

import asde.proj4.presentation.tictactoe.util.GridDTO;
import asde.proj4.presentation.tictactoe.util.MoveDTO;

class Test {

	static char[] elementos = { 'X', 'O', '-' }; // elements to combine
	static int cantidad = 3; // count of elements
	static int numero = 9; // number of positions to combine

	static int sum = 0;
	
	
	/**
	 * Imprime las combinaciones C(c,n) que se pueden hacer con el arreglo elementos
	 * repitiendo elementos en los grupos
	 */
	public void devolverSoluciones_R(int faltan, int addPosSolucion, char[] solucion, int iterador) {
		if (faltan == 0) {
			int different = 0;

			boolean found = false;
			for (int j = 0; j < solucion.length; j++) {
				if (solucion[j] == 'X')
					different++;
				else if (solucion[j] == 'O')
					different--;
				else found =true;
			}

			if (different >= -1 && different <= 1 && found) {

				if ((solucion[0] == solucion[1] && solucion[0] == solucion[2])
						|| (solucion[3] == solucion[4] && solucion[3] == solucion[5])
						|| (solucion[6] == solucion[7] && solucion[6] == solucion[8]) ||

						(solucion[0] == solucion[3] && solucion[0] == solucion[6])
						|| (solucion[1] == solucion[4] && solucion[1] == solucion[7])
						|| (solucion[2] == solucion[5] && solucion[2] == solucion[8])
						|| (solucion[0] == solucion[4] && solucion[0] == solucion[8])
						|| (solucion[2] == solucion[4] && solucion[2] == solucion[6])) {
				} else {
					
					String[] array = new String[9];
					for (int j = 0; j < solucion.length; System.out.print(solucion[j++])) {
						;// print the list of char
						array[j] = "" + solucion[j];
					}
					
					System.out.print("  ");
					
					char character = 'O';
					
					if(different == 1) {
						character = 'X';
					}					
					
					
					GridDTO gridDTO = new GridDTO(character, array);
										
					MoveDTO moveDTO = MoveDTO.getArrayMove(gridDTO);
					
					//we compare the character that the machine put with the character that the person put
					//if this happens, then it's wrong
					if(moveDTO.getCharacter() == character) {
						assertTrue(false);
					}
					
					
					//the machine put the character not in an empty place
					//if this happens, then it's wrong
					if(!array[moveDTO.getPosition()].equals("-")) {
						assertTrue(false);
					}
					
										
					System.out.println(); // line break
					
					//just i use to print the machine movement
					solucion[moveDTO.getPosition()] = moveDTO.getCharacter();
					System.out.println(String.valueOf(solucion) + "   chosen position: " + moveDTO.getPosition() + "   character played: " +  moveDTO.getCharacter());
					
					//restore the position
					solucion[moveDTO.getPosition()] = '-';
					
					sum++;
				}
			}

		} else
			for (int i = 0; i < cantidad; i++) {
				solucion[addPosSolucion] = elementos[i];
				devolverSoluciones_R(faltan - 1, addPosSolucion + 1, solucion, i);

			}
	}
	
	
	
	
	@org.junit.jupiter.api.Test
	
	void test() {
				
		/* just to the first test
		char character = 'O'; 
		final String[] array = {"O","-","O","-","X","-","X","O","X"};
		*/
			
		System.out.println("comenzo el test");
		devolverSoluciones_R(numero, 0, new char[numero], 0);
		System.out.println(sum + "");
		assertTrue(true);
	}	
	
}
