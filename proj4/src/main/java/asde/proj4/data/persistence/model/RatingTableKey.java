package asde.proj4.data.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RatingTableKey implements Serializable{

	@ManyToOne
	
	@Column(name = "player_id")
	private int playerID;
	
	
	@Column(name = "game_id")
	private int gameId;
	
	
	// standard constructors, getters, and setters
    // hashcode and equals implementation
}
