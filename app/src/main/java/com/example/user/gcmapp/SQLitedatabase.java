package com.example.user.gcmapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SQLitedatabase extends SQLiteOpenHelper {

    private Context mcontex;

    public static final String DATABASE_NAME="DBgcmApp.db";
    public static final int DATABASE_VERSION=1;

    private String DATABASE_LOCATION="";
    private String DATABASE_FULL_PATH="";
    public SQLiteDatabase mDB;

public SQLitedatabase( Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);

    }


    public SQLitedatabase(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        mcontex=context;

        DATABASE_LOCATION="/data/data/"+mcontex.getPackageName()+"/databases/";
        DATABASE_FULL_PATH=DATABASE_LOCATION+DATABASE_NAME;

        if(!isExistingDB()){

            try {
                File creatFolderLocation=new File(DATABASE_LOCATION);
                creatFolderLocation.mkdirs();
                extractAssetToDatabasesDirectory(DATABASE_NAME);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        mDB=SQLiteDatabase.openOrCreateDatabase(DATABASE_FULL_PATH,null);


    }

    boolean isExistingDB(){

        File file=new File(DATABASE_FULL_PATH);

        return file.exists();
    }
    public void extractAssetToDatabasesDirectory(String fileName)throws IOException{


        int length;
        InputStream sourceDatabase=this.mcontex.getAssets().open(fileName);
        File destinationPath=new File(DATABASE_FULL_PATH);
        OutputStream destination=new FileOutputStream(destinationPath);

        byte[] buffer=new byte[4096];
        while ((length=sourceDatabase.read(buffer))>0){

            destination.write(buffer,0,length);

        }
        sourceDatabase.close();
        destination.flush();
        destination.close();


    }


    public ArrayList<DatabaseColumn> getGroupList() {


        ArrayList<DatabaseColumn>databaseColumnList=new ArrayList<>();
        String q = "SELECT * FROM class";

        Cursor result = mDB.rawQuery(q,null);



        while (result.moveToNext()) {

            DatabaseColumn databaseColumn = new DatabaseColumn();

            databaseColumn.setClass_Id(result.getString(result.getColumnIndex("class_id")));
            databaseColumn.setClass_name(result.getString(result.getColumnIndex("class_name")));
            databaseColumn.setClass_location(result.getString(result.getColumnIndex("class_location")));
            databaseColumn.setClass_phone_no(result.getString(result.getColumnIndex("class_phone_number")));



            databaseColumnList.add(databaseColumn);
        }
        return databaseColumnList;
    }

    public void InsertGroupData(DatabaseColumn databaseColumn){

        String q = "INSERT INTO `class` VALUES('"+databaseColumn.getClass_Id()+"','"+databaseColumn.getClass_name()+"','"+databaseColumn.getClass_location()+"','"+databaseColumn.getClass_phone_no()+"')";
        try {
             mDB.execSQL(q);
            }catch (android.database.SQLException ex){

             Toast.makeText(mcontex,"Can't save",Toast.LENGTH_SHORT).show();
            }

    }

    public void UpdateGroup(DatabaseColumn databaseColumn){

        String q = "UPDATE class SET class_name='"+databaseColumn.getClass_name()+"',class_location='"+databaseColumn.getClass_location()+"',class_phone_number='"+databaseColumn.getClass_phone_no()+"' WHERE class_id='"+databaseColumn.getClass_Id()+"'";
        try {
            mDB.execSQL(q);
            Toast.makeText(mcontex,"complete",Toast.LENGTH_SHORT).show();
        }catch (android.database.SQLException ex){

            Toast.makeText(mcontex,"Can't update",Toast.LENGTH_SHORT).show();
        }
    }
public void DeleteGroup(String group_id){

    String quary="DELETE FROM class WHERE class_id='"+group_id+"'";
    try {
        mDB.execSQL(quary);

    }catch (android.database.SQLException ex){

        Toast.makeText(mcontex,"Can't delete",Toast.LENGTH_SHORT).show();

    }

}

    public DatabaseColumn  GetGroupData(String id){

        DatabaseColumn databaseColumnRow=new DatabaseColumn();
        String q = "SELECT * FROM CLASS WHERE class_id='"+id+"' ";

        Cursor result = mDB.rawQuery(q,null);

            result.moveToNext();
            databaseColumnRow.setClass_Id(result.getString(result.getColumnIndex("class_id")));
            databaseColumnRow.setClass_name(result.getString(result.getColumnIndex("class_name")));
            databaseColumnRow.setClass_location(result.getString(result.getColumnIndex("class_location")));
            databaseColumnRow.setClass_phone_no(result.getString(result.getColumnIndex("class_phone_number")));

        return databaseColumnRow;

    }


    public int GetMaxGroupId(){
        int count=0;
        mDB.isOpen();
        String q = "SELECT MAX(class_id) as max_group_id FROM class";
        Cursor result = mDB.rawQuery(q,null);
        result.moveToNext();
        count=Integer.parseInt(result.getString(0)+"");

        if(result.getCount()!=0) {
            mDB.close();
            return count;
        }else{
            mDB.close();
            return 0;
        }
    }
public int GetMaxStudentId(){
    int count=0;
    mDB.isOpen();
    String q = "SELECT MAX(student_id) as max_student_id FROM student";
    Cursor result = mDB.rawQuery(q,null);
    result.moveToNext();
    count=Integer.parseInt(result.getString(0)+"");

    if(result.getCount()!=0) {
        mDB.close();
        return count;
    }else{
        mDB.close();
        return 0;
    }

}

    public boolean InsertStudentData(DatabaseColumn databaseColumn){

        String q = "INSERT INTO `student` VALUES('"+databaseColumn.getClass_Id()+"','"+databaseColumn.getStudent_id()+"','"+databaseColumn.getStudent_name()+"','"+databaseColumn.getStudent_phone_no()+"')";
        try {
            mDB.execSQL(q);
            return  true;
        }catch (android.database.SQLException ex){

            Toast.makeText(mcontex,"Can't save",Toast.LENGTH_SHORT).show();
            return  false;
        }

    }
    public ArrayList<DatabaseColumn> getStudentList(String class_id) {


        ArrayList<DatabaseColumn>databaseColumnList=new ArrayList<>();
        String q = "SELECT class.class_name,student.class_id,student_id,student_name,student_phone_number FROM student , class where student.class_id='"+class_id+"' AND student.class_id=class.class_id";

        Cursor result = mDB.rawQuery(q,null);


        while (result.moveToNext()) {

            DatabaseColumn databaseColumn = new DatabaseColumn();

            databaseColumn.setClass_name(result.getString(result.getColumnIndex("class_name")));
            databaseColumn.setClass_Id(result.getString(result.getColumnIndex("class_id")));
            databaseColumn.setStudent_id(result.getString(result.getColumnIndex("student_id")));
            databaseColumn.setStudent_name(result.getString(result.getColumnIndex("student_name")));
            databaseColumn.setStudent_phone_no(result.getString(result.getColumnIndex("student_phone_number")));


            databaseColumnList.add(databaseColumn);
        }
        return databaseColumnList;
    }

    public void updateStudent(DatabaseColumn databaseColumn){


        String q = "UPDATE student SET student_name='"+databaseColumn.getStudent_name()+"',student_phone_number='"+databaseColumn.getStudent_phone_no()+"' WHERE class_id='"+databaseColumn.getClass_Id()+"' AND student_id='"+databaseColumn.getStudent_id()+"'";
        try {
            mDB.execSQL(q);
            Toast.makeText(mcontex,"complete",Toast.LENGTH_SHORT).show();
        }catch (android.database.SQLException ex){

            Toast.makeText(mcontex,"Can't update",Toast.LENGTH_SHORT).show();
        }


    }
    public void DeleteStudent(DatabaseColumn databaseColumn){

        String quary="DELETE FROM student WHERE class_id='"+databaseColumn.getClass_Id()+"' AND student_id='"+databaseColumn.getStudent_id()+"'";
        try {
            mDB.execSQL(quary);

        }catch (android.database.SQLException ex){

            Toast.makeText(mcontex,"Can't delete",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
