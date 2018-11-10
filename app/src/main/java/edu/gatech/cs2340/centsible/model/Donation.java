package edu.gatech.cs2340.centsible.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

import androidx.annotation.NonNull;

@SuppressWarnings("NullableProblems")
public class Donation {
    private @NonNull String location;
    private @NonNull String name;
    private @NonNull String shortDescription;
    private @NonNull String longDescription;
    private double value;
    private @NonNull String category;
    private @NonNull String enteredBy;
    private @ServerTimestamp Date lastUpdated;


    public Donation() {

    }

    /**
     * constructor for donation
     *
     * @param location location of donation
     * @param name name of the donation
     * @param shortDescription short description of donation
     * @param longDescription long description of donation
     * @param value value of donation
     * @param category category of donation
     * @param enteredBy person who entered donation into app
     * @param lastUpdated when the donation was last updated
     */
    public Donation(@NonNull String location, @NonNull String name, @NonNull String shortDescription,
                    @NonNull String longDescription, double value, @NonNull String category,
                    @NonNull String enteredBy, Date lastUpdated) {
        this.location = location;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.enteredBy = enteredBy;
        this.lastUpdated = lastUpdated;
    }

    /**
     * getter for location of donation
     */
    public @NonNull String getLocation() {
        return location;
    }

    /**
     * getter for name of donation
     */
    public @NonNull String getName() {
        return name;
    }

    /**
     * getter for short description of donation
     */
    public @NonNull String getShortDescription() {
        return shortDescription;
    }

    /**
     * getter for long description of donation
     */
    public @NonNull String getLongDescription() {
        return longDescription;
    }

    /**
     * getter for value of donation
     */
    public double getValue() {
        return value;
    }

    /**
     * getter for category of donation
     */
    public @NonNull String getCategory() {
        return category;
    }

    /**
     * getter for person who entered the donation
     */
    public @NonNull String getEnteredBy() {
        return enteredBy;
    }

    /**
     * getter for when donation was last updated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

}
