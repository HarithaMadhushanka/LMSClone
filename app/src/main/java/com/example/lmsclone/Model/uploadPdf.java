package com.example.lmsclone.Model;

public class uploadPdf {
    public String name, url, subject_id, sid;

    public uploadPdf(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public uploadPdf(String name, String sid, String subject_id, String url) {
        this.name = name;
        this.url = url;
        this.subject_id = subject_id;
        this.sid = sid;
    }
}
