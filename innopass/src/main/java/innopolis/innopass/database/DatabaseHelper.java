package innopolis.innopass.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import innopolis.innopass.models.Student;
import innopolis.innopass.models.User;

/**
 * Created by davlet on 07/6/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabaseManager{
    private static DatabaseHelper INSTANCE;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "innopassdb";

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        context.deleteDatabase(DATABASE_NAME);
    }

    public static synchronized DatabaseHelper getInstance(Context context){
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
                "login VARCHAR(32) NOT NULL UNIQUE, " +
                "password VARCHAR(32) NOT NULL, " +
                "firstname VARCHAR(32) NOT NULL, " +
                "surname VARCHAR(32) NOT NULL, " +
                "middlename VARCHAR(32), " +
                "date_of_birth INTEGER, " +
                "photo_id INTEGER " +
                ");");
        db.execSQL("INSERT INTO users " +
                "(login, password, firstname, surname) VALUES " +
                "('einstein', '', 'Albert', 'Einstein'" +
                ");");
        db.execSQL("INSERT INTO users " +
                "(login, password, firstname, surname) VALUES " +
                "('lillie', '1', 'lillie', 'clinton'" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion)
            onCreate(db);
    }

    @Override
    public User getUserByLogin(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new Student();
        String query = "SELECT * FROM users WHERE login = '" + login + "'";
        db.beginTransaction();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do {
                user.setId(c.getLong(c.getColumnIndex("id")));
                user.setLogin(c.getString(c.getColumnIndex("login")));
                user.setPassword(c.getString(c.getColumnIndex("password")));
                user.setFirstName(c.getString(c.getColumnIndex("firstname")));
                user.setSurname(c.getString(c.getColumnIndex("surname")));
            } while (c.moveToNext());
            c.close();
        }
        db.endTransaction();
        return user;
    }

    @Override
    public boolean addUser(User user) {
        //TODO ADD USER TO DB
        return true;
    }

    public Cursor getUserByLoginStatement(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[] {"login", "password", "firstname", "surname"},
                "login = ?", new String[] {login},
                null, null, null);
        return cursor;
    }

    @Override
    public String getPasswordByLogin(String password) {
        //TODO GET PASSWORD BY LOGIN
        return null;
    }
}
