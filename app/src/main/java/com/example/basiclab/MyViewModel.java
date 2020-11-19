package com.example.basiclab;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.basiclab.RoomRXJava.AppDataBase;
import com.example.basiclab.RoomRXJava.Colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class MyViewModel extends ViewModel {

    public HashMap<Integer, MutableLiveData<Integer>> color = new HashMap<Integer, MutableLiveData<Integer>>();

    public void initValues(){
        color.put(1, new MutableLiveData<Integer>());
        color.put(2, new MutableLiveData<Integer>());
        color.put(3, new MutableLiveData<Integer>());
    }
    public MutableLiveData<Integer> getColor(int colorId) {
        return color.get(colorId);
    }
    public void setColor(int colorId, Integer colorValue) {
        if (color.get(colorId) != null) {
            color.get(colorId).setValue(colorValue);
        }
    }
   /* @Override
    protected void onCleared(Application application) {
        super.onCleared();
        AppDataBase db = AppDataBase.getDatabase(application);
        List<Colors> colors = new ArrayList<>();
        if (color.get(1) != null) {
           // colors.add(new Colors(1,color.get(1)))
        }

        Completable.fromAction(() -> db.getMyDao()
                .insertColors())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }*/
}


