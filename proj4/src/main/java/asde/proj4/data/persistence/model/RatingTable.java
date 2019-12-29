package asde.proj4.data.persistence.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

//https://www.baeldung.com/jpa-many-to-many

@Entity
public class RatingTable {

	
	@EmbeddedId
	RatingTableKey id;
	
	
	@ManyToOne
    @MapsId("player_id")
    @JoinColumn(name = "player_id")
	private Player player;
	
	
	@ManyToOne
    @MapsId("game_id")
    @JoinColumn(name = "game_id")
	private Game game;
	
	@Column(nullable = false)
	private int rating;


	public RatingTable() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RatingTableKey getId() {
		return id;
	}


	public void setId(RatingTableKey id) {
		this.id = id;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
