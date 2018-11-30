package com.example.user.gcmapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Group_add_fragment extends Fragment {

    private static MainActivity MmainActivity;

    public static Group_add_fragment getnewinstance(MainActivity mainActivity) {

        Group_add_fragment group_add_fragment=new Group_add_fragment();
        MmainActivity=mainActivity;

        return group_add_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_add,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnGroupAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"click group",Toast.LENGTH_SHORT).show();
                MmainActivity.ShowFragment(14,"1234");

            }
        });

    }
}
