package com.example.wdftm.cmrdb_project;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

public class dayOfCalen  extends AppCompatActivity {

    private SQLiteDatabase db = null; //定義一個sqlite

    TextView CalenTitle, CalenTotalPrice;
    ListView ListView01;

    SQLite OpenHelper; //建立SQlite Class
    SQLiteDatabase Account;
    String Calen ; //點選日曆出現的日期
    int Dataquantity; //該日期的資料總筆數
    String Category[], Name[], Price[]; //建立分類、名稱、價格陣列，儲存資料庫的資料
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayofcalen);

        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        OpenHelper = new SQLite(this);  //建立一個SQlite class
        Calen = MainActivity.Calen;
        //生成物件
        CalenTitle = (TextView) findViewById(R.id.CalenTitle);
        CalenTotalPrice = (TextView) findViewById(R.id.CalenTotalPrice);
        ListView01 = (ListView) findViewById(R.id.ListView01);


        CalenTitle.setText(MainActivity.Cyear + "年" + MainActivity.Cmonth + "月" + MainActivity.CdayOMonth + "日");//設定CalenTitle 日期名稱

        Dataquantity = OpenHelper.getDataQuantity(Calen); //取得資料總筆數
        int a = OpenHelper.GetTotalPrice(Calen); //取得該日期總花費金額

        CalenTotalPrice.setText("總花費金額 : "+Integer.toString(a)); //設定CalenTotalPrice 總花費金額

        //設定三個陣列的大小為資料數筆數的數量
        Category = new String[Dataquantity];
        Name = new String[Dataquantity];
        Price = new String[Dataquantity];

        //儲存資料庫的資料放到相對應的陣列裡
        Get_columnData(2, Category);
        Get_columnData(3, Name);
        Get_columnData(4, Price);



          //輸入資料到SQLite
      //  OpenHelper.insertData("2019/6/6","FOOD","Rice",11111);
//        OpenHelper.insertData("2019/5/24","FOOD","Noodle",55);
//        OpenHelper.insertData("2019/5/24","FRUIT","Orange",23);
//        OpenHelper.insertData("2019/5/24","DRINK","Soda",66);

        //設定ListView01 的內容
        Myadapter md = new Myadapter(this, Category, Name, Price);
        ListView01.setAdapter(md);
    }

    public void Get_columnData(int col, String[] data) {
        //取得資料庫特定欄位的資料(Hashmap形式回傳)
        HashMap<String, String> map = OpenHelper.getData(Calen, col);
        int i = 0;
        //將Hashmap資料變成陣列的資料
        for (Object key : map.keySet()) {
            data[i] = map.get(key);
            i++;
        }
    }

}

