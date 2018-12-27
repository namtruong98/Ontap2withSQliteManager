package com.example.truong.ontap2withsqlitemanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder
{
    public TextView tvID,tvName,tvAge,tvGerder;
    public Button btnDelete;

    public ViewHolder(View itemView)
    {
        super(itemView);
        tvID = (TextView) itemView.findViewById(R.id.tv_id);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvAge = (TextView) itemView.findViewById(R.id.tv_age);
        btnDelete = itemView.findViewById(R.id.btnDelete);

    }
}