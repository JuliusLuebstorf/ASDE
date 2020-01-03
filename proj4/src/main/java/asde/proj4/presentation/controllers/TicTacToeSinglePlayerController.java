package asde.proj4.presentation.controllers;

import asde.proj4.logic.games.tictactoe.GridAdapter;
import asde.proj4.logic.games.tictactoe.Symbol;
import asde.proj4.logic.games.tictactoe.player.Move;
import asde.proj4.presentation.services.TicTacToeSinglePlayerService;
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
	public Move move(@RequestBody final Symbol symbol, @RequestBody final GridAdapter grid) {
		return ticTacToeSinglePlayerService.move(symbol, grid.convert());
	}
}
