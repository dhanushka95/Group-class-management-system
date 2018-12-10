package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Student_attendence_check extends Fragment {

    private ListView attendensList;
    private TextView studentName;
    private check_attendence_adapter check_attendence_adapter;
    private ArrayList<DatabaseColumn> databaseColumnslist;
    private static DatabaseColumn MdatabaseColumn;

    public static Fragment getInstance(DatabaseColumn databaseColumn){

        Student_attendence_check student_attendence_check=new Student_attendence_check();
        MdatabaseColumn=databaseColumn;
        return student_attendence_check;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_check_attendence,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        attendensList=(ListView)view.findViewById(R.id.check_attendence_student_list);
        studentName=(TextView) view.findViewById(R.id.check_attendence_student_fragment_name);

        studentName.setText(MdatabaseColumn.getStudent_name());
        databaseColumnslist=new ArrayList<>();

        SQLitedatabase sqLitedatabase=new SQLitedatabase(getContext());
        databaseColumnslist = sqLitedatabase.getAttendenceCheckList(MdatabaseColumn.getClass_Id(),MdatabaseColumn.getStudent_id());

        check_attendence_adapter=new check_attendence_adapter(getContext(),databaseColumnslist);
        attendensList.setAdapter(check_attendence_adapter);


    }
}
