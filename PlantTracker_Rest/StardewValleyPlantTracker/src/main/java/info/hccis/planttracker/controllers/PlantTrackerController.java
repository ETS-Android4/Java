package info.hccis.planttracker.controllers;

import info.hccis.planttracker.entity.jpa.PlantTracker;
import info.hccis.planttracker.repositories.PlantTrackerRepository;
import info.hccis.planttracker.services.FileWritingService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the Plant Tracker functionality
 *
 * @since 20191212
 * @author Fred Campos
 * @modified Kendall Fowler - Sprint 2 Requirements
 * @since 2020-06-06
 */
@Controller
@RequestMapping("/plants")
public class PlantTrackerController {

    private final PlantTrackerRepository plantRepository;

    public PlantTrackerController(PlantTrackerRepository pr) {
        plantRepository = pr;
    }

    /**
     * Page to allow user to list plants
     *
     * @author Kendall Fowler - Sprint 2 Requirements
     * @since 2020-06-06
     */
    @RequestMapping("/list")
    public String list(Model model) {

        //Go get the plants from the database.
        ArrayList<PlantTracker> plantEntries = (ArrayList<PlantTracker>) plantRepository.findAll();
        model.addAttribute("plantentries", plantEntries);

        //send the user to the list page.
        return "plants/list";
    }

    /**
     * Page to allow user to add plants
     *
     * @author Kendall Fowler - Sprint 2 Requirements
     * @since 2020-06-06
     */
    @RequestMapping("/add")
    public String add(Model model) {

        //creates new entry
        model.addAttribute("plantentry", new PlantTracker());

        //send the user to the add page.
        return "plants/add";
    }

    /**
     * Page to allow user to submit reflections
     *
     * @author  Kendall Fowler - Sprint 2 Requirements
     * @since 2020-06-06
     */
    @RequestMapping("/addSubmit")
    public String addSubmit(Model model, @Valid @ModelAttribute("plantentry") PlantTracker plantEntry, BindingResult result) {

        //validation if statement
        if (result.hasErrors()) {
            System.out.println("Validation error");
            return "plants/add";
        }

        //saves new entry
        plantRepository.save(plantEntry);

        //reload the list of plantEntries
        ArrayList<PlantTracker> plantEntries = (ArrayList<PlantTracker>) plantRepository.findAll();
        model.addAttribute("plantentries", plantEntries);

        //send the user to the list page.
        return "plants/list";
    }

    /**
     * Page to allow user to edit plants
     *
     * @author Kendall Fowler - Sprint 2 Requirements
     * @since 2020-06-06
     */
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);
        System.out.println("TEST - id=" + id);

        //finds Id of entry to edit
        Optional<PlantTracker> selectedPlant = plantRepository.findById(id);

        //if statement to detyermine if id is valid
        if (selectedPlant == null) {
            //send the user to the home page.
            return "index";
        } else {
            //finds entry
            model.addAttribute("plantentry", selectedPlant);
            //send the user to the add page.
            return "plants/add";
        }
    }

    /**
     * Page to allow user to delete plants
     *
     * @author  Kendall Fowler - Sprint 2 Requirements
     * @since 2020-06-06
     */
    @RequestMapping("/delete")
    public String delete(Model model, HttpServletRequest request) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);

        //call to method to delete entry
        plantRepository.deleteById(id);

        //Array list to delete entry from
        ArrayList<PlantTracker> plantEntries = (ArrayList<PlantTracker>) plantRepository.findAll();
        model.addAttribute("plantentries", plantEntries);

        //send the user to the list page.
        return "plants/list";

    }

    /**
     * Page to allow user to find plants
     *
     * @author  Kendall Fowler - Sprint 2 Requirements
     * @since 2020-06-06
     */
    @RequestMapping("/find")
    public String find(Model model) {

        //Load the names into the model
        Set<String> plantNames = new HashSet();

        //Array list to find entry from
        ArrayList<PlantTracker> plantEntries = (ArrayList<PlantTracker>) plantRepository.findAll();
        for (PlantTracker current : plantEntries) {
            plantNames.add(current.getPlantName());
        }

        model.addAttribute("plantNames", plantNames);

        //send the user to the find page.
        return "plants/find";
    }

    /**
     * Page to allow user to find/submit plants
     *
     * @author Kendall Fowler - Sprint 2 Requirements
     * @since 2020-06-06
     */
    @RequestMapping("/findSubmit")
    public String findSubmit(Model model, @ModelAttribute("plantentry") PlantTracker plantEntry) {

        //Array list to find entry 
        ArrayList<PlantTracker> plantEntries = (ArrayList<PlantTracker>) plantRepository.findByPlantName(plantEntry.getPlantName());
        model.addAttribute("plantentries", plantEntries);

        //Put the name in the model as well so it can be shown on the list view
        model.addAttribute("findNameMessage", " (" + plantEntry.getPlantName() + ")");

        //send the user to the list page.
        return "plants/list";
    }

    /**
     * Page to allow user to view dates
     *
     * @author Kendall Fowler - Sprint 3 Requirements
     * @since 2020-06-17
     */
    @RequestMapping("/date")
    public String date(Model model) {

        //send the user to the date page.
        return "plants/date";
    }

    /**
     * Page to allow user to export plant entries
     *
     * @author Kendall Fowler - Sprint 5 Requirements project
     * @since 2020-06-23
     */
    @RequestMapping("/export")
    public String export(HttpSession session, Model model) throws IOException {

        session.setAttribute("title", "Plant Entries");
        ArrayList<PlantTracker> plantEntries = (ArrayList<PlantTracker>) plantRepository.findAll();
        model.addAttribute("planttracker", plantEntries);
        FileWritingService.setupFile();
        FileWritingService.writePlantEntries(plantEntries);

        //send the user to the export.
        return "plants/export";
    }

}
