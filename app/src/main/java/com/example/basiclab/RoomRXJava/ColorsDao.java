package com.example.basiclab.RoomRXJava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

@Dao
public interface ColorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertColors(@NonNull Colors color);

    @Query("SELECT * FROM Colors LIMIT 3")
    Single <List<Colors>> getColors();

    @Query("DELETE FROM Colors ")
    void deleteAllColors();


}
