package com.unical.website.asde.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unical.website.asde.model.History;
import com.unical.website.asde.model.Player;

@Repository
public interface HistoryDAO extends CrudRepository<History, Integer>{

	
}
