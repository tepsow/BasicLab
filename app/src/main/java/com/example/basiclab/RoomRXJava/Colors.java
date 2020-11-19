package com.example.basiclab.RoomRXJava;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Colors {
    @NonNull
    @PrimaryKey
    private int colorId;
    @ColumnInfo(name = "Fragment Resource Id")
    private  int fragmentResId;
    @ColumnInfo(name = "Color Value")
    private  int colorValue;

    public Colors(@NonNull int colorId, int fragmentResId, int colorValue){
        this.fragmentResId = fragmentResId;
        this.colorValue = colorValue;
    }

    public int getFragmentResId(){
        return  fragmentResId;
    }
    public int getColorValue(){
        return  colorValue;
    }
    public int getColorId(){
        return colorId;
    }

}
