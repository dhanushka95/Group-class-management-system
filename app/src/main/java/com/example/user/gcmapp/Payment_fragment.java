package com.example.user.gcmapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Payment_fragment extends Fragment {

    private Button change;
    private EditText getDate;
    private TextView class_name;
    private static DatabaseColumn MdatabaseColumn;
    private SQLitedatabase sqLitedatabase;

    public static Fragment getInstance(DatabaseColumn databaseColumn){

        Payment_fragment payment_fragment=new Payment_fragment();
        MdatabaseColumn=databaseColumn;
        return payment_fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        change=(Button)view.findViewById(R.id.change_Year_payment_fragment);
        getDate=(EditText)view.findViewById( R.id.Month_Year_payment_fragment);
        class_name=(TextView)view.findViewById( R.id.class_name_payment_fragment);

        class_name.setText(MdatabaseColumn.getClass_name());

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLitedatabase=new SQLitedatabase(getContext());

                DatabaseColumn databaseColumn=new DatabaseColumn();
                databaseColumn.setPayment_date(getDate.getText().toString());
                databaseColumn.setClass_Id(MdatabaseColumn.getClass_Id());
                sqLitedatabase.Updatepayment(databaseColumn);
                getActivity().onBackPressed();
            }
        });


    }
}
