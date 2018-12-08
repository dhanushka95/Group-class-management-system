package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupList_fragment extends Fragment {



    private GroupList_adapter groupList_adapter;
    private ArrayList<DatabaseColumn> databaseColumnslist;
    private ListView listView;
    private static DatabaseColumn MdatabaseColumn;
    private static MainActivity MmainActivity;

    public static GroupList_fragment getnewinstance(MainActivity mainActivity,DatabaseColumn databaseColumn){

        GroupList_fragment groupList_fragment=new GroupList_fragment();
        MmainActivity=mainActivity;
        MdatabaseColumn=databaseColumn;
        return groupList_fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_group,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        listView=view.findViewById(R.id.list_group_listview);

        databaseColumnslist=new ArrayList<>();



        SQLitedatabase sqLitedatabase=new SQLitedatabase(getContext());
        if(MdatabaseColumn==null) {

            databaseColumnslist = sqLitedatabase.getGroupList();

        }else {
        try{
            ArrayList<DatabaseColumn>TempArry=new ArrayList<>();

            TempArry.add(sqLitedatabase.GetGroupData(MdatabaseColumn.getClass_Id()));
            databaseColumnslist=TempArry;
        }catch (Exception ex){

            Toast.makeText(getContext(),"QR is no valied",Toast.LENGTH_SHORT).show();

        }
        }
        groupList_adapter=new GroupList_adapter(getContext(),databaseColumnslist,MmainActivity);
        listView.setAdapter(groupList_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"Group name : "+view.getTag(),Toast.LENGTH_SHORT).show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
