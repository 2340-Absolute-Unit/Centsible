package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.UserFacade;

import android.view.View;
import android.widget.Button;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.cs2340.centsible.model.LocationManager;
import java.util.Arrays;

public class LandingPageActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private View rootView;

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        return new Intent(context, LandingPageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootView = findViewById(R.id.root);
        LocationManager lm = LocationManager.getInstance();
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                UserFacade.getInstance().setUser(FirebaseAuth.getInstance().getCurrentUser());
                startActivity(StorageActivity.createIntent(this, response));
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
