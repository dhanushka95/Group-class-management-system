package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Group_delete_fragment extends Fragment {

    private EditText GroupName,GroupLocation,GroupPhoneNumber;
    private SQLitedatabase sqLiteDatabase,DsqLitedatabase;
    static  Fragment deletefragment=null;
    static String inputValue;

    public static Fragment getInstance(String value){

        deletefragment=new Group_delete_fragment();
        inputValue=value;
        return deletefragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_delete,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        GroupName=(EditText)view.findViewById(R.id.group_Name_delete);
        GroupLocation=(EditText)view.findViewById(R.id.group_location_delete);
        GroupPhoneNumber=(EditText)view.findViewById(R.id.group_phoneNumber_delete);

        sqLiteDatabase=new SQLitedatabase(getContext());
        final DatabaseColumn databaseColumn=sqLiteDatabase.GetGroupData(inputValue);//get class information using class id

        /* make Edit text read only true*/
        GroupName.setEnabled(false);
        GroupLocation.setEnabled(false);
        GroupPhoneNumber.setEnabled(false);

        GroupName.setText(databaseColumn.getClass_name());
        GroupLocation.setText(databaseColumn.getClass_location());
        GroupPhoneNumber.setText(databaseColumn.getClass_phone_no());

        Button btnDeleteGroup=(Button)view.findViewById(R.id.btnGroupDelete);

        btnDeleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DsqLitedatabase=new SQLitedatabase(getContext());

                try {
                    DsqLitedatabase.DeleteGroup(inputValue);
                    Thread.sleep(1000);
                    Toast.makeText(getContext(),"deleted",Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
