package com.example.lmsclone.Model;

public class Degrees {
    private String name, fid,sid;

    public String getName() {
        return name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Degrees(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Degrees(String name, String fid) {
        this.name = name;
        this.fid = fid;
    }

    public Degrees(){

    }


}
