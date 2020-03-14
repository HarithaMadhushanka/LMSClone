package com.example.lmsclone.Model;

public class Subjects {
    public String getName() {
        return name;
    }
    public Subjects(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Subjects(String name, String filepath, String sid) {
        this.name = name;
        this.filepath = filepath;
        this.sid = sid;
    }

    private String name, filepath, sid, subject_id;

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public Subjects(String subject_id) {
        this.subject_id = subject_id;
    }
}
