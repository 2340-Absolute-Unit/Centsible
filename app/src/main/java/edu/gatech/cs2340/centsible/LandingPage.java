package edu.gatech.cs2340.centsible;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;

import java.util.Arrays;

public class LandingPage extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private View rootView;

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        return new Intent(context, LandingPage.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootView = findViewById(R.id.root);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button signin = (Button) findViewById(R.id.sign_in_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                                        new AuthUI.IdpConfig.EmailBuilder().build(),
                                        new AuthUI.IdpConfig.PhoneBuilder().build(),
                                        new AuthUI.IdpConfig.AnonymousBuilder().build()))
                                .build(),
                        RC_SIGN_IN);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                System.out.println("HELLO");
                startActivity(SignedInActivity.createIntent(this, response));

                finish();
            } else {

                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Snackbar.make(rootView, R.string.sign_in_cancelled,
                            Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Snackbar.make(rootView, R.string.no_internet_connection,
                            Snackbar.LENGTH_LONG).show();
                    return;
                }
                Snackbar.make(rootView, R.string.unknown_error,
                        Snackbar.LENGTH_LONG).show();
            }
        }
    }


}
