package innopolis.innopass.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import innopolis.innopass.models.Student;

/**
 * Created by davlet on 07/6/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabaseManager{
    private static DatabaseHelper INSTANCE;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "innopassdb";

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        context.deleteDatabase(DATABASE_NAME);
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
        db.execSQL("CREATE TABLE IF NOT EXISTS users" +
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
    public Student getStudentByLogin(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = null;
        String query = "SELECT * FROM users WHERE login = '" + login + "'";
        db.beginTransaction();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            student = new Student();
            do {
                student.setId(c.getLong(c.getColumnIndex("id")));
                student.setLogin(c.getString(c.getColumnIndex("login")));
                student.setPassword(c.getString(c.getColumnIndex("password")));
                student.setFirstName(c.getString(c.getColumnIndex("firstname")));
                student.setSurname(c.getString(c.getColumnIndex("surname")));
            } while (c.moveToNext());
            c.close();
        }
        db.endTransaction();
        return student;
    }

    @Override
    public boolean addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO users (login, password, firstname, surname, " +
                "middlename, date_of_birth, photo_id) VALUES (" +
                "?, ?, ?, ?, ?, ?, ?);";
        SQLiteStatement insertStatement = db.compileStatement(query);
        insertStatement.bindString(1, student.getLogin());
        insertStatement.bindString(2, student.getPassword());
        insertStatement.bindString(3, student.getFirstName());
        insertStatement.bindString(4, student.getSurname());
        insertStatement.bindString(5, student.getMiddleName());
        insertStatement.bindLong(6, student.getDateOfBirth().getTimeInMillis());
        insertStatement.bindLong(7, student.getPhotoId());
        insertStatement.execute();
        return true;
    }
}
