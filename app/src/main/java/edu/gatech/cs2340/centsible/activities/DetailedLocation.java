package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Location;

public class DetailedLocation extends AppCompatActivity implements Serializable, OnMapReadyCallback {
    private Location useLoc;
    private GoogleMap mMap;

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
        //MapFragment fMap = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        TextView textData = (TextView) findViewById(R.id.detailedData);
        String data = useLoc.getName() + "\n\n" + useLoc.getLatitude() + ", " + useLoc.getLongitude()
                + "\n\n" + useLoc.getStAddress() + "\n\n" + useLoc.getCity() + "\n\n"
                + useLoc.getState() + "\n\n" + useLoc.getZip() + "\n\n" + useLoc.getType()
                + "\n\n" + useLoc.getPhone() + "\n\n" + useLoc.getWebsite();
        textData.setText(data);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        Double lat = Double.parseDouble(useLoc.getLatitude());
        Double lon = Double.parseDouble(useLoc.getLongitude());
        mMap.setBuildingsEnabled(true);
        //mMap.setZoom(15);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lon);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(useLoc.getName()));
    }
}
