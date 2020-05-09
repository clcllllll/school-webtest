package com.clweb.labweb.entities;

import java.util.Date;

public class Equipment {
    public Equipment(){

    }
    String id,eqmode,eqname,eqsize;
    double eqprice;
    int eqcount;
    Date buydate,deaddate;
    String manufacturer,manager;
    int status;

    public Equipment(String id, String eqmode, String eqname, String eqsize, double eqprice, int eqcount, Date buydate, Date deaddate, String manufacturer, String manager, int status) {
        super();
        this.id = id;
        this.eqmode = eqmode;
        this.eqname = eqname;
        this.eqsize = eqsize;
        this.eqprice = eqprice;
        this.eqcount = eqcount;
        this.buydate = buydate;
        this.deaddate = deaddate;
        this.manufacturer = manufacturer;
        this.manager = manager;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + id + '\'' +
                ", eqmode='" + eqmode + '\'' +
                ", eqname='" + eqname + '\'' +
                ", eqsize='" + eqsize + '\'' +
                ", eqprice=" + eqprice +
                ", eqcount=" + eqcount +
                ", buydate=" + buydate +
                ", deaddate=" + deaddate +
                ", manufacturer='" + manufacturer + '\'' +
                ", manager='" + manager + '\'' +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEqmode() {
        return eqmode;
    }

    public void setEqmode(String eqmode) {
        this.eqmode = eqmode;
    }

    public String getEqname() {
        return eqname;
    }

    public void setEqname(String eqname) {
        this.eqname = eqname;
    }

    public String getEqsize() {
        return eqsize;
    }

    public void setEqsize(String eqsize) {
        this.eqsize = eqsize;
    }

    public double getEqprice() {
        return eqprice;
    }

    public void setEqprice(double eqprice) {
        this.eqprice = eqprice;
    }

    public int getEqcount() {
        return eqcount;
    }

    public void setEqcount(int eqcount) {
        this.eqcount = eqcount;
    }

    public Date getBuydate() {
        return buydate;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }

    public Date getDeaddate() {
        return deaddate;
    }

    public void setDeaddate(Date deaddate) {
        this.deaddate = deaddate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
