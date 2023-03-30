package com.dkit.LeeXuanOng.SD2A.DTOs;

import java.util.Objects;

public class Instrument {

    private int id;
    private String insName;
    private String insDesc;
    private int insStock;
    private double insPrice;
    private String insCategory;

    public Instrument(int id, String insName, String insDesc, int insStrock, double insPrice, String insCategory) {
        this.id = id;
        this.insName = insName;
        this.insDesc = insDesc;
        this.insStock = insStrock;
        this.insPrice = insPrice;
        this.insCategory = insCategory;
    }

    public Instrument(String insName, String insDesc, int insStrock, double insPrice, String insCategory) {
        //id need to be a new id that get from the mysql db;
        this.insName = insName;
        this.insDesc = insDesc;
        this.insStock = insStrock;
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

    public int getInsStock() {
        return insStock;
    }

    public void setInsStock(int insStock) {
        this.insStock = insStock;
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

    @Override
    public String toString() {
        return '\n'+"Instrument{" +
                "id=" + id +
                ", insName='" + insName + '\'' +
                ", insDesc='" + insDesc + '\'' +
                ", insStrock=" + insStock +
                ", insPrice=" + insPrice +
                ", insCategory='" + insCategory + '\'' +
                '}' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrument that)) return false;
        return id == that.id || (insStock == that.insStock && Double.compare(that.insPrice, insPrice) == 0 && insName.equals(that.insName) && Objects.equals(insDesc, that.insDesc) && Objects.equals(insCategory, that.insCategory));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, insName, insDesc, insStock, insPrice, insCategory);
    }
}
