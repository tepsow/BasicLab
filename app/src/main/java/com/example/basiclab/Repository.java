package com.example.basiclab;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.basiclab.RoomRXJava.AppDataBase;
import com.example.basiclab.RoomRXJava.Colors;
import com.example.basiclab.RoomRXJava.ColorsDao;

import java.util.List;

import io.reactivex.Single;

public class Repository {
    private ColorsDao mColorDao;
    private Single<List<Colors>> mAllColors;

    Repository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mColorDao = db.getMyDao();
        mAllColors = mColorDao.getColors();
    }



    Single<List<Colors>> getmAllColors() {
        return mAllColors;
    }

    void insert(Colors color) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mColorDao.insertColors(color);
        });
    }
}
