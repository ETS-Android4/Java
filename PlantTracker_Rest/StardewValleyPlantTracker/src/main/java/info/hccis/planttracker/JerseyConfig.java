package info.hccis.planttracker;

import info.hccis.planttracker.rest.PlantTrackerService;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * JerseyConfig for Plant Tracker Service
 * @author Kendall Fowler - Sprint 4 Requirements
 * @since 2020-06-21
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        registerClasses(PlantTrackerService.class);
    }
}
