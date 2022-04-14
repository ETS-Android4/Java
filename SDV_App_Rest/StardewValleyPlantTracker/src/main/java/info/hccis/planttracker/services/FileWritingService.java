package info.hccis.planttracker.services;

import com.google.gson.Gson;
import info.hccis.planttracker.entity.jpa.PlantTracker;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Exports Files for the Plant Tracker
 *
 * @author Kendall Fowler
 * @since 2020-06-23
 */
public class FileWritingService {

    public static final String PATH = "c:\\cis2232\\";
    public static final String FILE_NAME = "plantTracker.json";

    /**
     * Sets up json file - code sourced from Assignment 1
     *
     * @author Kendall Fowler
     * @since 2020-06-24
     */
    public static void setupFile() {

        File myFile;
        try {
            Path path = Paths.get(PATH);
            try {
                Files.createDirectories(path);
            } catch (IOException ex) {
                System.out.println("Error creating directory");
            }

            myFile = new File(PATH + FILE_NAME);
            if (myFile.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException ex) {
            System.out.println("Error creating new file");
        }
    }

    /**
     * Writes entries to array list - code sourced from Assignment 1
     * 
     *
     * @author Kendall Fowler
     * @since 2020-06-24
     *
     */
    public static void writePlantEntries(ArrayList<PlantTracker> plantEntries) throws IOException {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(PATH + FILE_NAME, false));
        } catch (IOException ex) {
            System.out.println("Error Accessing File");
        }

        String stringJson = "";
        for (PlantTracker current : plantEntries) {
            try {
                Gson gson = new Gson();
                String reflectionJson = gson.toJson(current);
                writer.write(reflectionJson + System.lineSeparator());
            } catch (IOException ex) {
                System.out.println("Error Accessing File");
            }
        }
        try {
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error Accessing File");
        }

    }
}
