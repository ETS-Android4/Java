package info.hccis.planttracker.rest;

import com.google.gson.Gson;
import info.hccis.planttracker.entity.jpa.PlantTracker;
import info.hccis.planttracker.repositories.PlantTrackerRepository;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import static org.hibernate.annotations.SourceType.DB;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * REST Class for Plant Tracker Service
 *
 * @author BJ McLean
 *
 * @modified Kendall Fowler - Sprint 4 Requirements
 * @since 2020-06-21
 */
@Path("/PlantTrackerService/plantentries")
public class PlantTrackerService {

    private static Map<Integer, PlantTracker> PT = new HashMap<>();
    private final PlantTrackerRepository pr;

    @Autowired
    public PlantTrackerService(PlantTrackerRepository or) {
        this.pr = or;
    }

    /**
     * @Get Class to view all Entries
     * @author BJ McLean
     *
     * @modified Kendall Fowler - Sprint 4 Requirements
     * @since 2020-06-23
     */
    @GET
    @Produces("application/json")
    public ArrayList<PlantTracker> getAllPlantEntries() {
        ArrayList<PlantTracker> plantentries = (ArrayList<PlantTracker>) pr.findAll();
        return plantentries;
    }

    /**
     * @Get Class to view one entry
     * @author BJ McLean
     *
     * @modified Kendall Fowler - Sprint 4 Requirements
     * @since 2020-06-23
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPlantNameById(@PathParam("id") int id) throws URISyntaxException {

        Optional<PlantTracker> plantTracker = pr.findById(id);

        if (plantTracker == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(plantTracker).build();
    }

    /**
     * @Get Class to create a new entry
     * @author BJ McLean
     *
     * @modified Kendall Fowler - Sprint 5 Requirements
     * @since 2020-06-23
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlantEntry(String plantTrackerJson) {

        Gson gson = new Gson();
        PlantTracker plantTracker = gson.fromJson(plantTrackerJson, PlantTracker.class);

        if (plantTracker.getPlantName() == null || plantTracker.getPlantName().isEmpty()) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }

        if (plantTracker.getId() == null) {
            plantTracker.setId(0);
        }

        plantTracker = pr.save(plantTracker);

        String temp = "";
        temp = gson.toJson(plantTracker);

        return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
    }

    /**
     * @Get Class to delete entry
     * @author BJ McLean
     *
     * @modified Kendall Fowler - Sprint 4 Requirements
     * @since 2020-06-23
     */
    @DELETE
    @Path("/{id}")
    public Response deleteEntry(@PathParam("id") int id) throws URISyntaxException {
        Optional<PlantTracker> planttracker = pr.findById(id);
        if (planttracker != null) {
            pr.deleteById(id);
            return Response.status(HttpURLConnection.HTTP_CREATED).build();
        }
        return Response.status(404).build();
    }

    /**
     * @PUT Class to view update entry
     * @author BJ McLean
     *
     * @modified Kendall Fowler - Sprint 4 Requirements
     * @since 2020-06-23
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEntry(@PathParam("id") int id, String plantTrackerJson) throws URISyntaxException 
    {

        Gson gson = new Gson();
        PlantTracker plantTracker = gson.fromJson(plantTrackerJson, PlantTracker.class);
        
        if(plantTracker.getPlantSeason()== null || plantTracker.getPlantSeason().isEmpty()) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
 
        if(plantTracker.getId() == null){
            plantTracker.setId(0);
        }

        plantTracker = pr.save(plantTracker);

        String temp = "";
        temp = gson.toJson(plantTracker);

        return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();        
    }    

}
