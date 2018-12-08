package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestMarks_fragment extends Fragment {

    private Test_marks_adapter test_marks_adapter;
    private ArrayList<DatabaseColumn> databaseColumnslist;
    private ListView listView;
    private static MainActivity MmainActivity;
    private static DatabaseColumn MdatabaseColumn;



    private SQLitedatabase sqLitedatabase,sqLitedatabaseGetStudentList,sqLitedatabaseInsert,getIfhaveList;

    private EditText test_no;

    Fragment fragment=null;
    public static TestMarks_fragment getnewinstance(MainActivity mainActivity,DatabaseColumn databaseColumn){

        TestMarks_fragment testMarks_fragment=new TestMarks_fragment();
        MmainActivity=mainActivity;
        MdatabaseColumn=databaseColumn;

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
        test_no=view.findViewById(R.id.test_no_edit_test);
        sqLitedatabase=new SQLitedatabase(getContext());
        int testNumber=sqLitedatabase.GetMaxTestId()+1;
        test_no.setText(testNumber+"");
        MdatabaseColumn.setTest_no(test_no.getText().toString());

        sqLitedatabaseGetStudentList=new SQLitedatabase(getContext());




        databaseColumnslist=new ArrayList<>();
        databaseColumnslist=sqLitedatabaseGetStudentList.getStudentList(MdatabaseColumn.getClass_Id());
        for (DatabaseColumn databsecolumn:
             databaseColumnslist) {
            databsecolumn.setTest_no(test_no.getText().toString());
        }

        test_marks_adapter=new Test_marks_adapter(getContext(),databaseColumnslist,MmainActivity);
        listView.setAdapter(test_marks_adapter);


        Button btnInsert=view.findViewById(R.id.btnMark_add_test_marks);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLitedatabaseInsert=new SQLitedatabase(getContext());
                if(sqLitedatabaseInsert.InsertMarks(databaseColumnslist)==true) {
                    MmainActivity.ShowFragment(13, null, MdatabaseColumn);
                }else {

                    Toast.makeText(getContext(),"can't save",Toast.LENGTH_SHORT).show();
                }

            }
        });

        test_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ArrayList<DatabaseColumn>databaseColumnsListTest=new ArrayList<>();
                getIfhaveList=new SQLitedatabase(getContext());

                databaseColumnsListTest=getIfhaveList.getTestList(MdatabaseColumn.getClass_Id(),test_no.getText().toString());

                test_marks_adapter=new Test_marks_adapter(getContext(),databaseColumnsListTest,MmainActivity);
                listView.setAdapter(test_marks_adapter);
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        super.onViewCreated(view, savedInstanceState);


    }
}
