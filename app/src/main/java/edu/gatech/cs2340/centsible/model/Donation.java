package edu.gatech.cs2340.centsible.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

import androidx.annotation.NonNull;

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

    public @NonNull String getLocation() {
        return location;
    }

    public @NonNull String getName() {
        return name;
    }

    public @NonNull String getShortDescription() {
        return shortDescription;
    }

    public @NonNull String getLongDescription() {
        return longDescription;
    }

    public double getValue() {
        return value;
    }

    public @NonNull String getCategory() {
        return category;
    }


    public @NonNull String getEnteredBy() {
        return enteredBy;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

}
