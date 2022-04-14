package info.hccis.planttracker.main;

import com.google.gson.Gson;
import info.hccis.planttracker.model.jpa.PlantTracker;
import info.hccis.planttracker.util.UtilityRest;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Controller Class for Rest App.
 *
 * @since 20171117
 * @author BJM
 * @modified Kendall Fowler - Sprint 4 Requirements
 * @since 2020-06-23
 */
public class Controller {

    final public static String MENU = "\nMain Menu \n"
            + "A) Add Entry\n"
            + "V) View All Entries\n"
            + "S) View One Entry\n"
            + "D) Delete Entry\n"
            + "U) Update Entry\n"
            + "X) eXit";
    final static Scanner input = new Scanner(System.in);
    private static final String URL_STRING = "http://localhost:8081/stardewvalley/api/PlantTrackerService/plantentries/";
    private static Integer viewId;

    public static void main(String[] args) {
        boolean endProgram = false;
        do {
            System.out.println(MENU);
            String choice = input.nextLine();
            Scanner input = new Scanner(System.in);
            PlantTracker plantTracker;
            String url;
            switch (choice.toUpperCase()) {
                case "A":
                    plantTracker = createPlantEntry();
                    url = URL_STRING;
                    System.out.println("Url=" + url);
                    UtilityRest.addUsingRest(url, plantTracker);
                    break;
                case "D":
                    System.out.println("Enter id to delete");
                    int id = input.nextInt();
                    input.nextLine();  //burn
                    UtilityRest.deleteUsingRest(URL_STRING, id);
                    break;
                case "V":
                    String jsonReturned = UtilityRest.getJsonFromRest(URL_STRING);
                    //**************************************************************
                    //Based on the json string passed back, loop through each json
                    //object which is a json string in an array of json strings.
                    //*************************************************************
                    JSONArray jsonArray = new JSONArray(jsonReturned);
                    //**************************************************************
                    //For each json object in the array, show the entries
                    //**************************************************************
                    System.out.println("Here are the entries");
                    Gson gson = new Gson();
                    for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                        PlantTracker current = gson.fromJson(jsonArray.getJSONObject(currentIndex).toString(), PlantTracker.class);
                        System.out.println(current.toString());
                    }
                    break;
                case "S":
                    System.out.println("Enter ID of the entry you wish to view");
                    int viewId = input.nextInt();
                    input.nextLine();  //burn
                    Gson gsonViewOne = new Gson();
                    System.out.println("Here is the entry");
                    PlantTracker current = gsonViewOne.fromJson((UtilityRest.getJsonFromRest(URL_STRING + viewId)), PlantTracker.class);
                    System.out.println(current.toString());
                    break;
                case "U":
                    plantTracker = updatePlantEntry();
                    url = URL_STRING + "" + plantTracker.getId();
                    System.out.println("Url=" + url);
                    UtilityRest.updateUsingRest(url, plantTracker);
                    break;
                case "X":
                    endProgram = true;
                    break;
                default:
                    System.out.println("INVALID OPTION");
            }
        } while (!endProgram);
    }

    /**
     * Create a PlantTracker object by passing asking user for input.
     *
     * @return PlantTracker
     * @since 20171117
     * @author BJM
     * @modified Kendall Fowler - Sprint 4 Requirements
     * @since 2020-06-21
     */
    public static PlantTracker createPlantEntry() {
        PlantTracker newPlantTracker = new PlantTracker();
        newPlantTracker.setId(0);

        System.out.println("Enter Season Planted:");
        newPlantTracker.setPlantSeason(input.nextLine());
        System.out.println("Enter Plant Name:");
        newPlantTracker.setPlantName(input.nextLine());
        System.out.println("Enter Date Planted:");
        newPlantTracker.setPlantDate(input.nextLine());
        System.out.println("Enter Number of Seeds Planted:");
        newPlantTracker.setNumberPlanted(input.nextInt());
        input.nextLine();  //burn

        return newPlantTracker;
        
    }

    /**
     * Update a PlantTracker object by passing asking user for input.
     *
     * @return PlantTracker
     * @since 20171117
     * @author BJM
     * @modified Kendall Fowler - Sprint 4 Requirements
     * @since 2020-06-23
     */
    public static PlantTracker updatePlantEntry() {

        System.out.println("Enter ID of the entry you wish to edit");
        int editId = input.nextInt();
        input.nextLine();  //burn
        
        PlantTracker updatedPlantTracker = new PlantTracker();
        updatedPlantTracker.setId(editId);

        System.out.println("Enter Season Planted:");
        updatedPlantTracker.setPlantSeason(input.nextLine());
        System.out.println("Enter Plant Name:");
        updatedPlantTracker.setPlantName(input.nextLine());
        System.out.println("Enter Date Planted:");
        updatedPlantTracker.setPlantDate(input.nextLine());
        System.out.println("Enter Number of Seeds Planted:");
        updatedPlantTracker.setNumberPlanted(input.nextInt());
        input.nextLine();  //burn

        return updatedPlantTracker;
        
    }

}
