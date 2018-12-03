package com.example.user.gcmapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ShowFragment(9,"",null);


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }



    }
    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
       if (id == R.id.nav_add_group) {
        //fragment=new Group_add_fragment();
           ShowFragment(2,null,null);

        }
        else if(id==R.id.nav_Group_List) {

          // fragment=new Student_add_fragment();
           ShowFragment(7,null,null);

       }

        if(fragment!=null){

            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area,fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ShowFragment(int Type,String value ,DatabaseColumn databaseColumn){
        Fragment fragment=null;

        switch (Type) {
            case 1:
                fragment = Student_add_fragment.getInstance(databaseColumn);
                break;
            case 2:
                fragment = Group_add_fragment.getnewinstance(this);
                break;
            case 3:
                fragment =Student_update_fragment.getInstance(databaseColumn);
                break;
            case 4:
                fragment = Group_update_fragment.getInstance(value);
                break;
            case 5:
                fragment =Student_delete_fragment.getInstance(databaseColumn);
                break;
            case 6:
                fragment = Group_delete_fragment.getInstance(value);
                break;
            case 7:
                fragment=GroupList_fragment.getnewinstance(this);
                break;
            case 8:
                fragment=StudentList_fragment.getnewinstance(this,value);
                break;
            case 9:
                fragment=new ScanQR_fragment();
                break;
            case 10:
                fragment=new Student_marks_fragment();
                break;
            case 11:
                fragment=Attendence_fragment.getnewinstance(this);
                break;
            case 12:
                fragment=TestMarks_fragment.getnewinstance(this);
                break;
            case 13:
                fragment=Sendmsg_fragment.getnewinstance(this);
                break;
            case 14:
                fragment= QRcreate_fragment.getInstance(value);
                break;
            default:
                fragment=null;
                break;

        }
        if(fragment!=null){

            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {

            if(result.getContents()==null)
            {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),result.getContents(),Toast.LENGTH_LONG).show();

            }

        }
        else {

            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
