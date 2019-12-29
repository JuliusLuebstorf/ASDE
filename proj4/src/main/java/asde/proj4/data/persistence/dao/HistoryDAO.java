package asde.proj4.data.persistence.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asde.proj4.data.persistence.model.History;

@Repository
public interface HistoryDAO extends CrudRepository<History, Integer>{

	
}
