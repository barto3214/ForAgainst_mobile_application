package com.example.bankobserver;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Argumenty_table")
public class Argument {
    @PrimaryKey(autoGenerate = true)
    public int ID;
    private int priority;
    public boolean czy_za;
    public String tytul;
    public int index;


    public Argument() {
    }

    public Argument(int index,int priority,boolean czy_za, String tytul) {
        this.ID = 0;
        this.index = index;
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCzy_za() {
        return czy_za;
    }

    public void setCzy_za(boolean czy_za) {
        this.czy_za = czy_za;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
