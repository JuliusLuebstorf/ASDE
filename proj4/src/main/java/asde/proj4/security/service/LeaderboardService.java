package asde.proj4.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;

@Service
public class LeaderboardService {
	
	@Autowired
	public UserPlayerDAO userPlayerDAO;
	
	public List<UserPlayer> playerList; // populate list from DB and sort it
	public String[] leaderList; // return sorted list for names to display leaders without scores
	
	public String[] displayLeaderboard(String gameType) {
		
		if (userPlayerDAO.findAll() != null) {
					
			playerList = userPlayerDAO.findAll();
			
			HashMap<String, Integer> userMap = new HashMap<String, Integer>();
			
			for (int i=0; i<playerList.size(); i++) {
				userMap.put(playerList.get(i).getUsername(), playerList.get(i).getTicTacToeScore());
				System.out.println(playerList.get(i).getTicTacToeScore() + playerList.get(i).getUsername());
			}
			
			Set<Entry<String, Integer>> userSet = userMap.entrySet();
			
			Comparator<Entry<String, Integer>> comparator = new Comparator<Entry<String, Integer>>() {
	            @Override
	            public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
	                Integer var1 = entry1.getValue();
	                Integer var2 = entry2.getValue();
	                return var2.compareTo(var1);
	            }
	        };
	        
	        List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>(userSet);
	        
	        Collections.sort(entryList, comparator);
		
			ArrayList<String > leaderArray = new ArrayList<String>();
			
			for(Entry<String, Integer> entry:entryList){
				leaderArray.add(entry.getKey());
	        }
			
			leaderList = leaderArray.toArray(new String[playerList.size()]);
			
			return leaderList; 
		
		} else {
			return leaderList;
		}
	}
	

	public void updateScores(String winner, String loser, boolean draw, String gameType) {
		
		UserPlayer winnerPlayer = userPlayerDAO.findByUsername(winner);
		UserPlayer loserPlayer = userPlayerDAO.findByUsername(loser);
		
		System.out.println(winner + "\t" + loser + "\t" + draw);
		if (!draw) {
			if (gameType.contentEquals("TicTacToe")) {
				winnerPlayer.setTicTacToeScore(1);
				userPlayerDAO.save(winnerPlayer);
				System.out.println(userPlayerDAO.findByUsername(winner).getTicTacToeScore());
				loserPlayer.setTicTacToeScore(-1);
				userPlayerDAO.save(loserPlayer);
				System.out.println(userPlayerDAO.findByUsername(loser).getTicTacToeScore());
			}
		}
	}

}

