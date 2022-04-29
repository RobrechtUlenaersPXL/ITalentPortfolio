package be.pxl.robrecht.rideshare.data.model;

import java.io.Serializable;

public class Ride implements Serializable {
    private String id;
    private String start;
    private String stop;
    private String date;
    private String userName;
    private String hour;
    private String userid;
    private String userEmail;

    public Ride(){}
    public Ride(String start, String stop, String date,String hour, String userName,String uid, String email) {
        this.start = start;
        this.stop = stop;
        this.date = date;
        this.userName = userName;
        this.hour =hour;
        this.userid = uid;
        this.userEmail = email;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getUserid() {
        return userid;
    }
}
