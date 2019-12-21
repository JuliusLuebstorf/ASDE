package asde.proj4.persistence.model;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Repository;

@Entity
public class Game {

	@OneToMany(mappedBy = "game")
	private Set<RatingTable> ratings;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	
	@PostConstruct
	public void init() {
		//will do executed after constructor
	}


	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Set<RatingTable> getRatings() {
		return ratings;
	}


	public void setRatings(Set<RatingTable> ratings) {
		this.ratings = ratings;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	
	
	
	
	
}
