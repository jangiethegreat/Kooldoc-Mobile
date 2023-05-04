package com.example.kooldocandroidfinal.CanceledServices;

public class Cancelled {

    String id,bookType,serviceType,acType,acBrand,unitType,noUnit,serviceDate,serviceTime,servicePrice,serviceStatus;

    Cancelled(){

    }

    public Cancelled(String id, String bookType, String serviceType, String acType, String acBrand, String unitType, String noUnit, String serviceDate, String serviceTime, String servicePrice, String serviceStatus) {
        this.id = id;
        this.bookType = bookType;
        this.serviceType = serviceType;
        this.acType = acType;
        this.acBrand = acBrand;
        this.unitType = unitType;
        this.noUnit = noUnit;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.servicePrice = servicePrice;
        this.serviceStatus = serviceStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public String getAcBrand() {
        return acBrand;
    }

    public void setAcBrand(String acBrand) {
        this.acBrand = acBrand;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getNoUnit() {
        return noUnit;
    }

    public void setNoUnit(String noUnit) {
        this.noUnit = noUnit;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
