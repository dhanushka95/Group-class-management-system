package com.example.user.gcmapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Test_marks_adapter extends BaseAdapter {

    private ArrayList<DatabaseColumn> mDatabaseColumnList;
    private Context mContext;
    private MainActivity mainActivity;

    public Test_marks_adapter(Context context, ArrayList<DatabaseColumn> databaseColumnList,MainActivity mainActivity) {

        this.mDatabaseColumnList=databaseColumnList;
        this.mContext=context;
        this.mainActivity=mainActivity;
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

        View v=View.inflate(mContext,R.layout.list_test,null);

        TextView txtName=(TextView)v.findViewById(R.id.list_test_name);
        EditText editText_marks=(EditText)v.findViewById(R.id.list_test_marks);

        txtName.setText(mDatabaseColumnList.get(position).getStudent_name());

        editText_marks.addTextChangedListener(new addListenerOnTextChange(mDatabaseColumnList.get(position)));

        editText_marks.setText(mDatabaseColumnList.get(position).getMarks());
        mDatabaseColumnList.get(position).setMarks(editText_marks.getText().toString());

        v.setTag(mDatabaseColumnList.get(position).getStudent_id());

        return v;
    }
}
