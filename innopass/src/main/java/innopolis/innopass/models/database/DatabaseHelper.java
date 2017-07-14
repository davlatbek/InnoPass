package innopolis.innopass.models.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import innopolis.innopass.models.entities.User;

/**
 * Created by davlet on 07/6/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabaseManager{
    private static DatabaseHelper INSTANCE;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "innopassdb";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_QUERIES = "queries";
    public static final String TABLE_CARDS = "cards";
    public static final String TABLE_ADMINISTRATORS = "administrators";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_STUDENTS = "usersList";
    public static final String TABLE_EMPLOYEES = "employees";

    //Create table queries strings
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USERS +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "login VARCHAR(32) NOT NULL UNIQUE, " +
            "password VARCHAR(32) NOT NULL, " +
            "firstname VARCHAR(32) NOT NULL, " +
            "surname VARCHAR(32) NOT NULL, " +
            "middlename VARCHAR(32), " +
            "date_of_birth INTEGER, " +
            "photo_id INTEGER " +
            ");";
    private static final String CREATE_TABLE_QUERIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_QUERIES +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id VARCHAR(32) NOT NULL UNIQUE, " +
            //one to many relationship (one card - many queries)
            "cards_id INTEGER REFERENCES cards(id), " +
            "message VARCHAR(128) NOT NULL, " +
            "priority INTEGER, " +
            "permissions INTEGER, " +
            "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + " (id)" +
            ");";
    private static final String CREATE_TABLE_ADMINISTRATORS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ADMINISTRATORS +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "login VARCHAR(32) NOT NULL UNIQUE, " +
            "password VARCHAR(32) NOT NULL, " +
            "firstname VARCHAR(32) NOT NULL " +
            ");";
    private static final String CREATE_TABLE_CARDS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CARDS +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id VARCHAR(32) NOT NULL UNIQUE, " +
                    "is_valid INTEGER NOT NULL, " +
                    "is_blocked INTEGER NOT NULL, " +
                    "priority INTEGER, " +
                    "permission_university INTEGER, " +
                    "permission_campus1 INTEGER, " +
                    "permission_campus2 INTEGER, " +
                    "permission_campus3 INTEGER, " +
                    "permission_campus4 INTEGER, " +
                    "permission_technopark INTEGER, " +
                    "permission_canteenuniversity INTEGER, " +
                    "permission_canteencampus1 INTEGER, " +
                    "permission_canteencampus4 INTEGER, " +
                    "permission_canteentechopark INTEGER, " +
                    "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + " (id)" +
                    ");";

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
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_QUERIES);
        db.execSQL(CREATE_TABLE_ADMINISTRATORS);
        db.execSQL("INSERT INTO users " +
                "(login, password, firstname, surname) VALUES " +
                "('einstein', '', 'Albert', 'Einstein'" +
                ");");
        db.execSQL("INSERT INTO users " +
                "(login, password, firstname, surname) VALUES " +
                "('lillie', '1', 'lillie', 'clinton'" +
                ");");
        db.execSQL("INSERT INTO users " +
                "(login, password, firstname, surname) VALUES " +
                "('davlet', 'Davlet19#', 'Davlet', 'Isroilov'" +
                ");");
        db.execSQL("INSERT INTO users " +
                "(login, password, firstname, surname) VALUES " +
                "('login1', 'Password19#', 'name1', 'surname1'" +
                ");");
        db.execSQL("INSERT INTO users " +
                "(login, password, firstname, surname) VALUES " +
                "('admin', 'Admin19#', 'Administrator', ''" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion)
            onCreate(db);
    }

    @Override
    public User getUserByLogin(String login) {
        SQLiteDatabase db = getWritableDatabase();
        User user = null;
        String query = "SELECT * FROM users WHERE login = '" + login + "'";
        db.beginTransaction();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            user = new User();
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
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO users (login, password, firstname, surname, " +
                "middlename, date_of_birth, photo_id) VALUES (" +
                "?, ?, ?, ?, ?, ?, ?);";
        SQLiteStatement insertStatement = db.compileStatement(query);
        insertStatement.bindString(1, user.getLogin());
        insertStatement.bindString(2, user.getPassword());
        insertStatement.bindString(3, user.getFirstName());
        insertStatement.bindString(4, user.getSurname());
        insertStatement.bindString(5, user.getMiddleName());
        insertStatement.bindLong(6, user.getDateOfBirth().getTimeInMillis());
        insertStatement.bindLong(7, user.getPhotoId());
        insertStatement.execute();
        return true;
    }
}
