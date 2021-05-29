package com.example.work2play;

public class HabitDataHelper {
    private String description;
    private int numberRep;
    private int numberRepDone;
    private int coinsOne;
    private int coinsAll;

    public HabitDataHelper(String description, int numberRep, int numberRepDone, int coinsOne, int coinsAll) {
        this.description = description;
        this.numberRep = numberRep;
        this.numberRepDone = numberRepDone;
        this.coinsOne = coinsOne;
        this.coinsAll = coinsAll;
    }


    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
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
