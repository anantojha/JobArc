package com.comp4905.jobarc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelHome {

    @SerializedName("jobList")
    @Expose
    private List<Job> items;

    public ModelHome() {
    }

    public ModelHome(List<Job> items) {
        this.items = items;
    }

    public List<Job> getItems() {
        return items;
    }

    public void setItems(List<Job> items) {
        this.items = items;
    }
}
