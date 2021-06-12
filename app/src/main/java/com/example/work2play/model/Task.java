package com.example.work2play.model;

public class Task {

    private int id;
    private String title;
    private String description;
    private int coins;
    private int repeatable;
    private String repeatFrequency;
    private int projectId;
    private String deadlineDate;

    public Task() {

    }

    public Task(String title, String description, int coins, int repeatable, String repeatFrequency, int projectId, String deadlineDate) {
        this.title = title;
        this.description = description;
        this.coins = coins;
        this.repeatable = repeatable;
        this.repeatFrequency = repeatFrequency;
        this.projectId = projectId;
        this.deadlineDate =deadlineDate;
    }

    public Task(int id, String title, String description, int coins, int repeatable, String repeatFrequency, int projectId, String deadlineDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coins = coins;
        this.repeatable = repeatable;
        this.repeatFrequency = repeatFrequency;
        this.projectId = projectId;
        this.deadlineDate =deadlineDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setRepeatable(int repeatable) {
        this.repeatable = repeatable;
    }

    public void setRepeatFrequency(String repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCoins() {
        return coins;
    }

    public int getRepeatable() {
        return repeatable;
    }

    public String getRepeatFrequency() {
        return repeatFrequency;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }


}