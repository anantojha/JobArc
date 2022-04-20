package com.comp4905.jobarc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicantsModel {
    @SerializedName("resumeList")
    @Expose
    private List<Resume> items;

    public ApplicantsModel() {
    }

    public ApplicantsModel(List<Resume> items) {
        this.items = items;
    }

    public List<Resume> getItems() {
        return items;
    }

    public void setItems(List<Resume> items) {
        this.items = items;
    }
}
