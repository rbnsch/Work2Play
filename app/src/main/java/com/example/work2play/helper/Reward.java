package com.example.work2play.helper;

public class Reward {

    int id;
    String titel;
    int coins;
    int repeatable;

    public Reward() {

    }

    public Reward(String titel, int coins, int repeatable) {
        this.titel = titel;
        this.coins = coins;
        this.repeatable = repeatable;
    }

    public Reward(int id, String titel, int coins, int repeatable) {
        this.id = id;
        this.titel = titel;
        this.coins = coins;
        this.repeatable = repeatable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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

    public String getTitel() {
        return this.titel;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getRepeatable() {
        return this.repeatable;
    }


}
