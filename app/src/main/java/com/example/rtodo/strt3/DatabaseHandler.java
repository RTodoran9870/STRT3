package com.example.rtodo.strt3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context ctx;
    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      //  String CREATE_ALARM_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_DAY + " TEXT," + Constants.KEY_HOUR +
        //        " INTEGER,"    + Constants.KEY_MINUTE + " INTEGER," + Constants.KEY_HOURMINURE + "TEXT);";

        String CREATE_ALARM_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("+ Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_DAY + " TEXT," + Constants.KEY_HOUR + " INTEGER," +
                Constants.KEY_MINUTE + " INTEGER," + Constants.KEY_HOURMINURE + " TEXT);";

        db.execSQL(CREATE_ALARM_TABLE);
        Log.v("at least", "here");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + ", " + Constants.KEY_DAY + ", " + Constants.KEY_HOUR + ", " + Constants.KEY_MINUTE
         + ", " + Constants.KEY_HOURMINURE + ") VALUES (0, 'Monday', 7, 0, '7:00');");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + ", " + Constants.KEY_DAY + ", " + Constants.KEY_HOUR + ", " + Constants.KEY_MINUTE
                + ", " + Constants.KEY_HOURMINURE + ") VALUES (1, 'Tuesday', 7, 0, '7:00');");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + ", " + Constants.KEY_DAY + ", " + Constants.KEY_HOUR + ", " + Constants.KEY_MINUTE
                + ", " + Constants.KEY_HOURMINURE + ") VALUES (2, 'Wednesday', 7, 0, '7:00');");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + ", " + Constants.KEY_DAY + ", " + Constants.KEY_HOUR + ", " + Constants.KEY_MINUTE
                + ", " + Constants.KEY_HOURMINURE + ") VALUES (3, 'Thursday', 7, 0, '7:00');");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + ", " + Constants.KEY_DAY + ", " + Constants.KEY_HOUR + ", " + Constants.KEY_MINUTE
                + ", " + Constants.KEY_HOURMINURE + ") VALUES (4, 'Friday', 7, 0, '7:00');");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + ", " + Constants.KEY_DAY + ", " + Constants.KEY_HOUR + ", " + Constants.KEY_MINUTE
                + ", " + Constants.KEY_HOURMINURE + ") VALUES (5, 'Saturday', 7, 0, '7:00');");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + ", " + Constants.KEY_DAY + ", " + Constants.KEY_HOUR + ", " + Constants.KEY_MINUTE
                + ", " + Constants.KEY_HOURMINURE + ") VALUES (6, 'Sunday', 7, 0, '7:00');");
        Log.v("Its in", "indeedy");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public void addAlarm(AlarmItem alarmItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_ID,alarmItem.getId());
        values.put(Constants.KEY_DAY, alarmItem.getAlarmDay());
        values.put(Constants.KEY_HOUR,alarmItem.getAlarmHour());
        values.put(Constants.KEY_MINUTE,alarmItem.getAlarmMinute());
        values.put(Constants.KEY_HOURMINURE, alarmItem.getAlarmHM());
        db.insert(Constants.TABLE_NAME, null, values);
    }

    public AlarmItem getAlarmItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
     //   Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID, Constants.KEY_DAY, Constants.KEY_HOUR, Constants.KEY_MINUTE, Constants.KEY_HOURMINURE},
       //         Constants.KEY_ID + "=?",
         //       new String[] {String.valueOf(id)}, null, null, null, null);


        Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID, Constants.KEY_DAY, Constants.KEY_HOUR, Constants.KEY_MINUTE, Constants.KEY_HOURMINURE},
                Constants.KEY_ID + " = ?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        AlarmItem alarmItem = new AlarmItem();
        alarmItem.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
        alarmItem.setAlarmDay(cursor.getString(cursor.getColumnIndex(Constants.KEY_DAY)));
        alarmItem.setAlarmHour(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_HOUR))));
        alarmItem.setAlarmMinute(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_MINUTE))));
        alarmItem.setAlarmHM(cursor.getString(cursor.getColumnIndex(Constants.KEY_HOURMINURE)));

        return alarmItem;
    }


    public int updateItem(AlarmItem alarmItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //    values.put(Constants.KEY_ID,alarmItem.getId());
        values.put(Constants.KEY_DAY, alarmItem.getAlarmDay());
        values.put(Constants.KEY_HOUR,alarmItem.getAlarmHour());
        values.put(Constants.KEY_MINUTE,alarmItem.getAlarmMinute());
        values.put(Constants.KEY_HOURMINURE, alarmItem.getAlarmHM());

        return db.update(Constants.TABLE_NAME, values, Constants.KEY_DAY + " = ?", new String[] {String.valueOf(alarmItem.getAlarmHour())});
    }

    public void deleteItem(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public List<AlarmItem> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<AlarmItem> alarmlist = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {
                Constants.KEY_DAY, Constants.KEY_HOUR, Constants.KEY_MINUTE,
                Constants.KEY_HOURMINURE}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                AlarmItem alarmItem = new AlarmItem();
                alarmItem.setAlarmHour(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_HOUR))));
                alarmItem.setAlarmMinute(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_MINUTE))));
                alarmItem.setAlarmDay(cursor.getString(cursor.getColumnIndex(Constants.KEY_DAY)));
                alarmItem.setAlarmHM(cursor.getString(cursor.getColumnIndex(Constants.KEY_HOURMINURE)));


                // Add to the groceryList
                alarmlist.add(alarmItem);

            }while (cursor.moveToNext());
        }

        return alarmlist;
    }
}
