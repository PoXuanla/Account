package com.example.wdftm.cmrdb_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Insert_view extends AppCompatActivity {
    EditText insertDate,price,thingname;
    Button output;
    Spinner insertSpinner;
    SQLite OpenHelper;
    String spinnerData[] = {"食","衣","住","行","樂"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_view);
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        insertDate = (EditText)findViewById(R.id.insertDate);
        price = (EditText)findViewById(R.id.price);
        thingname =(EditText)findViewById(R.id.thingname);
        insertSpinner = (Spinner)findViewById(R.id.insert_spinner);
        output = (Button)findViewById(R.id.output);
        OpenHelper = new SQLite(this);

        //設定 insertData 的預設文字
        Date date = new Date();
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
        String str = bartDateFormat.format(date);
        String year = str.substring(0, 4);
        String month = str.substring(4, 6);
        String day = str.substring(6, 8);
        str = year + "/" + month + "/" + day;

        insertDate.setText(str);
        //設定種類的字串
        ArrayAdapter<String> spinner = new ArrayAdapter<>(Insert_view.this,android.R.layout.simple_list_item_1,spinnerData);
        insertSpinner.setAdapter(spinner);

        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"新增一筆資料",Toast.LENGTH_SHORT).show();
                OpenHelper.insertData(insertDate.getText().toString(),insertSpinner.getSelectedItem().toString(),thingname.getText()
                        .toString(),Integer.parseInt(price.getText().toString()));
                Intent intent =  new Intent(Insert_view.this,Main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
