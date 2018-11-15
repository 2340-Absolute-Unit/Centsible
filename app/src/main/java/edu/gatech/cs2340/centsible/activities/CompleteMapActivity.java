package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Location;
import edu.gatech.cs2340.centsible.model.LocationManager;

/**
 * complete map activity
 */
public class CompleteMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final LocationManager tempLoc = LocationManager.getInstance();
    private final List<Location> locArray = new ArrayList<Location>(tempLoc.getList());

    /**
     * create intent of context of complete map activity
     *
     * @param context of nonnull context of complete map activity
     * @return intent of map activity
     */
    public static Intent createIntent(@NonNull Context context) {
        //useLoc = inLoc;
        return new Intent().setClass(context, CompleteMapActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_map);

        LinearLayout linLayout = findViewById(R.id.dataLayout);

        //Intent intent = getIntent();
        //locArray = (ArrayList<Location>)intent.getSerializableExtra("key");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap mMap) {
        mMap.clear();
        mMap.setBuildingsEnabled(true);
        for (Location indLoc : locArray) {
            if ("Name".equals(indLoc.getName())) {

            } else {
                Double tempLat = Double.parseDouble(indLoc.getLatitude());
                Double tempLon = Double.parseDouble(indLoc.getLongitude());
                LatLng tempLL = new LatLng(tempLat, tempLon);
                mMap.addMarker(new MarkerOptions().position(tempLL).title(indLoc.getName()
                        + "\n" + indLoc.getPhone()));
            }
        }
        //mMap.setZoom(15);
        //set focus on first location in list
        Double fLat = Double.parseDouble(locArray.get(1).getLatitude());
        Double fLon = Double.parseDouble(locArray.get(1).getLongitude());
        LatLng focus = new LatLng(fLat, fLon);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(focus, 25f));
    }
}
