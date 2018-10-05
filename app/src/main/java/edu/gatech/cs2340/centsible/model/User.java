package edu.gatech.cs2340.centsible.model;

public class User {

    private String uid;
    private String displayName;
    private String email;

    private boolean emailVerified;
    private UserTypes userType;

    public String getUid() {
        return uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public UserTypes getUserType() {
        return userType;
    }


    public User(String uid, String displayName, String email,
                boolean emailVerified, UserTypes userType) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.emailVerified = emailVerified;
        this.userType = userType;
    }

    public User(String uid, String displayName, String email,
                boolean emailVerified) {
        this(uid, displayName, email, emailVerified, UserTypes.USER);
    }





}
