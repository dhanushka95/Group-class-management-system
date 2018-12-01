package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TestMarks_fragment extends Fragment {

    private Test_marks_adapter test_marks_adapter;
    private ArrayList<DatabaseColumn> databaseColumnslist;
    private ListView listView;
    private static MainActivity MmainActivity;
    Fragment fragment=null;
    public static TestMarks_fragment getnewinstance(MainActivity mainActivity){

        TestMarks_fragment testMarks_fragment=new TestMarks_fragment();
        MmainActivity=mainActivity;

        return testMarks_fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_testmarks,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listView=view.findViewById(R.id.test_student_listview);

        databaseColumnslist=new ArrayList<>();
/*
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","herath","0555718897","1"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","rohitha","0555718897","2"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","dilan","0555718897","3"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","g","0555718897","4"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","h","0555718897","5"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","k","0555718897","6"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","l","0555718897","7"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","m","0555718897","8"));
        databaseColumnslist.add(new DatabaseColumn(1,"a","","","1","n","0555718897","9"));
*/
        test_marks_adapter=new Test_marks_adapter(getContext(),databaseColumnslist,MmainActivity);
        listView.setAdapter(test_marks_adapter);


        Button btnInsert=view.findViewById(R.id.btnMark_add);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MmainActivity.ShowFragment(13,null);

            }
        });

        super.onViewCreated(view, savedInstanceState);


    }
}
