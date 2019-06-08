package com.example.wdftm.cmrdb_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static int Cyear,Cmonth,CdayOMonth;
    static String Calen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
              //  Toast.makeText(getApplicationContext(),dayOfMonth+"  dd",Toast.LENGTH_LONG).show();
                Cyear = year;
                Cmonth = month+1;
                CdayOMonth = dayOfMonth;
                Calen = year +"/" + Cmonth + "/" + dayOfMonth;
                Toast.makeText(getApplicationContext(),Calen,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,dayOfCalen.class);
                startActivity(intent);
            }
        });

    }
}
