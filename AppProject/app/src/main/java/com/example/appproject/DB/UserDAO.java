package com.example.appproject.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appproject.User;

import java.util.List;


@Dao
public interface UserDAO {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User users);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    List<User> getUsersById(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username ")
    User getUsersByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("DELETE FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username")
    void deleteUser(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId ")
    User getUserByUserId(int userId);
}