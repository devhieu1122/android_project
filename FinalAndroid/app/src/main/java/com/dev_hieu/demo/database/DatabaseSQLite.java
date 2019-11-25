package com.dev_hieu.demo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dev_hieu.demo.model.Student;

public class DatabaseSQLite extends SQLiteOpenHelper {

    private static Context context;
    private static SQLiteDatabase sql;
    private static final String DB_NAME = "student.db";
    private static final String TABLE_STUDENT = "student";
    private final static int DB_VERSION = 1;

    static String code = "code", classID = "classID", name = "name", email = "email", gender = "gender", phone = "phone", birth = "birth", address = "address", note = "note";

    public DatabaseSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table " + TABLE_STUDENT + " ("
                + code + " int primary key,"
                + classID + " char(10), "
                + name + " text,"
                + email + " text,"
                + gender + " text,"
                + phone + " tex,t"
                + birth + " text,"
                + address + " text,"
                + note + " text)";

        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_STUDENT);

        onCreate(db);
    }

//    public void addStudent(Student student){
//        String sql = "insert int"
//    }

    public Student getStudent(int id) {
        sql = this.getReadableDatabase();
        String query = "select * from " + TABLE_STUDENT + " where code =?";
        Cursor cursor = sql.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        int code = Integer.parseInt(cursor.getString(0));
        String classID = cursor.getString(1);
        String name = cursor.getString(2);
        String email = cursor.getString(3);
        String gender = cursor.getString(4);
        String phone = cursor.getString(5);
        String birth = cursor.getString(6);
        String address = cursor.getString(7);
        String note = cursor.getString(8);
        Student student = new Student(code, classID, name, email, gender, phone, birth, address, note);
        return student;
    }
}
