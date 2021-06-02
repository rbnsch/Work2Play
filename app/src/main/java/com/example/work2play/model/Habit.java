package com.example.work2play.model;

public class Habit {

    int id;
    String title;
    int coinsOne;
    int coinsAll;
    int numberRep; //wie oft muss es in der Woche gemacht werden
    int numberRepDone; //wie oft wurde es bis jetzt gemacht (weekly reset? geht sowas in der Db?)

    public Habit() {

    }

    public Habit(String title, int coinsOne, int coinsAll, int numberRep, int numberRepDone) {
        this.title = title;
        this.coinsOne = coinsOne;
        this.coinsAll = coinsAll;
        this.numberRep = numberRep;
        this.numberRepDone = numberRepDone;
    }

    public Habit(int id, String title, int coinsOne, int coinsAll, int numberRep, int numberRepDone) {
        this.id = id;
        this.title = title;
        this.coinsOne = coinsOne;
        this.coinsAll = coinsAll;
        this.numberRep = numberRep;
        this.numberRepDone = numberRepDone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoinsOne(int coinsOne) {
        this.coinsOne = coinsOne;
    }

    public void setCoinsAll(int coinsAll) {
        this.coinsAll = coinsAll;
    }

    public void setNumberRep(int numberRep) {
        this.numberRep = numberRep;
    }

    public void setNumberRepDone(int numberRepDone) {
        this.numberRepDone = numberRepDone;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCoinsOne() {
        return coinsOne;
    }

    public int getCoinsAll() {
        return coinsAll;
    }

    public int getNumberRep() {
        return numberRep;
    }

    public int getNumberRepDone() {
        return numberRepDone;
    }

}
