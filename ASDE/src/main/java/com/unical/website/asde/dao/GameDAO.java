package com.unical.website.asde.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unical.website.asde.model.Game;

@Repository
public interface GameDAO extends CrudRepository<Game, Integer>{

	List<Game> findByName(String name);
	
}
