package com.example.truong.ontap2withsqlitemanager;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DBManager {
    public static final String DB_PATH = Environment.getDataDirectory() + "/data/com.example.truong.ontap2withsqlitemanager/database";
    public static final String DB_NAME = "student_class";
    public static final String TABLE_STUDENT = "student";
    public static final String SQL_QUERRY = "SELECT * FROM " + TABLE_STUDENT;

    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
        copyDatabase();
    }

    private void copyDatabase() {
        new File(DB_PATH).mkdirs();
        try {
            File file = new File(DB_PATH + "/" + DB_NAME);
            if (file.exists()){
                Toast.makeText(mContext, "File Exits", Toast.LENGTH_SHORT).show();
                return;
            }
            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int len;
            byte buff[] = new byte[1024];
            while ((len = inputStream.read(buff)) > 0){
                fileOutputStream.write(buff,0,len);
            }
            fileOutputStream.close();
            inputStream.close();
            Toast.makeText(mContext, "Copy data done !", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


}
