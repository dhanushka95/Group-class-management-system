package com.example.user.gcmapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class check_attendence_adapter extends BaseAdapter {


    private ArrayList<DatabaseColumn> mDatabaseColumnList;
    private Context mContext;


    public check_attendence_adapter(Context context, ArrayList<DatabaseColumn> databaseColumnList) {

        this.mDatabaseColumnList=databaseColumnList;
        this.mContext=context;

    }

    @Override
    public int getCount() {
        return mDatabaseColumnList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(mContext,R.layout.layout_attendence_list,null);

        TextView txtClass_no=(TextView)v.findViewById(R.id.layout_attendence_class_no);
        TextView txtClass_date_time=(TextView)v.findViewById(R.id.layout_attendence_datetime);

        txtClass_no.setText(mDatabaseColumnList.get(position).getClass_no_days());
        txtClass_date_time.setText(mDatabaseColumnList.get(position).getCurrent_date());

        v.setTag(mDatabaseColumnList.get(position).getStudent_id());

        return v;
    }
}
