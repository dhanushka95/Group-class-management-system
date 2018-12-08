package com.example.user.gcmapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.CompoundButtonCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Attendence_list_adapter extends BaseAdapter {

    private ArrayList<DatabaseColumn> mDatabaseColumnList;
    private Context mContext;


    public Attendence_list_adapter(Context context, ArrayList<DatabaseColumn> databaseColumnList) {

        this.mDatabaseColumnList=databaseColumnList;
        this.mContext=context;

    }

    @Override
    public int getCount() {
        return mDatabaseColumnList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatabaseColumnList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View v= View.inflate(mContext,R.layout.list_attendence,null);

        CheckBox checkBox=v.findViewById(R.id.list_attendence_checkbox);

        checkBox.setText(mDatabaseColumnList.get(position).getStudent_name());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int getPosition = (Integer) buttonView.getTag();
                mDatabaseColumnList.get(getPosition).setSelected(buttonView.isChecked());


            }
        });

        checkBox.setTag(position);
        checkBox.setChecked(mDatabaseColumnList.get(position).isSelected());

        v.setTag(mDatabaseColumnList.get(position).getId());
        return v;
    }

}
