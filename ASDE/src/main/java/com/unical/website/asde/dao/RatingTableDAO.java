package com.unical.website.asde.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unical.website.asde.model.Game;
import com.unical.website.asde.model.RatingTable;
import com.unical.website.asde.model.RatingTableKey;

@Repository
public interface RatingTableDAO extends CrudRepository<RatingTable, RatingTableKey>{

	
}
