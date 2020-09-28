package com.example.todolist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    private String title;

    private String body;
    private int color;
    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("date") private String date;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
