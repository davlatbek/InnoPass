package innopolis.innopass.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by davlet on 07/6/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper INSTANCE;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "innopassdb";

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if (INSTANCE == null)
            INSTANCE = new DatabaseHelper(context.getApplicationContext());
        return INSTANCE;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables, set foreign keys
        db.execSQL("CREATE TABLE users" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname VARCHAR(32), surname VARCHAR(32), " +
                "middlename VARCHAR(32) " +
                ");");
        db.execSQL("INSERT INTO students (name, surname) VALUES (" +
                "'Albert', 'Einstein', 'Einstein'" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion)
            onCreate(db);
    }
}
