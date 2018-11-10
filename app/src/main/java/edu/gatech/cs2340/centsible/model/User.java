package edu.gatech.cs2340.centsible.model;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;


// POJO

@SuppressWarnings({"ALL", "unused"})
public class User {
    private static final String TAG = "NEWCENTSIBLE";

    private final String displayName;
    private final String uid;
    @SuppressWarnings("unused")
    private final String email;
    private final boolean emailVerified;
    // --Commented out by Inspection (11/10/18, 1:41 AM):private boolean isLocked;
    private ArrayList<UserEntitlements> entitlements = new ArrayList<>();

    public User(FirebaseUser user) {
        displayName = user.getDisplayName();
        uid = user.getUid();
        email = user.getEmail();
        emailVerified = user.isEmailVerified();
        retrieveEntitlementsFromFirestore();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUid() {
        return uid;
    }

// --Commented out by Inspection START (11/10/18, 1:41 AM):
//    public String getEmail() {
//        return email;
//    }
// --Commented out by Inspection STOP (11/10/18, 1:41 AM)

    public List<UserEntitlements> getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(ArrayList<UserEntitlements> entitlements) {
        this.entitlements = entitlements;
    }

// --Commented out by Inspection START (11/10/18, 1:41 AM):
//    public boolean isEmailVerified() {
//        return emailVerified;
//    }
// --Commented out by Inspection STOP (11/10/18, 1:41 AM)


    private void retrieveEntitlementsFromFirestore() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        final CollectionReference usersRef = database.collection("users");
        Query query = usersRef.whereEqualTo("uid", uid);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot s = task.getResult();
                    if (!(Objects.requireNonNull(task.getResult()).isEmpty())) {
                        if (task.getResult().size() > 1) {
                            Log.d(TAG, "There are multiple documents matching the uid" + uid);
                            return; // error handling
                        }
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            List<String> remoteEntitlements = (List<String>) document.getData().get("entitlements");
                            for (String j: remoteEntitlements) {
                                entitlements.add(UserEntitlements.valueOf(j));
                            }
                        }
                    } else {
                        entitlements.add(UserEntitlements.USER);
                        ArrayList<String> list = new ArrayList<>();
                        list.add("USER");
                        Map<String, Object> user = new HashMap<>();
                        user.put("entitlements", list);
                        user.put("uid", uid);
                        usersRef.add(user)
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, "Failed to add an entitlement to Firestore");
                                            }
                                        }
                                );
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }




}