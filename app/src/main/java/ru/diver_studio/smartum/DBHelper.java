package ru.diver_studio.smartum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Oleg Potkin on 09.03.2015.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HometaskDB.db";
    public static final String HOMETASK_TABLE_NAME = "hometasks";
    public static final String HOMETASK_COLUMN_ID = "id";
    public static final String HOMETASK_COLUMN_SUBJECT = "subject";
    public static final String HOMETASK_COLUMN_DATE = "date";
    public static final String HOMETASK_COLUMN_DESCRIPTION = "description";
    public static final String HOMETASK_COLUMN_TEACHER = "teacher";

    private HashMap hp;

    public DBHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table hometasks " +
                "(id integer primary key, subject text,date text,description text, teacher text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS hometasks");
        onCreate(db);
    }

    public boolean insertContact (String subject, String date, String description, String teacher){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("subject", subject);
        contentValues.put("date", date);
        contentValues.put("description", description);
        contentValues.put("teacher", teacher);

        db.insert("hometasks", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from hometasks where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, HOMETASK_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String subject, String date, String description, String teacher){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", subject);
        contentValues.put("date", date);
        contentValues.put("description", description);
        contentValues.put("teacher", teacher);
        db.update("hometasks", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("hometasks",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList getAllCotacts(){
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from hometasks", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(HOMETASK_COLUMN_SUBJECT)));
            res.moveToNext();
        }
        return array_list;
    }
}