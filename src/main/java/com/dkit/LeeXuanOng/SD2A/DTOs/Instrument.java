package com.dkit.LeeXuanOng.SD2A.DTOs;

public class Instrument {

    private int id;
    private String insName;
    private String insDesc;
    private int insStrock;
    private double insPrice;
    private String insCategory;

    public Instrument(int id, String insName, String insDesc, int insStrock, double insPrice, String insCategory) {
        this.id = id;
        this.insName = insName;
        this.insDesc = insDesc;
        this.insStrock = insStrock;
        this.insPrice = insPrice;
        this.insCategory = insCategory;
    }

    public Instrument(String insName, String insDesc, int insStrock, double insPrice, String insCategory) {
        //id need to be a new id that get from the mysql db;
        this.insName = insName;
        this.insDesc = insDesc;
        this.insStrock = insStrock;
        this.insPrice = insPrice;
        this.insCategory = insCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getInsDesc() {
        return insDesc;
    }

    public void setInsDesc(String insDesc) {
        this.insDesc = insDesc;
    }

    public int getInsStrock() {
        return insStrock;
    }

    public void setInsStrock(int insStrock) {
        this.insStrock = insStrock;
    }

    public double getInsPrice() {
        return insPrice;
    }

    public void setInsPrice(double insPrice) {
        this.insPrice = insPrice;
    }

    public String getInsCategory() {
        return insCategory;
    }

    public void setInsCategory(String insCategory) {
        this.insCategory = insCategory;
    }
}
