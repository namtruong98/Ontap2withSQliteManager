package com.example.truong.ontap2withsqlitemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class StudentDAO {
    private DBManager dbManager;
    private SQLiteDatabase sqLiteDatabase;
    public StudentDAO(DBManager dbManager)
    {
        this.dbManager = dbManager;
    }

    private void openDB(){
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()){
            sqLiteDatabase = SQLiteDatabase.openDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null,SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void CloseDB()
    {
        if (sqLiteDatabase != null || sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }

    public ArrayList<Student> getAllStudent()
    {
        openDB();
        Cursor cursor = sqLiteDatabase.rawQuery(DBManager.SQL_QUERRY,null);
        if (cursor == null){
            return null;
        }

        int indexID = cursor.getColumnIndex("id");
        int indexName = cursor.getColumnIndex("name");
        int indexAge = cursor.getColumnIndex("age");

        String name;
        int id,age;
        cursor.moveToFirst();

        ArrayList<Student> list = new ArrayList<>();
        while (!cursor.isAfterLast()){
            id = cursor.getInt(indexID);
            name = cursor.getString(indexName);
            age = cursor.getInt(indexAge);

            list.add(new Student(id,name,age));
            cursor.moveToNext();
        }
        cursor.close();
        CloseDB();
        return list;
    }
    public long insert(Student student){
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",student.id);
        contentValues.put("name",student.name);
        contentValues.put("age",student.age);

        long result = sqLiteDatabase.insert(DBManager.TABLE_STUDENT,null, contentValues);
        return result;
    }
    public long update(Student student){
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",student.id);
        contentValues.put("name",student.name);
        contentValues.put("age",student.age);

        long result = sqLiteDatabase.update(DBManager.TABLE_STUDENT, contentValues, "id" + "=?",new String[]{String.valueOf(student.id)});
        return result;
    }
    public long delete(Student student){
        openDB();
        long result = sqLiteDatabase.delete(DBManager.TABLE_STUDENT, "id" + "=?", new String[]{String.valueOf(student.id)});
        return result;
    }
}
