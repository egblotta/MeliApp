
package com.example.meliapp.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResult {

    @SerializedName("results")
    @Expose
    private ArrayList<Result> results = null;

    @SerializedName("paging")
    @Expose
    private Paging paging;

    public ArrayList<Result> getResults() {return results;}

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Paging getPaging() {return paging;}

    public void setPaging(Paging paging) {this.paging = paging;}
}