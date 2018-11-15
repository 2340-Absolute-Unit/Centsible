package edu.gatech.cs2340.centsible.activities;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.User;
import edu.gatech.cs2340.centsible.model.UserEntitlements;
import edu.gatech.cs2340.centsible.model.UserFacade;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * signed in activity
 */
public class SignedInActivity extends AppCompatActivity {

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        rootView = findViewById(R.id.root);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Button signout = findViewById(R.id.sign_out_button);
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

        TextView nameField = findViewById(R.id.nameText);
        UserFacade tempUF = UserFacade.getInstance();
        User tempUser = tempUF.getUser();
        String displayName = tempUser.getDisplayName();
        nameField.setText(displayName);
        // set text to be displayName
        setEntitlementsText();

    }

    /**
     * create intent of signed in page
     *
     * @param context code to see if credentials are good to sign-in
     * @param response to check to go to resultcode
     * @return the intent of the signed in activity
     */
    public static Intent createIntent(@NonNull Context context,
                                      @Nullable Parcelable response) {
        return new Intent().setClass(context, SignedInActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, response);
    }

    private void setEntitlementsText() {
        final String TAG  = "Centsible";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        UserFacade tempUF = UserFacade.getInstance();
        final User tempUser = tempUF.getUser();
        final String uid = tempUser.getUid();
        final CollectionReference usersRef = db.collection("users");
        Query query = usersRef.whereEqualTo("uid", uid);
        //noinspection FeatureEnvy
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //QuerySnapshot s = task.getResult();
                    if (!(Objects.requireNonNull(task.getResult()).isEmpty())) {
                        if (task.getResult().size() > 1) {
                            Log.d(TAG, "There are multiple documents matching the uid" + uid);
                            return; // error handling
                        }
                        ArrayList<UserEntitlements> userEntitlements = new ArrayList<>();
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            Map<String, Object> tempDoc = document.getData();
                            Iterable<String> remoteEntitlements = (List<String>) tempDoc
                                    .get("entitlements");
                            for (String j: remoteEntitlements) {
                                userEntitlements.add(UserEntitlements.valueOf(j));
                            }
                        }
                        UserFacade tempUF = UserFacade.getInstance();
                        User tempUser = tempUF.getUser();
                        tempUser.setEntitlements(userEntitlements);
                    } else {
                        Collection<UserEntitlements> userEntitlements = new ArrayList<>();
                        userEntitlements.add(UserEntitlements.USER);
                        Collection<String> list = new ArrayList<>();
                        list.add("USER");
                        Map<String, Object> user = new HashMap<>();
                        user.put("entitlements", list);
                        user.put("uid", uid);
                        usersRef.add(user)
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, "Failure to add an " +
                                                        "entitlement to Firestore");
                                            }
                                        }
                                );
                    }
                    // set UI
                    StringBuilder k = new StringBuilder();
                    UserFacade tempUF = UserFacade.getInstance();
                    User tempInstance = tempUF.getUser();
                    //List<Object> tempUserEnt = (List<Object>) tempInstance.getEntitlements();
                    for (UserEntitlements ue: UserFacade.getInstance().getUser()
                            .getEntitlements()) {
                        k.append(ue.toString()).append(" ");
                    }
                    TextView entitlementsField = findViewById(R.id.entitlementsText);
                    entitlementsField.setText(k.toString());

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

}
