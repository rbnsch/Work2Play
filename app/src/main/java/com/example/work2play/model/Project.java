package com.example.work2play.model;

public class Project {

    int id;
    String title;

    public Project() {

    }

    public Project(String title) {
        this.title = title;

    }

    public Project(int id, String title) {
        this.id = id;
        this.title = title;

    }



    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }



}
