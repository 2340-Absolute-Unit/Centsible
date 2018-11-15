package edu.gatech.cs2340.centsible;

import android.text.TextUtils;

/**
 * filter class for filtering search
 */
public class Filters {

    private String category;
    private String name;
    private String location;


    /*empty constructor for filters
    public Filters() {}*/

    /**
     * get default filter
     *
     * @return default filter
     */
    public static Filters getDefault() {
        return new Filters();
    }

    /**
     * whether or not filtering by category
     *
     * @return boolean if category filter true or false
     */
    public boolean hasCategory() {
        return !(TextUtils.isEmpty(category));
    }

    /**
     * whether or not filtering by name
     *
     * @return boolean if name filter true or false
     */
    public boolean hasName() {
        return !(TextUtils.isEmpty(name));
    }

    /**
     * whether or not filtering by location
     *
     * @return boolean if location filter true or false
     */
    public boolean hasLocation() {
        return !(TextUtils.isEmpty(location));
    }

    /**
     * getter for category
     *
     * @return category name to search for items
     */
    public String getCategory() {
        return category;
    }

    /**
     * getter for name
     *
     * @return name of donation to search items for
     */
    public String getName() {
        return name;
    }

    /**
     * getter for location of items
     *
     * @return locations to search items for
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter for names
     *
     * @param name name of name filter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for category
     *
     * @param category new category to search for
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * setter for location
     *
     * @param location new location to filter items for
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /*public String getSearchDescription() {
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
    }*/


}
