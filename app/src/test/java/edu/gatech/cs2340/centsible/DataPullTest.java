package edu.gatech.cs2340.centsible;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import edu.gatech.cs2340.centsible.activities.DataPullActivity;
import edu.gatech.cs2340.centsible.activities.DetailedLocation;
import edu.gatech.cs2340.centsible.model.LocationManager;
import edu.gatech.cs2340.centsible.model.Location;

import static org.junit.Assert.assertEquals;
public class DataPullTest {
    public Map<String, Location> jULlocations = new HashMap<String, Location>();
    //LocationManager testManager = new LocationManager();
    public ArrayList<Location> locList;
    private static final String TEST_NAME = "./testFile.csv";

    @Before
    public void setUp() {

        final Map<String, Location> myMap = new HashMap<>();
        //FirebaseStorage storage = FirebaseStorage.getInstance();
        String line = "\n";
        String csvSplitBy = ",";
        BufferedReader br = null;
        String[] locations = new String[]{};
        StringBuilder outP = new StringBuilder();
        Collection<Location> locArr = new ArrayList<>();
        int counter = 0;
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        try {
            Writer writer = Files.newBufferedWriter(Paths.get(TEST_NAME));
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] wline = {"Key", "Name", "Latitude", "Longitude", "Street"};
            csvWriter.writeNext(wline);
            String[] wline2 = {"Address", "City", "State", "Zip", "Type", "Phone", "Website"};
            csvWriter.writeNext(wline2);
            String[] wline3 = {"1", "AFD Station 4", "33.75416", "-84.37742", "309 EDGEWOOD AVE"};
            csvWriter.writeNext(wline3);
            String[] wline4 = {"SE", "Atlanta", "GA", "30332", "Drop Off", "(404) 555 - 3456", "www.afd04.atl.ga"};
            csvWriter.writeNext(wline4);
            String[] wline5 = {"2", "BOYS & GILRS CLUB W.W. WOOLFOLK", "33.73182", "-84.43971", "1642 RICHLAND"};
            csvWriter.writeNext(wline5);
            String[] wline6 = {"RD", "Atlanta", "GA", "30332", "Store", "(404) 555 - 1234", "www.bgc.wool.ga"};
            csvWriter.writeNext(wline6);
            String[] wline7 = {"3", "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES", "33.70866", "-84.41853", "1683 SYLVAN"};
            csvWriter.writeNext(wline7);
            String[] wline8 = {"RD", "Atlanta", "GA", "30332", "Warehouse", "(404) 555 - 5432", "www.pathways.org"};
            csvWriter.writeNext(wline8);
            String[] wline9 = {"4", "PAVILION OF HOPE INC", "33.80129", "-84.25537", "3558 EAST PONCE DE LEON"};
            csvWriter.writeNext(wline9);
            String[] wline0 = {"AVE", "SCOTTDALE", "GA", "30079", "Warehouse", "(404) 555 - 8765", "www.pavhope.org"};
            csvWriter.writeNext(wline0);
            String[] wline11 = {"5", "D&D CONVENIENCE STORE", "33.71747", "-84.2521", "2426 COLUMBIA"};
            csvWriter.writeNext(wline11);
            String[] wline22 = {"DRIVE", "DECATUR", "GA", "30034", "Drop Off", "(404) 555 - 9876", "www.ddconv.com"};
            csvWriter.writeNext(wline22);
            String[] wline33 = {"6", "KEEP NORTH FULTON BEAUTIFUL", "33.96921", "-84.3688", "470 MORGAN FALLS RD,Sandy "};
            csvWriter.writeNext(wline33);
            String[] wline44 = {"Springs", "GA", "30302", "Store", "(770) 555 - 7321", "www.knfb.org"};
            csvWriter.writeNext(wline44);
        } catch (IOException e) {

        }

