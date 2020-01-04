package asde.proj4.presentation.tictactoe.multiplayer.controllers;

import asde.proj4.presentation.tictactoe.multiplayer.services.TicTacToeMultiplayerService;
import asde.proj4.presentation.tictactoe.util.GridDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeMultiplayerController {
	@Autowired
	private TicTacToeMultiplayerService ticTacToeMultiplayerService;
	
	@CrossOrigin
	@GetMapping("/multiplayer/newGame")
	public void newGame() {
		ticTacToeMultiplayerService.newGame();
	}
	
	@CrossOrigin
	@PostMapping("/multiplayer/move")
	public void move(@RequestBody final GridDTO gridDTO) {
		ticTacToeMultiplayerService.setGrid(gridDTO);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/getGrid")
	public String[] getGrid(@RequestParam final int gameID) {
		return ticTacToeMultiplayerService.getGrid(gameID);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/endGame")
	public void endGame(@RequestParam final int gameID) {
		ticTacToeMultiplayerService.endGame(gameID);
	}
}
