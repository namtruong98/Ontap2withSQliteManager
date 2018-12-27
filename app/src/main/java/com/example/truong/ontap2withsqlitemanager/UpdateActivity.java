package com.example.truong.ontap2withsqlitemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity
{
    private DBManager dbManager;
    private StudentDAO studentDAO;
    private EditText edtID,edtName,edtAge,edtGerger;
    private Button btnSua;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edtID = findViewById(R.id.ed_ud_id);
        edtName = findViewById(R.id.ed_ud_name);
        edtAge = findViewById(R.id.ed_ud_age);

        btnSua = findViewById(R.id.btnSua);

        dbManager = new DBManager(this);
        studentDAO = new StudentDAO(dbManager);

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.valueOf(edtID.getText().toString().trim());
                String vldID = String.valueOf(id);
                String name = edtName.getText().toString().trim();
                int age = Integer.valueOf(edtAge.getText().toString().trim());
                String vldAge = String.valueOf(age);
                if (vldID.isEmpty() || name.isEmpty() || vldAge.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
                Student student = new Student(id,name,age);
                long result = studentDAO.update(student);
                Toast.makeText(UpdateActivity.this, "Sửa Thành Công"+result, Toast.LENGTH_SHORT).show();
                setResult(999);
                finish();
            }
        });
    }
}
