package com.example.user.gcmapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

import static android.content.Context.WINDOW_SERVICE;
import static android.support.constraint.Constraints.TAG;

public class QRcreate_fragment extends Fragment {




    String TAG = "GenerateQRCode";
    // edtValue;
    ImageView qrImage;
    Button Shair;
   static String inputValue;
    static String saveName;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/GcmAPPQRCodeImage/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    static Date currentTime = Calendar.getInstance().getTime();


    static  Fragment qr=null;
    public static Fragment getInstance(String value){

        qr=new QRcreate_fragment();
        inputValue=value;
        saveName=currentTime+"_"+inputValue;
        return qr;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qr_create,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        qrImage = (ImageView)view.findViewById(R.id.QRimage);

        Shair = (Button)view.findViewById(R.id.btnQRshair);


        if (inputValue.length() > 0) {
            WindowManager manager = (WindowManager)getActivity().getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qrImage.setImageBitmap(bitmap);
                Toast.makeText(getContext(),inputValue,Toast.LENGTH_SHORT).show();
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }
        }

        /*image auto save*/

        boolean save;
        String result;
        try {
            save = QRGSaver.save(savePath, saveName, bitmap, QRGContents.ImageType.IMAGE_JPEG);
            result = save ? "Image Saved" : "Image Not Saved";
            if(save==false) {
                Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        Shair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
try {
    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("image/*");
    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(savePath + "/" + saveName + ".jpg")));
    startActivity(Intent.createChooser(share, "Share QR via"));
}catch (Exception e){

    Toast.makeText(getContext(),"can't share",Toast.LENGTH_SHORT).show();
}
            }
        });












        super.onViewCreated(view, savedInstanceState);
    }
}
