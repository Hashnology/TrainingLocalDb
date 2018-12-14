package com.example.hashi.traininglocaldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.hashi.traininglocaldb.AppDataBase.DATABASE_NAME;

public class MyLocalDatBase {
    public Context context;
    public SQLiteDatabase sqLiteDatabase;
    private Db db;


    public static final String DATABSE_NAME = "users";
    public static final int DATABASE_VERSION = 1;

    /* defined table name*/

    public static final String KEY_TABLE_NAME = "users_table";
    /*definded column names*/
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    /*to drop table*/
    private static final String DROP_TABLE_USERS = "DROP TABLE IF EXISTS " + KEY_TABLE_NAME;

    /*to create database table*/

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + KEY_TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_NAME + " TEXT," +
                    KEY_EMAIL + " TEXT," +
                    KEY_PASSWORD + " TEXT)";


    /*to get context of main activity in this class*/
    public MyLocalDatBase(Context context) {
        this.context = context;

    }


    /*create a method to insert data into our local database*/

    public long insertUsers(String name, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_PASSWORD, password);

        long inserted_id = sqLiteDatabase.insert(KEY_TABLE_NAME, null, contentValues);

        return inserted_id;
    }


    /*to open database class in other app ,database does not allow to read or write any thing without opening it*/
    public MyLocalDatBase open() throws android.database.SQLException {
        /*Db class is actual class that is extended by open helper class*/
        db = new Db(context);
        /*to make db class writeable */
        sqLiteDatabase = db.getWritableDatabase();

        return MyLocalDatBase.this;
    }

    /*now creat a method to close data base*/
    public void close() {
        sqLiteDatabase.close();
    }


    public class Db extends SQLiteOpenHelper {

        public Db(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE_USERS);
        }
    }
}
