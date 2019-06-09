package com.example.wdftm.cmrdb_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;

public class Main extends AppCompatActivity {
    ImageButton Main_insertButton,Main_viewButton,Main_checkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Main_insertButton = (ImageButton)findViewById(R.id.Main_insertButton);
        Main_viewButton = (ImageButton)findViewById(R.id.Main_viewButton);
        Main_checkButton = (ImageButton)findViewById(R.id.Main_checktButton);

        Main_insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,Insert_view.class);
                startActivity(intent);
            }
        });
        Main_viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,MainActivity.class);
                startActivity(intent);

            }
        });
        Main_checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,Category.class);
                startActivity(intent);

            }
        });

    }
}
