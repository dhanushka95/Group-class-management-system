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

public class Student_add_fragment extends Fragment {

    private static DatabaseColumn MdatabaseColumn =null;
    private EditText GroupName,StudentName,StudentPhoneNumber;
    private Button btnStudentAdd;
    private SQLitedatabase sqLitedatabase,sqLitedatabaseInsert;

    public static Fragment getInstance(DatabaseColumn databaseColumn){

        Student_add_fragment student_add_fragment=new Student_add_fragment();
        MdatabaseColumn=databaseColumn;
        return student_add_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_add,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        GroupName=(EditText)view.findViewById(R.id.Group_Name_student_add);
        StudentName=(EditText)view.findViewById(R.id.studentName_student_add);
        StudentPhoneNumber=(EditText)view.findViewById(R.id.studentPhoneNumber_student_add);
        btnStudentAdd=(Button) view.findViewById(R.id.btnAddStudent_student_add);

        GroupName.setEnabled(false);
        GroupName.setText(MdatabaseColumn.getClass_name());

        btnStudentAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseColumn databaseColumn=new DatabaseColumn();
                sqLitedatabase=new SQLitedatabase(getContext());
                sqLitedatabaseInsert=new SQLitedatabase(getContext());

                int studentId=sqLitedatabase.GetMaxStudentId()+1;

                databaseColumn.setClass_Id(MdatabaseColumn.getClass_Id());
                databaseColumn.setStudent_name(StudentName.getText().toString());
                databaseColumn.setStudent_phone_no(StudentPhoneNumber.getText().toString());
                databaseColumn.setStudent_id(studentId+"");
            if(sqLitedatabaseInsert.InsertStudentData(databaseColumn)==true){

                Toast.makeText(getContext(),"save",Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }else {

                Toast.makeText(getContext(),"can't save",Toast.LENGTH_SHORT).show();
            }


            }
        });

    }
}
