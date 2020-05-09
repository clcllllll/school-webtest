package com.clweb.labweb.entities;

import java.util.Date;

public class ScrappedEquipment {

    int id;
    String eqname;
    double eqprice;
    Date date;

    public ScrappedEquipment(){

    }

    public ScrappedEquipment(int id, String eqname, double eqprice, Date date) {
        super();
        this.id = id;
        this.eqname = eqname;
        this.eqprice = eqprice;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ScrappedEquipment{" +
                "id=" + id +
                ", eqname='" + eqname + '\'' +
                ", eqprice=" + eqprice +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEqname() {
        return eqname;
    }

    public void setEqname(String eqname) {
        this.eqname = eqname;
    }

    public double getEqprice() {
        return eqprice;
    }

    public void setEqprice(double eqprice) {
        this.eqprice = eqprice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
