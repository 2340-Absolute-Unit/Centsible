package edu.gatech.cs2340.centsible.activities;

import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class DonationActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, DonationActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
    }
}
