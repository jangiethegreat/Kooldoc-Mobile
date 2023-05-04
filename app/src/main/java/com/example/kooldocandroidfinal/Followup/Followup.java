package com.example.kooldocandroidfinal.Followup;

public class Followup {

    String id,serviceId,adminId,reason,followupDate,followupTime,followupReport,followupStatus,createdat,updatedat,customerLastname,adminLastname,fullAddress,techLastname,fullContact;

    public Followup(String id, String serviceId, String adminId, String reason, String followupDate, String followupTime, String followupReport, String followupStatus, String createdat, String updatedat, String customerLastname, String adminLastname, String fullAddress, String techLastname, String fullContact) {
        this.id = id;
        this.serviceId = serviceId;
        this.adminId = adminId;
        this.reason = reason;
        this.followupDate = followupDate;
        this.followupTime = followupTime;
        this.followupReport = followupReport;
        this.followupStatus = followupStatus;
        this.createdat = createdat;
        this.updatedat = updatedat;
        this.customerLastname = customerLastname;
        this.adminLastname = adminLastname;
        this.fullAddress = fullAddress;
        this.techLastname = techLastname;
        this.fullContact = fullContact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public String getFollowupTime() {
        return followupTime;
    }

    public void setFollowupTime(String followupTime) {
        this.followupTime = followupTime;
    }

    public String getFollowupReport() {
        return followupReport;
    }

    public void setFollowupReport(String followupReport) {
        this.followupReport = followupReport;
    }

    public String getFollowupStatus() {
        return followupStatus;
    }

    public void setFollowupStatus(String followupStatus) {
        this.followupStatus = followupStatus;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public void setCustomerLastname(String customerLastname) {
        this.customerLastname = customerLastname;
    }

    public String getAdminLastname() {
        return adminLastname;
    }

    public void setAdminLastname(String adminLastname) {
        this.adminLastname = adminLastname;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getTechLastname() {
        return techLastname;
    }

    public void setTechLastname(String techLastname) {
        this.techLastname = techLastname;
    }

    public String getFullContact() {
        return fullContact;
    }

    public void setFullContact(String fullContact) {
        this.fullContact = fullContact;
    }
}
