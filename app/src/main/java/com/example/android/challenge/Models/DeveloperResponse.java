package com.example.android.challenge.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by adeyemogabriel on 8/28/17.
 */

public class DeveloperResponse {

    @SerializedName("total_count")
    private int total_count;

    @SerializedName("incomplete_results")
    private boolean incomplete_results;

    @SerializedName("items")
    private List<Developer> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Developer> getItems() {
        return items;
    }

    public void setItems(List<Developer> items) {
        this.items = items;
    }
}