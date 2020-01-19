package asde.proj4.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
			System.out.println(playerList.size());
			
			HashMap<String, Integer> userMap = new HashMap<String, Integer>();
			
			for (int i=0; i<playerList.size(); i++) {
				userMap.put(playerList.get(i).getUsername(), playerList.get(i).getTicTacToeScore());
				System.out.println(playerList.get(i).getTicTacToeScore() + playerList.get(i).getUsername());
			}
			
			Set<Entry<String, Integer>> entries = userMap.entrySet();
			
			Comparator<Entry<String, Integer>> comparator = new Comparator<Entry<String, Integer>>() {
	            
	            @Override
	            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
	                Integer v1 = e1.getValue();
	                Integer v2 = e2.getValue();
	                return v2.compareTo(v1);
	            }
	        };
	        
	        List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>(entries);
	        
	        Collections.sort(entryList, comparator);
			

			ArrayList<String > leaderArray = new ArrayList<String>();
			
			for(Entry<String, Integer> entry:entryList){
				leaderArray.add(entry.getKey());
	        }
			
			System.out.println(leaderArray);
			
			leaderList = leaderArray.toArray(new String[playerList.size()]);
			
			System.out.println(leaderList.toString() + "leaderlist");
			
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

