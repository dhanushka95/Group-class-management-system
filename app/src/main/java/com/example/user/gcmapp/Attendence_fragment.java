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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class Attendence_fragment extends Fragment {

    private Attendence_list_adapter attendence_list_adapter,attendence_list_adapterUpdate;
    private ArrayList<DatabaseColumn> databaseColumnslist;
    private ListView listView;
    private EditText txtNUmberOfClass;
    private TextView GroupName;
    private static DatabaseColumn MdatabaseColumn;
    private SQLitedatabase sqLitedatabase,InsertSqldatabase,getClassNumberOfDay,getIfhaveList;

    public static Attendence_fragment getnewinstance(DatabaseColumn databaseColumn){

        Attendence_fragment attendence_fragment=new Attendence_fragment();
        MdatabaseColumn=databaseColumn;

        return attendence_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attendence,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        listView=view.findViewById(R.id.list_Attendence_add_listview);
        txtNUmberOfClass=view.findViewById(R.id.numberofday_attendence_add);
        GroupName=view.findViewById(R.id.GroupName_attendence_add);
        Date date=new Date();


        getClassNumberOfDay=new SQLitedatabase(getContext());
        int maxClassNumber=getClassNumberOfDay.GetMaxClassNumber()+1;

        GroupName.setText(MdatabaseColumn.getClass_name());
        txtNUmberOfClass.setText(maxClassNumber+"");

        InsertSqldatabase=new SQLitedatabase(getContext());

        databaseColumnslist=new ArrayList<>();

        sqLitedatabase=new SQLitedatabase(getContext());
        databaseColumnslist = sqLitedatabase.getStudentList(MdatabaseColumn.getClass_Id());
        String dates=date.toString();
        for (DatabaseColumn databasecolumn:databaseColumnslist) {

            databasecolumn.setCurrent_date(dates);
            databasecolumn.setClass_no_days(txtNUmberOfClass.getText().toString());

        }


        attendence_list_adapter=new Attendence_list_adapter(getContext(),databaseColumnslist);
        listView.setAdapter(attendence_list_adapter);

        Button ok=view.findViewById(R.id.btnAttendenceMark_attendence_add);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InsertSqldatabase=new SQLitedatabase(getContext());


                if(InsertSqldatabase.InsertAttendence(databaseColumnslist)==true){
                 Toast.makeText(getContext(),"save",Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(getContext(),"can't save",Toast.LENGTH_SHORT).show();
                }




            }
        });

        txtNUmberOfClass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    ArrayList<DatabaseColumn>databaseColumnslistUpdate=new ArrayList<>();
                    getIfhaveList=new SQLitedatabase(getContext());

                    databaseColumnslistUpdate=getIfhaveList.getAttendenceList(MdatabaseColumn.getClass_Id(),txtNUmberOfClass.getText().toString());

                    attendence_list_adapterUpdate=new Attendence_list_adapter(getContext(),databaseColumnslistUpdate);
                    listView.setAdapter(attendence_list_adapterUpdate);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        super.onViewCreated(view, savedInstanceState);
    }
}
