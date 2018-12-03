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

public class Student_update_fragment extends Fragment {
   static Fragment StudentUpdate;
   static DatabaseColumn MdatabaseColumn;
   private EditText GroupName,StudentName,StudentPhoneNo;
   private Button btnUpdateStudent;
   private SQLitedatabase sqLiteDatabase,UsqLitedatabase;

    public static Fragment getInstance(DatabaseColumn databaseColumn){

        StudentUpdate=new Student_update_fragment();
        MdatabaseColumn=databaseColumn;
        return StudentUpdate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_update,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GroupName=(EditText)view.findViewById(R.id.GroupName_student_update);
        StudentName=(EditText)view.findViewById(R.id.StudentNameUpdate_student_update);
        StudentPhoneNo=(EditText)view.findViewById(R.id.phoneNumberStudent_student_update);
        btnUpdateStudent=(Button)view.findViewById(R.id.btnStudentUpdate_student_update);

        GroupName.setEnabled(false);
        GroupName.setText(MdatabaseColumn.getClass_name());
        StudentName.setText(MdatabaseColumn.getStudent_name());
        StudentPhoneNo.setText(MdatabaseColumn.getStudent_phone_no());

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sqLiteDatabase=new SQLitedatabase(getContext());


                MdatabaseColumn.setStudent_name(StudentName.getText().toString());
                MdatabaseColumn.setStudent_phone_no(StudentPhoneNo.getText().toString());

                sqLiteDatabase.updateStudent(MdatabaseColumn);

                getActivity().onBackPressed();

            }
        });


    }
}
