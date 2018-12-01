package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Group_add_fragment extends Fragment {

    private static MainActivity MmainActivity;
    private EditText GroupName,GroupLocation,GroupPhoneNumber;
    SQLitedatabase sqLitedatabase,sqLitedatabaseGid;
    public static Group_add_fragment getnewinstance(MainActivity mainActivity) {

        Group_add_fragment group_add_fragment=new Group_add_fragment();
        MmainActivity=mainActivity;

        return group_add_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_add,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GroupName=(EditText)view.findViewById(R.id.group_name_add);
        GroupLocation=(EditText)view.findViewById(R.id.group_location_add);
        GroupPhoneNumber=(EditText)view.findViewById(R.id.group_phoneNumber_add);

        sqLitedatabase=new SQLitedatabase(getContext());
        sqLitedatabaseGid=new SQLitedatabase(getContext());
        view.findViewById(R.id.btnGroupAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"click group",Toast.LENGTH_SHORT).show();
                DatabaseColumn databaseColumn=new DatabaseColumn();

                    int gId=sqLitedatabaseGid.GetRowCountGroup()+1;//use for group id

                    databaseColumn.setClass_Id(gId+"");
                    databaseColumn.setClass_name(GroupName.getText().toString());
                    databaseColumn.setClass_location(GroupLocation.getText().toString());
                    databaseColumn.setClass_phone_no(GroupPhoneNumber.getText().toString());

                sqLitedatabase.InsertGroupData(databaseColumn);

                MmainActivity.ShowFragment(14,databaseColumn.getClass_Id().toString());

            }
        });

    }
}
