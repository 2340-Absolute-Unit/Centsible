package edu.gatech.cs2340.centsible;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        final int waitTime = 5000;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                startActivity(LandingPage.createIntent(SplashScreen.this));
            }
        };

    }

}
