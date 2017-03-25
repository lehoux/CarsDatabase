package com.example.rent.carsdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RENT on 2017-03-25.
 */

public class MotoDbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "moto.db";
    private static int DATABASE_VERSION = 2;
    // podbijamy z jedynki na dwójkę, jak upgradujemy tabele w metodzie
    private static String SQL_CREATE_TABLE = "create table " + CarsTableContract.TABLE_NAME
            + " (" + CarsTableContract._ID + " integer primary key autoincrement, "
            + CarsTableContract.COLUMN_MAKE + " text, "
            + CarsTableContract.COLUMN_MODEL + " text, "
            + CarsTableContract.COLUMN_IMAGE + " text, "
            + CarsTableContract.COLUMN_YEAR + " int) ";

    private static String SQL_DROP_TABLE = "drop table if exists " + CarsTableContract.TABLE_NAME;

    public MotoDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    public boolean insertCar(Car car) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(CarsTableContract.COLUMN_MAKE, car.getMake());
        contentValues.put(CarsTableContract.COLUMN_MODEL, car.getMake());
        contentValues.put(CarsTableContract.COLUMN_YEAR, car.getMake());
        contentValues.put(CarsTableContract.COLUMN_IMAGE, car.getMake());

        long value = getWritableDatabase().insert(CarsTableContract.TABLE_NAME, null, contentValues);

        return value != -1;
    }

    public Cursor getAllItems() {
        Cursor cursor = getReadableDatabase().query(CarsTableContract.TABLE_NAME,
                new String[]{
                        CarsTableContract._ID,
                        CarsTableContract.COLUMN_MAKE,
                        CarsTableContract.COLUMN_MODEL,
                        CarsTableContract.COLUMN_IMAGE,
                        CarsTableContract.COLUMN_YEAR

                }, null, null, null, null, null);

        Log.d("result", "cursoreSize" + cursor.getCount());
        return cursor;
    }

    public Cursor searchQuery(CharSequence constraint) {
        Cursor cursor = getReadableDatabase().query(CarsTableContract.TABLE_NAME,
               null, CarsTableContract.COLUMN_MAKE + " like ?",

                new String[] {
                        constraint.toString() + "%"

                }, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(SQL_DROP_TABLE);
            onCreate(db);
        }
    }
}
