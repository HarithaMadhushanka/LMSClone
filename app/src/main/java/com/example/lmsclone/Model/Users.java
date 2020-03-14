package com.example.lmsclone.Model;

public class Users
{
    private String name, phone, username, id, email, password, image, address, degree, batch;

    public Users()
    {

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Users(String name, String phone, String username, String id, String email, String password, String image, String address, String degree, String batch) {
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.id = id;
        this.email = email;
        this.password = password;
        this.image = image;
        this.address = address;
        this.degree = degree;
        this.batch = batch;
    }
}
