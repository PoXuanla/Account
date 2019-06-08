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
        insertDate = (EditText)findViewById(R.id.insertDate);
        price = (EditText)findViewById(R.id.price);
        thingname =(EditText)findViewById(R.id.thingname);
        insertSpinner = (Spinner)findViewById(R.id.insert_spinner);
        output = (Button)findViewById(R.id.output);
        OpenHelper = new SQLite(this);

        //設定 insertData 的預設文字
        Date date = new Date();
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMdd");
        String str = bartDateFormat.format(date);
        String strr = "";
        if(str.charAt(5)=='0'){
           for(int i= 0;i<str.length();i++){
               if(i==5) continue;
               if(i==4 || i==6)strr+="/";
               strr += str.charAt(i);
           }
        }
        insertDate.setText(strr);
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
