package com.singh9512gmail.mohit.criminalrecord.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.singh9512gmail.mohit.criminalrecord.Model.Criminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusharaggarwal on 01/11/17.
 */

public class DatabaseHandler  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CURRENT = "current";

    private static final String TABLE_NAME = "criminals";

    private static final String KEY_ID = "id";
    private static final String REF_ID = "ref_id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String VALID_FROM = "valid_from";
    private static final String VALID_TO = "valid_to";

    public DatabaseHandler(Context context){
        super(context,DATABASE_CURRENT, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CURRENT_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                REF_ID + " INTEGER DEFAULT NULL, " +
                NAME + " TEXT DEFAULT NULL, " +
                ADDRESS + " TEXT DEFAULT NULL, " +
                VALID_FROM + " TEXT DEFAULT NULL, " +
                VALID_TO + " TEXT DEFAULT NULL" +
                ")";

        sqLiteDatabase.execSQL(CREATE_CURRENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertRecord(Criminal criminal){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REF_ID , criminal.get_ref_id());
        values.put(NAME,criminal.get_name());
        values.put(ADDRESS,criminal.get_address());
        values.put(VALID_FROM,criminal.get_valid_from());
        values.put(VALID_TO,criminal.get_valid_to());

        long i  = db.insert(TABLE_NAME ,null , values);
        db.close();
    }

    public List<Criminal> getCriminal(String name ){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Criminal> allCriminals = new ArrayList<Criminal>();
        Cursor cursor = db.query(TABLE_NAME , new String[] {KEY_ID , REF_ID , NAME , ADDRESS , VALID_FROM , VALID_TO } , NAME  + "=?",
                 new String[] {name} , null, null , null , null);

        if(cursor.moveToFirst()){
            do{
                Criminal criminal =  new Criminal();
                criminal.set_id(cursor.getInt(0));
                criminal.set_ref_id(cursor.getString(1));
                criminal.set_name(cursor.getString(2));
                criminal.set_address(cursor.getString(3));
                criminal.set_valid_from(cursor.getString(4));
                criminal.set_valid_to(cursor.getString(5));

                allCriminals.add(criminal);
            }while (cursor.moveToNext());
        }
        return allCriminals;
    }

    public List<Criminal> getAllCriminals(){
        List<Criminal> allCriminals = new ArrayList<Criminal>();
        String selectQuery = "Select * from " + TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Criminal criminal =  new Criminal();
                criminal.set_id(cursor.getInt(0));
                criminal.set_ref_id(cursor.getString(1));
                criminal.set_name(cursor.getString(2));
                criminal.set_address(cursor.getString(3));
                criminal.set_valid_from(cursor.getString(4));
                criminal.set_valid_to(cursor.getString(5));

                allCriminals.add(criminal);
            }while (cursor.moveToNext());
        }
        return allCriminals;
    }

    public int updateCriminal(Criminal criminal){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REF_ID , criminal.get_ref_id());
        values.put(NAME,criminal.get_name());
        values.put(ADDRESS,criminal.get_address());
        values.put(VALID_FROM,criminal.get_valid_from());
        values.put(VALID_TO,criminal.get_valid_to());
        return db.update(TABLE_NAME, values , KEY_ID + "=?" , new String[] {String.valueOf(criminal.get_id())});
    }

    public void showAllDatabase(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from criminals",null);
        Log.v("TAG", DatabaseUtils.dumpCursorToString(cursor));
    }
}
