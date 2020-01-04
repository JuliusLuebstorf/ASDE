package asde.proj4.presentation.tictactoe.multiplayer.controllers;

import asde.proj4.presentation.tictactoe.multiplayer.services.TicTacToeMultiplayerService;
import asde.proj4.presentation.tictactoe.util.GameDTO;
import java.util.List;
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
	@GetMapping("/multiplayer/joinGame")
	public void joinGame(@RequestParam final int gameID) {
		ticTacToeMultiplayerService.joinGame(gameID);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/getGrid")
	public String[] getGrid(@RequestParam final int gameID) {
		return ticTacToeMultiplayerService.getGrid(gameID);
	}
	
	@CrossOrigin
	@PostMapping("/multiplayer/move")
	public void setGrid(@RequestBody final GameDTO gameDTO) {
		ticTacToeMultiplayerService.setGrid(gameDTO);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/getWaitingGames")
	public List <GameDTO> getWaitingGames() {
		return ticTacToeMultiplayerService.getWaitingGames();
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/endGame")
	public void endGame(@RequestParam final int gameID) {
		ticTacToeMultiplayerService.endGame(gameID);
	}
}
