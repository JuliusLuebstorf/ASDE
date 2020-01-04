package asde.proj4.presentation.tictactoe.singleplayer.services;

import asde.proj4.presentation.tictactoe.util.MoveDTO;
import asde.proj4.presentation.tictactoe.util.GridDTO;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeSinglePlayerService {
	public synchronized MoveDTO move(final GridDTO gridAdapter) {
		return MoveDTO.getArrayMove(gridAdapter);
	}
}
