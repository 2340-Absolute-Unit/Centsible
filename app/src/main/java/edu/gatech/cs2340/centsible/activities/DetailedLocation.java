package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Location;

public class DetailedLocation extends AppCompatActivity implements Serializable {
    private TextView textData;
    private String data;
    private Location useLoc;

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        //useLoc = inLoc;
        return new Intent().setClass(context, DetailedLocation.class);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_location);
        Intent intent = getIntent();
        useLoc = (Location)intent.getSerializableExtra("key");

        textData = (TextView) findViewById(R.id.detailedData);
        data = useLoc.getName() + "\n\n" + useLoc.getLatitude() + ", " + useLoc.getLongitude()
            + "\n\n" + useLoc.getStAddress() + "\n\n" + useLoc.getCity() + "\n\n"
            + useLoc.getState() + "\n\n" + useLoc.getZip() + "\n\n" + useLoc.getType()
            + "\n\n" + useLoc.getPhone() + "\n\n" + useLoc.getWebsite();
        textData.setText(data);
    }
}
