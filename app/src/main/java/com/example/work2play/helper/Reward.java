package com.example.work2play.helper;

public class Reward {

    int id;
    String title;
    int coins;
    int repeatable;

    public Reward() {

    }

    public Reward(String title, int coins, int repeatable) {
        this.title = title;
        this.coins = coins;
        this.repeatable = repeatable;
    }

    public Reward(int id, String title, int coins, int repeatable) {
        this.id = id;
        this.title = title;
        this.coins = coins;
        this.repeatable = repeatable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setRepeatable(int repeatable) {
        this.repeatable = repeatable;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getRepeatable() {
        return this.repeatable;
    }


}
