package com.example.user.gcmapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    CreateFolder();
                    sleep(2000);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

    void CreateFolder(){
        String myfolder1=Environment.getExternalStorageDirectory().getPath() + "/GcmAPP/";
        String myfolder2=Environment.getExternalStorageDirectory().getPath() + "/GcmAPP/Database";
        String myfolder3=Environment.getExternalStorageDirectory().getPath() + "/GcmAPP/Image/";
        File f1=new File(myfolder1);
        File f2=new File(myfolder2);
        File f3=new File(myfolder3);
        if(!f1.exists())
            {
              f1.mkdir();

            }
        if(!f2.exists())
        {
            f2.mkdir();

        }
        if(!f3.exists())
        {
            f3.mkdir();

        }
    }
}
