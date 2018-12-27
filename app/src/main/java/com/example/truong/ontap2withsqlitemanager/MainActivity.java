package com.example.truong.ontap2withsqlitemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private StudentDAO studentDAO;
    private ArrayList<Student> studentArrayList;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Button btnAdd,btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);

        dbManager = new DBManager(this);
        studentDAO = new StudentDAO(dbManager);
        studentArrayList = studentDAO.getAllStudent();
        studentAdapter = new StudentAdapter(this,studentArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent,999);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                startActivityForResult(intent,999);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999){
            dbManager = new DBManager(this);
            studentDAO = new StudentDAO(dbManager);
            studentArrayList = studentDAO.getAllStudent();
            studentAdapter = new StudentAdapter(this,studentArrayList);
            linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setAdapter(studentAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }
}
