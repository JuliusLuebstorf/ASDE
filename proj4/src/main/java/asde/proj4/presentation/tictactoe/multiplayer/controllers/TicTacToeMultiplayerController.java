package asde.proj4.presentation.tictactoe.multiplayer.controllers;

import asde.proj4.presentation.tictactoe.multiplayer.services.TicTacToeMultiplayerService;
import asde.proj4.presentation.tictactoe.util.GridDTO;
import asde.proj4.presentation.tictactoe.util.MoveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeMultiplayerController {
	@Autowired
	private TicTacToeMultiplayerService ticTacToeMultiplayerService;
	
	@CrossOrigin
	@PostMapping("/multiplayer/move")
	public MoveDTO move(@RequestBody final GridDTO gridAdapter) {
		return ticTacToeMultiplayerService.move(gridAdapter);
	}
}
