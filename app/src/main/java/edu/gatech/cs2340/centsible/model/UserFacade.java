package edu.gatech.cs2340.centsible.model;

import com.google.firebase.auth.FirebaseUser;

public class UserFacade {

    // singleton
    private static final UserFacade INSTANCE = new UserFacade();
    private User user = null;

    public static UserFacade getInstance() {
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(FirebaseUser firebaseUser) {
        user = new User(firebaseUser);
    }

}
