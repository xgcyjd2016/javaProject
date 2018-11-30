package com.xgcyjd.po;

import java.util.Date;

public class SignReord {

    int id;
    int user_id;
    String date;

    public SignReord(){}
    public SignReord(int user_id, String date) {
        this.user_id = user_id;
        this.date = date;
    }

    @Override
    public String toString() {
        return "SignReord{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
