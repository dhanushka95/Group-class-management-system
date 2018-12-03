package com.example.user.gcmapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Group_update_fragment extends Fragment {

    private EditText GroupName,GroupLocation,GroupPhoneNumber;
    private SQLitedatabase sqLiteDatabase,UsqLitedatabase;
    static  Fragment updatefragment=null;
    static String inputValue;
    public static Fragment getInstance(String value){

        updatefragment=new Group_update_fragment();
        inputValue=value;
        return updatefragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_update,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GroupName=(EditText)view.findViewById(R.id.groupName_update);
        GroupLocation=(EditText)view.findViewById(R.id.grouplocation_update);
        GroupPhoneNumber=(EditText)view.findViewById(R.id.groupPhoneNumber_update);

        sqLiteDatabase=new SQLitedatabase(getContext());
        final DatabaseColumn databaseColumn=sqLiteDatabase.GetGroupData(inputValue);//get class information using class id

        GroupName.setText(databaseColumn.getClass_name());
        GroupLocation.setText(databaseColumn.getClass_location());
        GroupPhoneNumber.setText(databaseColumn.getClass_phone_no());



        view.findViewById(R.id.btnGroupUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsqLitedatabase=new SQLitedatabase(getContext());

                DatabaseColumn MdatabaseColumn=new DatabaseColumn();

                MdatabaseColumn.setClass_name(GroupName.getText().toString());
                MdatabaseColumn.setClass_location(GroupLocation.getText().toString());
                MdatabaseColumn.setClass_phone_no(GroupPhoneNumber.getText().toString());
                MdatabaseColumn.setClass_Id(inputValue);

                try {
                    UsqLitedatabase.UpdateGroup(MdatabaseColumn);
                    Thread.sleep(1000);
                    Toast.makeText(getContext(),"complete",Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

    }

}
