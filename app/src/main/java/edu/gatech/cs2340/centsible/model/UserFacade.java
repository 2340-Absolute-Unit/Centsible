package edu.gatech.cs2340.centsible.model;

import com.google.firebase.auth.FirebaseUser;

/**
 * user facade
 */
public class UserFacade {

    // singleton
    private static final UserFacade INSTANCE = new UserFacade();
    private User user;

    /**
     * get instance of user facade
     *
     * @return user facade of user
     */
    public static UserFacade getInstance() {
        return INSTANCE;
    }

    /**
     * get user of user facade
     *
     * @return user of user facade
     */
    public User getUser() {
        return user;
    }

    /**
     * setter for user of firebase
     *
     * @param firebaseUser new user of firebase
     */
    public void setUser(FirebaseUser firebaseUser) {
        user = new User(firebaseUser);
    }

}
