package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Location;

@SuppressWarnings("ALL")
public class DataPullActivity extends AppCompatActivity implements Serializable {

    private TextView textName;
    private Button toMap;
    private File downloadedFile;
    private LinearLayout linLayout;
    private boolean parsed = false;
    public ArrayList<Location> locArr = new ArrayList<Location>();

    private final FirebaseStorage storage = FirebaseStorage.getInstance();
    private final StorageReference storageReference = storage.getReferenceFromUrl("gs://centsible-d48e9.appspot.com").child("locations/")
            .child("currentLocations");

    @NonNull
    /**
     * create intent of context to pull data
     *
     * @param context of nonnull context of data pull
     * @return intent of data information from donation
     */
    public static Intent createIntent(@NonNull Context context) {
        return new Intent().setClass(context, DataPullActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pull);

        linLayout = findViewById(R.id.dataLayout);

        toMap = (Button) findViewById(R.id.mapButton);
        downloadedFile = downloadFile();

//        toMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CompleteMapActivity.createIntent(DataPullActivity.this));
//                startActivity(intent);
//            }
//        });

    }

    private File downloadFile() {
        try {
            final File localFile = File.createTempFile("text", "csv");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    String filename = localFile.getName();
                    //textName.setText(filename);
                    Toast.makeText(DataPullActivity.this, "Downloaded", Toast.LENGTH_SHORT).show();
                    if (!parsed) {
                        parseFile(localFile);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

            return localFile;
        } catch (IOException e) {
            return null;
        }
    }

    private void parseFile(File inFile) {
        String line = "\n";
        String csvSplitBy = ",";
        BufferedReader br = null;
        String[] locations = new String[]{};
        StringBuilder outP = new StringBuilder();
        ArrayList<Location> locArr = new ArrayList<>();

        int counter = 0;
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        try {

            FileReader filereader = new FileReader(inFile);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
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
            for (Location loc : locArr) {
                String tempStr = loc.getKey() + " | " + loc.getName() + " | " + loc.getLatitude()
                        + " | " + loc.getLongitude() + " | " + loc.getStAddress() + " | "
                        + loc.getCity() + " | " + loc.getState() + " | " + loc.getZip() + " | "
                        + loc.getType() + " | " + loc.getPhone() + " | " + loc.getWebsite() + "\n";

                Button btn = new Button(this);
                btn.setId(locCount);
                locCount++;
                final int id_ = btn.getId();
                final Location outLoc = loc;
                btn.setText(loc.getName());
                this.linLayout.addView(btn,lparams);
                Button btn1 = findViewById(id_);
                btn1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailedLocation.createIntent(DataPullActivity.this));
                        intent.putExtra("key", outLoc);
                        startActivity(intent);
                        //startActivity(DetailedLocation.createIntent(DataPullActivity.this));
                    }
                });
                //TextView tv=new TextView(this);
                //tv.setLayoutParams(lparams);
                //tv.setText(tempStr);
                //this.linLayout.addView(tv);
            }
            this.parsed = true;

            //fileContent.setText(tempStr);
        } catch (IOException l) {
            l.printStackTrace();
        }
    }
}
