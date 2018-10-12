package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.firebase.ui.auth.util.ExtraConstants;
import com.firebase.ui.auth.IdpResponse;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;
import com.opencsv.CSVReader;

import edu.gatech.cs2340.centsible.R;

public class DataPullActivity extends AppCompatActivity {

    private Button btnDownload, btnParse;
    private TextView textName, fileContent;
    private File downloadedFile;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReferenceFromUrl("gs://centsible-d48e9.appspot.com").child("locations/")
            .child("currentLocations");

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        return new Intent().setClass(context, DataPullActivity.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pull);

        btnDownload = (Button) findViewById(R.id.btnDownload);
        btnParse = (Button) findViewById(R.id.btnParse);
        textName = (TextView) findViewById(R.id.textView);
        fileContent = (TextView) findViewById(R.id.fileContent);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadedFile = downloadFile();
            }
        });

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadedFile != null) {
                    parseFile();
                }
            }
        });

    }

    private File downloadFile() {
        try {
            final File localFile = File.createTempFile("text", "csv");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    String filename = localFile.getName();
                    textName.setText(filename);
                    Toast.makeText(DataPullActivity.this, "Downloaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {

                }
            });
            return localFile;
        } catch (IOException e) {
            return null;
        }
    }

    private void parseFile() {
        String line = "\n";
        String csvSplitBy = ",";
        BufferedReader br = null;
        String[] locations = new String[]{};
        StringBuilder outP = new StringBuilder();
        int counter = 0;

        try {
            /*br = new BufferedReader(new FileReader(downloadedFile));
            while ((line = br.readLine()) != null) {
                locations = line.split(csvSplitBy);
                String[] tester = new String[]{"1", "2", "3"};
                //counter++;
            }
            for (String word : locations) {
                outP.append(word);
            }

            fileContent.setText(Arrays.toString(locations));*/

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(downloadedFile);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    outP.append(cell);
                }
                fileContent.setText(outP);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException l) {
            l.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
