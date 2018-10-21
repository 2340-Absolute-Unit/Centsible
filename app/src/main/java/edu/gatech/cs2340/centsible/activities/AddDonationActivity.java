package edu.gatech.cs2340.centsible.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Donation;
import edu.gatech.cs2340.centsible.model.UserFacade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class AddDonationActivity extends AppCompatActivity {

    public static Intent createIntent(@NonNull Context context) {
        return new Intent(context, AddDonationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);
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
                Double value = Double.valueOf(valueTextField.getText().toString());

                Donation d = new Donation(null, name, shortDescription, longDescription, value,
                        category, UserFacade.getInstance().getUser(), new Date());

                // send to firebase

                startActivity(DonationActivity.createIntent(AddDonationActivity.this));
            }
        });
    }


}
