package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.UserFacade;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedInActivity extends AppCompatActivity {

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        rootView = findViewById(R.id.root);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Button signout = (Button) findViewById(R.id.sign_out_button);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(SignedInActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(LandingPageActivity.createIntent(
                                            SignedInActivity.this));
                                    finish();
                                } else {
                                    Snackbar.make(rootView,
                                            R.string.unknown_error,
                                            Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        TextView nameField = (TextView) findViewById(R.id.nameText);
        nameField.setText(UserFacade.getInstance().getUser().getDisplayName()); // set text to be displayName

    }

    @NonNull
    public static Intent createIntent(@NonNull Context context,
                                      @Nullable IdpResponse response) {
        return new Intent().setClass(context, SignedInActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, response);
    }


}
