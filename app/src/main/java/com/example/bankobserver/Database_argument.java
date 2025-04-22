package com.example.bankobserver;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Argument.class}, version = 2,exportSchema = false)
public abstract class Database_argument extends RoomDatabase {
    public abstract Dao_Argument zwroc_Dao_arg();
}
