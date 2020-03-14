package com.example.lmsclone.Model;

public class AdminOrders
{
    String name, phone, address, state, time, totalAmount, city, date, postal_code;


    public AdminOrders()
    {
    }

    public AdminOrders(String name, String phone, String address, String state, String time, String totalAmount, String city, String date, String postal_code) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.time = time;
        this.totalAmount = totalAmount;
        this.city = city;
        this.date = date;
        this.postal_code = postal_code;
    }


    public String getName() {
        return name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}




