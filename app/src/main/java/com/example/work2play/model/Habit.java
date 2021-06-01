package com.example.work2play.model;

public class Habit {

    int id;
    String title;
    int coinsOne;
    int coinsAll;
    int numberRep; //wie oft muss es in der Woche gemacht werden
    int numberRepDone; //wie oft wurde es bis jetzt gemacht (weekly reset? geht sowas in der Db?)
}
