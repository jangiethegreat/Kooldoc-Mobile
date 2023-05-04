package com.example.kooldocandroidfinal.EmployeeHistory;

public class Service {

    String id,bookType,serviceType,acType,acBrand,unitType,noUnit,serviceDate,serviceTime,servicePrice,adminLastname,techLastname,customerFullname,fullAddress,fullContact;

    Service(){

    }



    public Service(String id, String bookType, String serviceType, String acType, String acBrand, String unitType, String noUnit, String serviceDate, String serviceTime, String servicePrice, String adminLastname, String techLastname,String customerFullname, String fullAddress, String fullContact) {
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
        this.adminLastname = adminLastname;
        this.techLastname = techLastname;
        this.customerFullname = customerFullname;
        this.fullAddress = fullAddress;
        this.fullContact = fullContact;

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

    public String getAdminLastname() {
        return adminLastname;
    }

    public void setAdminLastname(String adminLastname) {
        this.adminLastname = adminLastname;
    }

    public String getTechLastname() {
        return techLastname;
    }

    public void setTechLastname(String techLastname) {
        this.techLastname = techLastname;
    }
    public String getCustomerFullname() {
        return customerFullname;
    }

    public void setCustomerFullname(String customerFullname) {
        this.customerFullname = customerFullname;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFullContact() {
        return fullContact;
    }

    public void setFullContact(String fullContact) {
        this.fullContact = fullContact;
    }
}
