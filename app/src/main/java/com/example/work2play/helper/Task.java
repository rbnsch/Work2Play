package com.example.work2play.helper;

public class Task {

    int id;
    String titel;
    String description;
    int coins;
    int repeatable;
    String repeatFrequency;
    int projectId;
    String deadlineDate;

    public Task() {

    }

    public Task(String titel, String description, int coins, int repeatable, String repeatFrequency, int projectId, String deadlineDate) {
        this.titel = titel;
        this.description = description;
        this.coins = coins;
        this.repeatable = repeatable;
        this.repeatFrequency = repeatFrequency;
        this.projectId = projectId;
        this.deadlineDate =deadlineDate;
    }

    public Task(int id, String titel, String description, int coins, int repeatable, String repeatFrequency, int projectId, String deadlineDate) {
        this.id = id;
        this.titel = titel;
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

    public void setTitel(String titel) {
        this.titel = titel;
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

    public String getTitel() {
        return titel;
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
