package com.example.truong.ontap2withsqlitemanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddActivity extends AppCompatActivity {
    private DBManager dbManager;
    private StudentDAO studentDAO;
    private EditText edtID,edtName,edtAge,edtGerger;
    private Button btnAdd;
    private List<Student> studentList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DBManager(this);
        studentDAO = new StudentDAO(dbManager);
        edtID = findViewById(R.id.ed_id);
        edtName = findViewById(R.id.ed_name);
        edtAge = findViewById(R.id.ed_age);
        btnAdd = findViewById(R.id.btnThem);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.valueOf(edtID.getText().toString().trim());
                String vldID = String.valueOf(id);
                String name = edtName.getText().toString().trim();
                int age = Integer.valueOf(edtAge.getText().toString().trim());
                String vldAge = String.valueOf(age);
                if (vldID.isEmpty() || name.isEmpty() || vldAge.isEmpty()){
                    Toast.makeText(AddActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
                Student student = new Student(id,name,age);
                long result = studentDAO.insert(student);
                Toast.makeText(AddActivity.this, "Thêm thành công " +result, Toast.LENGTH_SHORT).show();
                setResult(999);
                finish();
            }
        });
    }
}
