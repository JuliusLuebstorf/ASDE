package asde.proj4.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;
import asde.proj4.security.util.LeaderboardSorter;

@Service
public class LeaderboardService {
	
	@Autowired
	public UserPlayerDAO userPlayerDAO;
	
	public List<UserPlayer> playerList; // populate list from DB and sort it
	public String[] leaderList; // return sorted list for names to display leaders without scores
	
	public String[] displayLeaderboard(String gameType) {
		
		if (userPlayerDAO.findAll() != null) {
			playerList = userPlayerDAO.findAll();
			
			playerList.sort(new LeaderboardSorter(gameType));
			
			leaderList = new String[playerList.size()];
			
			for (int j=0; j<playerList.size(); j++) {
				leaderList[j] = playerList.get(j).getUsername();
			}
			
			return leaderList; 
		
		} else {
			return leaderList;
		}
	}
	

	public void updateScores(String winner, String loser, boolean draw) {
		
		UserPlayer winnerPlayer = userPlayerDAO.findByUsername(winner);
		UserPlayer loserPlayer = userPlayerDAO.findByUsername(loser);
		
		if (!draw) {
			winnerPlayer.setTicTacToeScore(1);
			loserPlayer.setTicTacToeScore(-1);
		}
	}

}

