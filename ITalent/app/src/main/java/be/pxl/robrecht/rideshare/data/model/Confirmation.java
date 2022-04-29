package be.pxl.robrecht.rideshare.data.model;

import java.io.Serializable;

public class Confirmation implements Serializable {
    private String id;
    private String timestamp;
    private String userId1;
    private String userId2;
    private String location;

    public Confirmation() {
    }

    public Confirmation(String time, String uid1, String uid2,String loc) {
        this.timestamp = time;
        this.userId1 = uid1;
        this.userId2 = uid2;
        this.location = loc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

