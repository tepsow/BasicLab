package com.example.basiclab;

import android.graphics.Color;
import android.os.Bundle;

import com.example.basiclab.RoomRXJava.AppDataBase;
import com.example.basiclab.RoomRXJava.Colors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
   NavController navController;
  private Integer color;
  private Random rnd = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "AppDataBase").build();
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        db.getMyDao().getColors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Colors>>() {
                    @Override
                    public void onSuccess(List<Colors> colors) {
                       if(!colors.isEmpty()){
                        for(Colors color : colors){
                                model.setColor(new Integer(color.getColorId()), new Integer(color.getColorValue()));

                        }
                    }}

                    @Override
                    public void onError(Throwable e) {
                        // ...
                    }
                });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view,new Integer(navController.getCurrentDestination().getId()).toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // navController.getCurrentDestination().getLabel().toString()

                color= new Integer(Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
                switch(navController.getCurrentDestination().getId()) {
                    case (R.id.FirstFragment):
                        model.setColor(1, color);
                        break;
                    default:break;
                }





            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String message = "";


        switch (id) {
            case R.id.action_settings: message = "Settings"; break;
            case R.id.go_to_fragment1: navController.navigate(R.id.FirstFragment); message = "First Fragment"; break;
            case R.id.go_to_fragment2: navController.navigate(R.id.SecondFragment);message = "Second Fragment";break;
            case R.id.go_to_fragment3: navController.navigate(R.id.ThirdFragment); message = "Third Fragment";break;
        }

        Snackbar.make(findViewById(R.id.rootLayout), message, Snackbar.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }



}