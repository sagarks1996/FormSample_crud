package com.example.formsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydb";
    public static final String TABLE_NAME = "my_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "firstname";
    public static final String COL_3 = "lastname";


    public Dbhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT , firstname TEXT ,lastname TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertdata(String firstname, String lastname){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,firstname);
        contentValues.put(COL_3,lastname);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }


    public Cursor getalldata(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " +TABLE_NAME,null);
        return cursor;

    }


    public Cursor getdata(String uid){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] whereArgs = {uid};
        //Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME, new String[]{COL_1 + "=?" + new String[]{id}});
        String query="select * from "+TABLE_NAME+" where "+COL_1+" = "+uid;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
       // sqLiteDatabase.close();
        return  cursor;
    }




}
