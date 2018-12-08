package com.example.user.gcmapp;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Wave;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Sendmsg_fragment extends Fragment {

    private static MainActivity MmainActivity;
    private static DatabaseColumn MdatabaseColumn;
    private SQLitedatabase sqLitedatabase;
    private ArrayList<DatabaseColumn> smsList;

    public static Sendmsg_fragment getnewinstance(MainActivity mainActivity,DatabaseColumn databaseColumn){

        Sendmsg_fragment sendmsgFragment=new Sendmsg_fragment();
        MmainActivity=mainActivity;
        MdatabaseColumn=databaseColumn;

        return sendmsgFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_msg,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.spin_kit);
        Button sendSms=(Button)view.findViewById(R.id.btnSendMsg);


        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sprite wave = new Wave();
                progressBar.setVisibility(View.VISIBLE);

                sqLitedatabase=new SQLitedatabase(getContext());
                smsList=sqLitedatabase.getTestList(MdatabaseColumn.getClass_Id(),MdatabaseColumn.getTest_no());

              if(sendSmsList(smsList)==true){
                   progressBar.setVisibility(View.GONE);

               }

            }
        });


       // progressBar.setIndeterminateDrawable(wave);

        super.onViewCreated(view, savedInstanceState);
    }


    private boolean sendSmsList(ArrayList<DatabaseColumn> databaseColumnArrayList){

        String sms="";
        String phoneNum="";

        for (DatabaseColumn databasecolumn:
             databaseColumnArrayList) {
        if(databasecolumn.getMarks()!=null) {
            sms = "student name : " + databasecolumn.getStudent_name() + " \n" +
                    "Test number : " + databasecolumn.getTest_no() + " \n" +
                    "Marks : " + databasecolumn.getMarks() + "";

            phoneNum = "" + databasecolumn.getStudent_phone_no() + "";


            if (!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                if (MmainActivity.checkPermission()) {

                    sendSMS(getContext(), 0, phoneNum, null, sms, null, null);

                } else {
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }


        }

        return true;
    }

    public static boolean sendSMS(Context ctx, int simID, String toNum, String centerNum, String smsText, PendingIntent sentIntent, PendingIntent deliveryIntent) {
        String name;

        try {
            if (simID == 0) {
                name = "isms";
                // for model : "Philips T939" name = "isms0"
            } else if (simID == 1) {
                name = "isms2";
            } else {
                throw new Exception("can not get service which for sim '" + simID + "', only 0,1 accepted as values");
            }
            Method method = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
            method.setAccessible(true);
            Object param = method.invoke(null, name);

            method = Class.forName("com.android.internal.telephony.ISms$Stub").getDeclaredMethod("asInterface", IBinder.class);
            method.setAccessible(true);
            Object stubObj = method.invoke(null, param);
            if (Build.VERSION.SDK_INT < 18) {
                method = stubObj.getClass().getMethod("sendText", String.class, String.class, String.class, PendingIntent.class, PendingIntent.class);
                method.invoke(stubObj, toNum, centerNum, smsText, sentIntent, deliveryIntent);
            } else {
                method = stubObj.getClass().getMethod("sendText", String.class, String.class, String.class, String.class, PendingIntent.class, PendingIntent.class);
                method.invoke(stubObj, ctx.getPackageName(), toNum, centerNum, smsText, sentIntent, deliveryIntent);
            }

            return true;
        } catch (ClassNotFoundException e) {
            Log.e("apipas", "ClassNotFoundException:" + e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.e("apipas", "NoSuchMethodException:" + e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e("apipas", "InvocationTargetException:" + e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e("apipas", "IllegalAccessException:" + e.getMessage());
        } catch (Exception e) {
            Log.e("apipas", "Exception:" + e.getMessage());
        }
        return false;
    }


}
