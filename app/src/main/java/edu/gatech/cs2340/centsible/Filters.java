package edu.gatech.cs2340.centsible;

import android.content.Context;
import android.text.TextUtils;

import edu.gatech.cs2340.centsible.model.Donation;
import com.google.firebase.firestore.Query;

public class Filters {

    private String category = null;
    private String name = null;
    private String location = null;

    public Filters() {}

    public static Filters getDefault() {
        return new Filters();
    }

    public boolean hasCategory() {
        return !(TextUtils.isEmpty(category));
    }

    public boolean hasName() {
        return !(TextUtils.isEmpty(name));
    }

    public boolean hasLocation() {
        return !(TextUtils.isEmpty(location));
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSearchDescription() {
        StringBuilder searchDescription = new StringBuilder();

        if (category == null && name == null && location == null) {
            searchDescription.append("<b>");
            searchDescription.append("All donations");
            searchDescription.append("</b>");
        }

        if (category != null) {
            searchDescription.append("<b>");
            searchDescription.append("Category: ");
            searchDescription.append(category);
            searchDescription.append("</b>");
        }

        if (location != null) {
            searchDescription.append("<b>");
            searchDescription.append("Location: ");
            searchDescription.append(location);
            searchDescription.append("</b>");
        }

        if (name != null) {
            searchDescription.append("<b>");
            searchDescription.append("Name: ");
            searchDescription.append(name);
            searchDescription.append("</b>");
        }

        return searchDescription.toString();
    }

}
