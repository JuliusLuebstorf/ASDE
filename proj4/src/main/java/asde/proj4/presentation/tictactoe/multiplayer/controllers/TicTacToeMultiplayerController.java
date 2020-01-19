package asde.proj4.presentation.tictactoe.multiplayer.controllers;

import asde.proj4.presentation.tictactoe.multiplayer.services.TicTacToeMultiplayerService;
import asde.proj4.presentation.tictactoe.util.GameDTO;
import asde.proj4.presentation.tictactoe.util.PlayerDTO;

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
	public void newGame(@RequestParam final String player) {
		ticTacToeMultiplayerService.newGame(player);
	}
	
	@CrossOrigin
	@PostMapping("/multiplayer/joinGame")
	public boolean joinGame(@RequestBody final GameDTO gameDTO) {
		return ticTacToeMultiplayerService.joinGame(gameDTO);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/getGrid")
	public String[] getGrid(@RequestParam final int gameID) {
		return ticTacToeMultiplayerService.getGrid(gameID);
	}
	
	@CrossOrigin
	@PostMapping("/multiplayer/move")
	public String move(@RequestBody final GameDTO gameDTO) {
		return ticTacToeMultiplayerService.move(gameDTO);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/getGames")
	public List <GameDTO> getGames() {
		return ticTacToeMultiplayerService.getGames();
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/endGame")
	public void endGame(@RequestParam final int gameID, @RequestParam final String user) {
		ticTacToeMultiplayerService.endGame(gameID, user);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/endGameSetWinner")
	public void endGameSetWinner(@RequestParam final int gameID, @RequestParam final String user, @RequestParam final String winnerSymbol) {
		ticTacToeMultiplayerService.endGameSetWinner(gameID, user, winnerSymbol);
	}
	
	@CrossOrigin
	@PostMapping("/multiplayer/get")
	public PlayerDTO get(@RequestBody final GameDTO gameDTO) {
		return ticTacToeMultiplayerService.get(gameDTO);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/update")
	public GameDTO update(@RequestParam final int gameID) {
		return ticTacToeMultiplayerService.update(gameID);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/isRunning")
	public boolean isRunning(@RequestParam final int gameID) {
		return ticTacToeMultiplayerService.isRunning(gameID);
	}
	
	@CrossOrigin
	@GetMapping("/multiplayer/isPresent")
	public boolean isPresent(@RequestParam final int gameID) {
		return ticTacToeMultiplayerService.isPresent(gameID);
	}
}
