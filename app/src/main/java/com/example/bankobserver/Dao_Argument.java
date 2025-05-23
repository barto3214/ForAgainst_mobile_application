package com.example.bankobserver;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Dao_Argument {
    @Insert
    public void wstaw_argument(Argument argument);

    @Delete
    public void usun_argument(Argument argument);

    @Query("SELECT * FROM argumenty_table")
    public List<Argument> zwroc_argumenty();

    @Query("SELECT * FROM argumenty_table ORDER BY priority DESC")
    public List<Argument> zwroc_posortowane();


}
