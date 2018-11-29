package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentList_fragment extends Fragment {

    private ArrayList<DatabaseColumn> databaseColumnslistStudent;
    private StudentList_adapter studentList_adapter;
    private ListView listView;
    private static MainActivity MmainActivity;

    public static StudentList_fragment getnewinstance(MainActivity mainActivity){

        StudentList_fragment studentList_fragment=new StudentList_fragment();
        MmainActivity=mainActivity;

        return studentList_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_student,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        listView=view.findViewById(R.id.list_student_listview);
        databaseColumnslistStudent=new ArrayList<>();
        databaseColumnslistStudent.add(new DatabaseColumn(1,"dhanushka","","","123","herath","0555718897","1"));
        databaseColumnslistStudent.add(new DatabaseColumn(1,"dayawansha","","","12","rohitha","0555718897","2"));
        databaseColumnslistStudent.add(new DatabaseColumn(1,"kb","","","13","dilan","0555718897","3"));

        studentList_adapter=new StudentList_adapter(getContext(),databaseColumnslistStudent,MmainActivity);
        listView.setAdapter(studentList_adapter);

    }
}
