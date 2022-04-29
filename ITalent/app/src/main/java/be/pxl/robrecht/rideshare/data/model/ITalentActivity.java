package be.pxl.robrecht.rideshare.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ITalentActivity implements Serializable {
    private String id;
    private String date;
    private String description;
    private String duration;
    private String domain;
    private String location;
    private String longForm;
    private ArrayList<String> media;
    private String title;

    public ITalentActivity() {
    }

    public ITalentActivity(String date, String description, String duration, String location, String longForm, ArrayList<String> media, String title, String domain) {
        this.date = date;
        this.description = description;
        this.duration = duration;
        this.location = location;
        this.longForm = longForm;
        this.media = media;
        this.title = title;
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongForm() {
        return longForm;
    }

    public void setLongForm(String longForm) {
        this.longForm = longForm;
    }

    public ArrayList<String> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<String> media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}


