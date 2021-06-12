package com.example.work2play;

public class HabitDataHelper {
    private int id;
    private String title;
    private int numberRep;
    private int numberRepDone;
    private int coinsOne;
    private int coinsAll;


    public HabitDataHelper() {

    }

    public HabitDataHelper(String title, int numberRep, int numberRepDone, int coinsOne, int coinsAll) {
        this.title = title;
        this.numberRep = numberRep;
        this.numberRepDone = numberRepDone;
        this.coinsOne = coinsOne;
        this.coinsAll = coinsAll;
    }
    public HabitDataHelper(int id, String title, int coinsOne, int coinsAll, int numberRep, int numberRepDone) {
        this.id = id;
        this.title = title;
        this.coinsOne = coinsOne;
        this.coinsAll = coinsAll;
        this.numberRep = numberRep;
        this.numberRepDone = numberRepDone;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberRep() {
        return numberRep;
    }

    public int getNumberRepDone() {
        return numberRepDone;
    }

    public int getCoinsOne() {
        return coinsOne;
    }

    public int getCoinsAll() {
        return coinsAll;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setNumberRep(int numberRep) {
        this.numberRep = numberRep;
    }

    public void setNumberRepDone(int numberRepDone) {
        this.numberRepDone = numberRepDone;
    }

    public void setCoinsOne(int coinsOne) {
        this.coinsOne = coinsOne;
    }

    public void setCoinsAll(int coinsAll) {
        this.coinsAll = coinsAll;
    }
}
