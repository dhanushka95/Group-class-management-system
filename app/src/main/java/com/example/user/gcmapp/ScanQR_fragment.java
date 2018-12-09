package com.example.user.gcmapp;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQR_fragment extends Fragment {

    public  static MainActivity MmainActivity;
    public TextView resultQR;
    public static ScanQR_fragment getnewinstance(MainActivity mainActivity){

        ScanQR_fragment scanQR_fragment=new ScanQR_fragment();
        MmainActivity=mainActivity;

        return scanQR_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scanqr,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button ScanQR=(Button)view.findViewById(R.id.btnScanQr);
        final ImageView scanImage=(ImageView)view.findViewById(R.id.imageBarcodevalue_scan);
        resultQR=(TextView)view.findViewById(R.id.Barcodevalue_scan_qr);
        final Button Goto=(Button)view.findViewById(R.id.nextFragment_scan_qr);
        MmainActivity.setScnFragment(this);
        Goto.setEnabled(false);
        ScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator=new IntentIntegrator(MmainActivity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scann");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();




            }

        });


        resultQR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Goto.setEnabled(true);
                scanImage.setImageResource(R.drawable.finishimage);


            }
        });
        Goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DatabaseColumn TempdatabaseColumn = new DatabaseColumn();
                    TempdatabaseColumn.setClass_Id(resultQR.getText().toString());
                    MmainActivity.ShowFragment(7, null, TempdatabaseColumn);
                    scanImage.setImageResource(R.drawable.qrimage);
                }catch (Exception ex){

                    Toast.makeText(getContext(),"QR is no valied",Toast.LENGTH_SHORT).show();

                }
            }
        });




    }





}
