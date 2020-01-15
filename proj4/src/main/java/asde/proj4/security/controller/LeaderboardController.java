package asde.proj4.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asde.proj4.security.service.LeaderboardService;

@RestController
public class LeaderboardController {
	@Autowired
	private LeaderboardService leaderboardService;
	
	@CrossOrigin
	@GetMapping("/multiplayer/leaderboard")
	public String[] displayLeaderboard(@RequestParam String gameType) {
		return leaderboardService.displayLeaderboard(gameType);
	}

}
