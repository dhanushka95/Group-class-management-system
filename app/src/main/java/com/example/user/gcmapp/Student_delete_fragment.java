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

public class Student_delete_fragment extends Fragment {

    static Fragment StudentDelete;
    static DatabaseColumn MdatabaseColumn;
    private EditText GroupName,StudentName,StudentPhoneNo;
    private Button btnDeleteStudent;
    private SQLitedatabase sqLiteDatabase;

    public static Fragment getInstance(DatabaseColumn databaseColumn){

        StudentDelete=new Student_delete_fragment();
        MdatabaseColumn=databaseColumn;
        return StudentDelete;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_delete,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GroupName=(EditText)view.findViewById(R.id.GroupName_student_delete);
        StudentName=(EditText)view.findViewById(R.id.studentName_student_delete);
        StudentPhoneNo=(EditText)view.findViewById(R.id.studentPhoneNumber_student_delete);
        btnDeleteStudent=(Button)view.findViewById(R.id.btnDeleteStudent_student_delete);

        GroupName.setText(MdatabaseColumn.getClass_name());
        StudentName.setText(MdatabaseColumn.getStudent_name());
        StudentPhoneNo.setText(MdatabaseColumn.getStudent_phone_no());

        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase=new SQLitedatabase(getContext());
                sqLiteDatabase.DeleteStudent(MdatabaseColumn);
                Toast.makeText(getContext(),"complete",Toast.LENGTH_SHORT).show();

                getActivity().onBackPressed();
            }
        });
    }
}
