package com.example.truong.ontap2withsqlitemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private Context mContext;
    private List<Student> studentList;

    public StudentAdapter(Context mContext, List<Student> studentList)
    {
        this.mContext = mContext;
        this.studentList = studentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)

    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Student student = studentList.get(position);
        holder.tvID.setText(String.valueOf(student.id));
        holder.tvName.setText(student.name);
        holder.tvAge.setText(String.valueOf(student.age));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(mContext);
                StudentDAO studentDAO = new StudentDAO(dbManager);
                studentDAO.delete(student);
                studentList.remove(position);
                Toast.makeText(mContext, "Xóa bay", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return studentList.size();
    }


}
