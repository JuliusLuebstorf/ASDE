package asde.proj4.presentation.tictactoesingleplayer.services;

import asde.proj4.presentation.tictactoesingleplayer.util.GridAdapter;
import asde.proj4.presentation.tictactoesingleplayer.util.MoveAdapter;

import org.springframework.stereotype.Service;

@Service
public class TicTacToeSinglePlayerService {
	public synchronized MoveAdapter move(final GridAdapter gridAdapter) {
		return MoveAdapter.getArrayMove(gridAdapter);
	}
}
