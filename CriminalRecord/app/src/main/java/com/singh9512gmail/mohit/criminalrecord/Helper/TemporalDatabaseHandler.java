package com.singh9512gmail.mohit.criminalrecord.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.singh9512gmail.mohit.criminalrecord.Model.Criminal;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusharaggarwal on 01/11/17.
 */

public class TemporalDatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TEMPORAL = "temporal";

    private static final String TABLE_NAME = "criminals";

    private static final String KEY_ID = "id";
    private static final String REF_ID = "ref_id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String VALID_FROM = "valid_from";
    private static final String VALID_TO = "valid_to";

    public TemporalDatabaseHandler(Context context){
        super(context,DATABASE_TEMPORAL, null , DATABASE_VERSION);
    }

    public static List<Criminal> allCriminalNames = new ArrayList<>() ;

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

    public long insertRecord(Criminal criminal){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REF_ID , criminal.get_ref_id());
        values.put(NAME,criminal.get_name());
        values.put(ADDRESS,criminal.get_address());
        values.put(VALID_FROM,criminal.get_valid_from());
        values.put(VALID_TO,criminal.get_valid_to());

        long i  = db.insert(TABLE_NAME ,null , values);
        db.close();
        return i;
    }

    public List<Criminal> getCriminalAllNames(Criminal search ){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME , new String[] {KEY_ID , REF_ID , NAME , ADDRESS , VALID_FROM , VALID_TO } , KEY_ID  + "=?",
                new String[] {search.get_ref_id()} , null, null , null , null);
        if(cursor!= null){
            cursor.moveToFirst();
            Criminal criminal = new Criminal(cursor.getInt(0) , cursor.getString(1) , cursor.getString(2),
                    cursor.getString(3) , cursor.getString(4) , cursor.getString(5));
            allCriminalNames.add(criminal);
            if(criminal.get_ref_id().equals("null")){
                return allCriminalNames;
            }else{
                getCriminalAllNames(criminal);
            }
        }
        return  null;
    }

    public void showAllDatabase(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from criminals",null);
        Log.v("TAG",DatabaseUtils.dumpCursorToString(cursor));
    }
}
