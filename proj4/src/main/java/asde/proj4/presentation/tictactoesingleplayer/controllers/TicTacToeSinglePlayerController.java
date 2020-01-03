package asde.proj4.presentation.tictactoesingleplayer.controllers;

import asde.proj4.presentation.tictactoesingleplayer.services.TicTacToeSinglePlayerService;
import asde.proj4.presentation.tictactoesingleplayer.util.GridAdapter;
import asde.proj4.presentation.tictactoesingleplayer.util.MoveAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeSinglePlayerController {
	@Autowired
	private TicTacToeSinglePlayerService ticTacToeSinglePlayerService;
	
	@CrossOrigin
	@PostMapping("/move")
	public MoveAdapter move(@RequestBody final GridAdapter gridAdapter) {
		return ticTacToeSinglePlayerService.move(gridAdapter);
	}
}
