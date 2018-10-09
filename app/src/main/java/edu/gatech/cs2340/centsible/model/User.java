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

import androidx.annotation.NonNull;


// POJO

public class User {
    public static final String TAG = "NEWCENTSIBLE";

    private String displayName;
    private String uid;
    private String email;
    private boolean emailVerified;
    public ArrayList<UserEntitlements> entitlements = new ArrayList<>();

    public String getDisplayName() {
        return displayName;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public List<UserEntitlements> getEntitlements() {
        return entitlements;
    }

    public boolean isEmailVerified() {
        return isEmailVerified();
    }

    public User(FirebaseUser user) {
        displayName = user.getDisplayName();
        uid = user.getUid();
        email = user.getEmail();
        emailVerified = user.isEmailVerified();
        retrieveEntitlementsFromFirestore();
    }

    public void refreshInformation(FirebaseUser user) {
        displayName = user.getDisplayName();
        uid = user.getUid();
        email = user.getEmail();
        emailVerified = user.isEmailVerified();
        retrieveEntitlementsFromFirestore();
    }

    private void retrieveEntitlementsFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference usersRef = db.collection("users");
        Query query = usersRef.whereEqualTo("uid", uid);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot s = task.getResult();
                    if (!(task.getResult().isEmpty())) {
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
                        String[] list = {"USER"};
                        Map<String, Object> user = new HashMap<>();
                        user.put("entitlements", list);
                        user.put("uid", uid);
                        usersRef.add(user)
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, "Failure to add an entitlement to Firestore");
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