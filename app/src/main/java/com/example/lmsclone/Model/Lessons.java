package com.example.lmsclone.Model;

public class Lessons {
    public String getLid() {
        return lid;
    }

    public Lessons(){

    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Lessons(String lid, String name) {
        this.lid = lid;
        this.name = name;
    }

    public Lessons(String link) {
        this.link = link;
    }

    private String lid, name, link;
}
