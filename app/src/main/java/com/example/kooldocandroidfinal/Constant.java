package com.example.kooldocandroidfinal;

public class Constant {

    public static final String URL = "https://kooldocbusiness.com/";
    public static final String HOME = URL+"api";
    public static final String LOGIN = HOME+"/mobile-login";
    public static final String REGISTER = HOME+"/mobile-register";
    public static final String SERVICES = HOME+"/mobile-book";

    //CUSTOMER
    public static final String CUSTOMERALL = HOME + "/customer-booked-services/";
    public static final String GETCUSTOMERINFO = HOME+ "/customer-profile/";
    public static final String CANCELBOOK = HOME + "/customer-cancel-service/";
    public static final String COMPLETEDBOOKING = HOME + "/customer-completed-services/";
    public static final String FEEDBACK = HOME + "/add-feedback/";
    public static final String CUSTOMERUPDATE = HOME + "/customer-update/";


    //TECHNICIAN
    public static final String TECHALLFOLLOWUP = HOME + "/technician-followup-services/";
    public static final String UPDATEFOLLOW = HOME + "/technician-followup-report/";
    public static final String TECHNICIANALL = HOME + "/technician-assigned-services/";
    public static final String SERVICEREPORT = HOME + "/technician-service-report/";
    public static final String FOLLOWUP = HOME + "/followup-book/";
    public static final String GETTECHNICIANINFO = HOME+ "/technician-profile/";
}
