package com.example.application3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User where username= :username and password= :password")
    User getUser(String username, String password);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);



}