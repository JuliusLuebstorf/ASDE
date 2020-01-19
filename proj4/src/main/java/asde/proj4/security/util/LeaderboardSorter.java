package asde.proj4.security.util;

import java.util.Comparator;

import asde.proj4.security.domain.UserPlayer;

public class LeaderboardSorter implements Comparator<UserPlayer> 
{
	private String gameType;
	
	public LeaderboardSorter() {
		super();
	}
	
	public LeaderboardSorter(String gameType) {
		super();
		this.gameType = gameType;
	}
	
    @Override
    public int compare(UserPlayer player1, UserPlayer player2) {
    	
    	if (gameType == "TicTacToe") {
    		return player2.getTicTacToeScore().compareTo(player1.getTicTacToeScore());
    	} else {
    		return 0;
    	}
    	// with other game types, the sort function can sort for the appropriate game
 
    }
}