package com.example.wdftm.cmrdb_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class SQLite extends SQLiteOpenHelper {
    int i = 1;
    SQLiteDatabase Account;
    // String name[] = new String[2];
    private static final String Database_Name = "APP.db";
    private static final int Database_Version = 1;

    private static final String Table_Account = "Account";

    private static final String Col_Id = "ID";
    private static final String Col_Date = "DATE";
    private static final String Col_Category = "CATEGORY";
    private static final String Col_Name = "NAME";
    private static final String Col_Price = "PRICE";

    public SQLite(Context context) {
        super(context, Database_Name, null, 1);
        Account = this.getWritableDatabase();
    }
    //建立資料庫
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Account_Table = "CREATE TABLE " + Table_Account +
                " (" +
                Col_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_Date + " TEXT," +
                Col_Category + " VARCHAR," +
                Col_Name + " VARCHAR," +
                Col_Price + " INT" +
                ")";
        db.execSQL(Create_Account_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Account);
        onCreate(db);
    }
    //輸入資料到SQLite
    public void insertData(String DATE, String CATEGORY, String NAME, int PRICE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_Date, DATE);
        contentValues.put(Col_Category, CATEGORY);
        contentValues.put(Col_Name, NAME);
        contentValues.put(Col_Price, PRICE);

        db.insert(Table_Account, null, contentValues);

    }
    //取得特定日期共有幾筆資料
    public int getDataQuantity(String Calen) {
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE DATE =" + "'" + Calen + "'", null);
        while (cursor.moveToNext()) {
            i++;
        }
        db.close();
        return i;
    }
    //取得指定欄位資料，以Hashmap型態回傳
    public HashMap<String, String> getData(String Calen, int columnIndex) {

        HashMap<String, String> map = new HashMap<String, String>();
        SQLiteDatabase db = this.getWritableDatabase(); //取得寫入資料庫權限

        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE DATE =" + "'" + Calen + "'", null); //取得日曆日期的所有資料

        //把對應的資料庫欄位資料放進map
        while (cursor.moveToNext()) {
            map.put(Integer.toString(i), cursor.getString(columnIndex));
            i++;
        }
        i = 1;
        db.close();
        return map;
    }
    //取得總金額的資料
    public int GetTotalPrice(String Calen) {
        int a = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT sum(PRICE) FROM Account WHERE DATE =" + "'" + Calen + "'", null);

        while (cursor.moveToNext())
            a = cursor.getInt(0);
        db.close();
        return a;
    }
    public int GetcCategory_TotalPrice_Month(String date,String string){
        SQLiteDatabase db = this.getWritableDatabase();
        int a = 0;
        Cursor cursor = db.rawQuery("SELECT sum(PRICE) FROM Account WHERE DATE like "
                + "'" + date + "'"
                +"AND CATEGORY = "+"'"+ string + "'", null);
        while (cursor.moveToNext())
            a = cursor.getInt(0);
        db.close();
        return a;
    }
}