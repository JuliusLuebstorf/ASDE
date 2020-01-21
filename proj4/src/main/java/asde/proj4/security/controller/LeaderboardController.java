package asde.proj4.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asde.proj4.security.domain.UserPlayer;
import asde.proj4.security.domain.UserPlayerSimple;
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

	
	@CrossOrigin
	@GetMapping("/multiplayer/leaderboardTopList")
	public List<UserPlayer> leaderboardTopList(@RequestParam String gameType) {		
		return leaderboardService.leaderboardTopList(gameType);
	}
	
	
	@CrossOrigin
	@GetMapping("/multiplayer/leaderboardTopListOptimized")
	public List<UserPlayer> leaderboardTopListOptimized(@RequestParam String gameType) {		
		return leaderboardService.findTop3WithQuery(gameType);
	}
}
