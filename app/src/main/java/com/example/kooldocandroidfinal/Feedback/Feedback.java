package com.example.kooldocandroidfinal.Feedback;

public class Feedback {

    String id,bookType,serviceType,acType,acBrand,acHp,cooling,description,mechanicalNoise,electricConnectivity,unitType,noUnit,serviceDate,serviceTime,servicePrice,serviceStatus,adminLastname,technicianLastname;


    public Feedback(){
    }



    public Feedback(String id, String bookType, String description,String actype,String serviceType,String acHp, String cooling, String mechanicalNoise, String electricConnectivity, String acBrand, String unitType, String noUnit, String serviceDate, String serviceTime, String servicePrice, String serviceStatus, String adminLastname, String technicianLastname) {
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
        this.adminLastname = adminLastname;
        this.technicianLastname = technicianLastname;
        this.acHp = acHp;
        this.cooling = cooling;
        this.description = description;
        this.mechanicalNoise = mechanicalNoise;
        this.electricConnectivity = electricConnectivity;
    }

    public String getAcHp() {
        return acHp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAcHp(String acHp) {
        this.acHp = acHp;
    }

    public String getCooling() {
        return cooling;
    }

    public void setCooling(String cooling) {
        this.cooling = cooling;
    }

    public String getMechanicalNoise() {
        return mechanicalNoise;
    }

    public void setMechanicalNoise(String mechanicalNoise) {
        this.mechanicalNoise = mechanicalNoise;
    }

    public String getElectricConnectivity() {
        return electricConnectivity;
    }

    public void setElectricConnectivity(String electricConnectivity) {
        this.electricConnectivity = electricConnectivity;
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

    public String getAdminLastname() {
        return adminLastname;
    }

    public void setAdminLastname(String adminLastname) {
        this.adminLastname = adminLastname;
    }

    public String getTechnicianLastname() {
        return technicianLastname;
    }

    public void setTechnicianLastname(String technicianLastname) {
        this.technicianLastname = technicianLastname;
    }
}
