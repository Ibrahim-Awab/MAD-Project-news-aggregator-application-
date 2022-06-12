package com.example.midterm;

public class NewsF
{
    int id;
    String description;
    String url;
    String heading;

    public NewsF(int id, String description, String url, String heading) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.heading = heading;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
