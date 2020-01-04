package asde.proj4.presentation.tictactoe.singleplayer.controllers;

import asde.proj4.presentation.tictactoe.util.MoveDTO;
import asde.proj4.presentation.tictactoe.singleplayer.services.TicTacToeSinglePlayerService;
import asde.proj4.presentation.tictactoe.util.GridDTO;
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
	@PostMapping("/singleplayer/move")
	public MoveDTO move(@RequestBody final GridDTO gridAdapter) {
		return ticTacToeSinglePlayerService.move(gridAdapter);
	}
}
