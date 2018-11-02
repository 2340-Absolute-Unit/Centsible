package edu.gatech.cs2340.centsible.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Donation;
import edu.gatech.cs2340.centsible.model.Location;
import edu.gatech.cs2340.centsible.model.LocationManager;
import edu.gatech.cs2340.centsible.model.UserFacade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddDonationActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private Double value;

    public static Intent createIntent(@NonNull Context context) {
        return new Intent(context, AddDonationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        final Spinner spinner = (Spinner) findViewById(R.id.donation_spinner);
        ArrayAdapter<Location> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, LocationManager.getInstance().getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameTextView = findViewById(R.id.nameTextField);
                TextView shortDescriptionTextView = findViewById(R.id.shortDescriptionTextField);
                TextView longDescriptionTextView = findViewById(R.id.longDescriptionTextField);
                TextView categoryTextView = findViewById(R.id.categoryTextField);
                TextView valueTextField = findViewById(R.id.valueTextField);
                String name = nameTextView.getText().toString();
                String shortDescription = shortDescriptionTextView.getText().toString();
                String longDescription = longDescriptionTextView.getText().toString();
                String category = categoryTextView.getText().toString();
                if (valueTextField.getText().toString().equals("")) {
                    value = 0.0;
                } else {
                    value = Double.valueOf(valueTextField.getText().toString());
                }
                Location loc = (Location) spinner.getSelectedItem();

                Donation d = new Donation(loc.getKey(), name, shortDescription, longDescription, value,
                        category, UserFacade.getInstance().getUser().getUid(), new Date());

                // send to firebase

                mFirestore = FirebaseFirestore.getInstance();
                final CollectionReference donations = mFirestore.collection("donations");
                Map<String, Object> donation = new HashMap<>();
                donation.put("name", name);
                donation.put("shortDescription", shortDescription);
                donation.put("longDescription", longDescription);
                donation.put("category", category);
                donation.put("value", value);
                donation.put("enteredBy", d.getEnteredBy());
                donation.put("lastUpdated", d.getLastUpdated());
                donation.put("location", d.getLocation());
                donations.add(donation)
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "Failure to add an donation to Firestore");
                                    }
                                }
                        );


                startActivity(DonationActivity.createIntent(AddDonationActivity.this));
            }
        });
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DonationActivity.createIntent(AddDonationActivity.this));
            }
        });
    }


}