        try {
            File localFile = new File(TEST_NAME);

            FileReader filereader = new FileReader(localFile);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Location tempLoc = new Location();
                for (int i = 0; i < 11; i++) {
                    outP.append(nextRecord[i]);
                    if (i == 0) {
                        tempLoc.setKey(nextRecord[i]);
                    }
                    if (i == 1) {
                        tempLoc.setName(nextRecord[i]);
                    }
                    if (i == 2) {
                        tempLoc.setLatitude(nextRecord[i]);
                    }
                    if (i == 3) {
                        tempLoc.setLongitude(nextRecord[i]);
                    }
                    if (i == 4) {
                        tempLoc.setStAddress(nextRecord[i]);
                    }
                    if (i == 5) {
                        tempLoc.setCity(nextRecord[i]);
                    }
                    if (i == 6) {
                        tempLoc.setState(nextRecord[i]);
                    }
                    if (i == 7) {
                        tempLoc.setZip(nextRecord[i]);
                    }
                    if (i == 8) {
                        tempLoc.setType(nextRecord[i]);
                    }
                    if (i == 9) {
                        tempLoc.setPhone(nextRecord[i]);
                    }
                    if (i == 10) {
                        tempLoc.setWebsite(nextRecord[i]);
                    }
                }
                locArr.add(tempLoc);

            }
            int locCount = 0;
            //Map<String, Location> myMap = new HashMap<>();
            for (Location loc : locArr) {
                String tempStr = loc.getKey() + " | " + loc.getName() + " | " + loc.getLatitude()
                        + " | " + loc.getLongitude() + " | " + loc.getStAddress() + " | "
                        + loc.getCity() + " | " + loc.getState() + " | " + loc.getZip() + " | "
                        + loc.getType() + " | " + loc.getPhone() + " | " + loc.getWebsite() + "\n";

                locCount++;
                myMap.put(loc.getKey(), loc);

            }
            jULlocations = myMap;
            locList = new ArrayList<>(jULlocations.values());

        } catch (IOException l) {
            l.printStackTrace();
        }
    }





    @Test
    public void testDataPullParseFile() {

        String line = "\n";
        String csvSplitBy = ",";
        BufferedReader br = null;
        String[] locations = new String[]{};
        StringBuilder outP = new StringBuilder();
        Collection<Location> locArr = new ArrayList<>();

        int counter = 0;
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        try {

            File inFile = new File(TEST_NAME);

            FileReader filereader = new FileReader(inFile);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord = csvReader.readNext();

            // we are going to read data line by line

            while (nextRecord != null) {
                Location tempLoc = new Location();
                int tempNum = 11;
                for (int i = 0; i < tempNum; i++) {
                    outP.append(nextRecord[i]);
                    if (i == 0) {
                        tempLoc.setKey(nextRecord[i]);
                    }
                    if (i == 1) {
                        tempLoc.setName(nextRecord[i]);
                    }
                    if (i == 2) {
                        tempLoc.setLatitude(nextRecord[i]);
                    }
                    if (i == 3) {
                        tempLoc.setLongitude(nextRecord[i]);
                    }
                    if (i == 4) {
                        tempLoc.setStAddress(nextRecord[i]);
                    }
                    if (i == 5) {
                        tempLoc.setCity(nextRecord[i]);
                    }
                    if (i == 6) {
                        tempLoc.setState(nextRecord[i]);
                    }
                    if (i == 7) {
                        tempLoc.setZip(nextRecord[i]);
                    }
                    if (i == 8) {
                        tempLoc.setType(nextRecord[i]);
                    }
                    if (i == 9) {
                        tempLoc.setPhone(nextRecord[i]);
                    }
                    if (i == 10) {
                        tempLoc.setWebsite(nextRecord[i]);
                    }
                }
                locArr.add(tempLoc);

            }
            int locCount = 0;




            //fileContent.setText(tempStr);
        } catch (IOException l) {
            l.printStackTrace();
        }
        assertEquals("Lists didn't match", locList, locArr);
    }

}
