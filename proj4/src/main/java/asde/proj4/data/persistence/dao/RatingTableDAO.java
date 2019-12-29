package asde.proj4.data.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asde.proj4.data.persistence.model.RatingTable;
import asde.proj4.data.persistence.model.RatingTableKey;

@Repository
public interface RatingTableDAO extends CrudRepository<RatingTable, RatingTableKey>{

	
}
