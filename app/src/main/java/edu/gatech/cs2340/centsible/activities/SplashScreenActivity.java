package edu.gatech.cs2340.centsible.activities;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.cs2340.centsible.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        final int waitTime = 5000;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                startActivity(LandingPageActivity.createIntent(SplashScreen.this));
            }
        };

        handler.postDelayed(runnable, waitTime);
    }

}
