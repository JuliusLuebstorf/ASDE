package asde.proj4.security.service;

import java.util.List;

import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;
import asde.proj4.security.util.LeaderboardSorter;

public class LeaderboardService {
	
	public UserPlayerDAO userPlayerDAO;
	public List<UserPlayer> playerList; // populate list from DB and sort it
	public String[] leaderList; // return sorted list for names to display leaders without scores
	
	public String[] displayLeaderboard(String gameType) {
		
		playerList = userPlayerDAO.findAll();
		
		playerList.sort(new LeaderboardSorter(gameType));
		
		leaderList = new String[playerList.size()];
		
		for (int j=0; j<playerList.size(); j++) {
			leaderList[j] = playerList.get(j).getUsername();
		}
		
		return leaderList;
	}

}

