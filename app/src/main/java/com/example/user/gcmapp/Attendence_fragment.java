package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Attendence_fragment extends Fragment {

    private Attendence_list_adapter attendence_list_adapter;
    private ArrayList<DatabaseColumn> databaseColumnslist;
    private ListView listView;
    private static MainActivity MmainActivity;

    public static Attendence_fragment getnewinstance(MainActivity mainActivity){

        Attendence_fragment attendence_fragment=new Attendence_fragment();
        MmainActivity=mainActivity;

        return attendence_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attendence,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        listView=view.findViewById(R.id.list_Attendence_listview);

        databaseColumnslist=new ArrayList<>();
/*
        databaseColumnslist.add(new DatabaseColumn(1,"dhanushka","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(2,"dayawansha","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(3,"herath","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(4,"hm","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(5,"kb","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(6,"rm","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(7,"a","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(8,"b","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(9,"c","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(10,"k","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(11,"l","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(12,"m","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(13,"n","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(14,"o","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(15,"p","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(16,"q","monaragala","12","10"));
        databaseColumnslist.add(new DatabaseColumn(17,"r","okkampitiya","12345","10"));
        databaseColumnslist.add(new DatabaseColumn(18,"s","monaragala","12","10"));
        */

        attendence_list_adapter=new Attendence_list_adapter(getContext(),databaseColumnslist,MmainActivity);
        listView.setAdapter(attendence_list_adapter);

        Button ok=view.findViewById(R.id.btnAttendenceMarkOk);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
}
