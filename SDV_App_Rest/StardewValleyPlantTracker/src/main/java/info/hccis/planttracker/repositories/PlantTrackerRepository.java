package info.hccis.planttracker.repositories;

import info.hccis.planttracker.entity.jpa.PlantTracker;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Class to handle CRUD
 * @author Kendall Fowler
 * @since 2020-06-23
 */
@Repository
public interface PlantTrackerRepository extends CrudRepository<PlantTracker, Integer> {

    ArrayList<PlantTracker> findByPlantName(String plantName);

}
