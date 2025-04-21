package com.example.bankobserver;

public class Argument {
    private int priority;
    public boolean czy_za;
    public String tytul;
    public int ID;

    public Argument(int priority,int id,boolean czy_za, String tytul) {
        this.ID = id;
        this.czy_za = czy_za;
        this.tytul = tytul;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Argument " + ID + " \ntreść: " + tytul;
    }

    public int getID() {
        return ID;
    }

    public boolean isCzy_za() {
        return czy_za;
    }

    public String getTytul() {
        return tytul;
    }
}
