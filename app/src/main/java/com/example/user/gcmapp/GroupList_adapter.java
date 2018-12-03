package com.example.user.gcmapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupList_adapter extends BaseAdapter {

    private ArrayList<DatabaseColumn> mDatabaseColumnList;
    private Context mContext;
    private MainActivity mainActivity;

    public GroupList_adapter(Context context, ArrayList<DatabaseColumn> databaseColumnList,MainActivity mainActivity) {

    this.mDatabaseColumnList=databaseColumnList;
    this.mContext=context;
    this.mainActivity=mainActivity;
    }

    @Override
    public int getCount() {
        return mDatabaseColumnList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatabaseColumnList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v= View.inflate(mContext,R.layout.list_group,null);

        TextView txtGname=(TextView)v.findViewById(R.id.group_name_list);
        TextView txtGlocation=(TextView)v.findViewById(R.id.group_location_list);
        TextView txtGphone=(TextView)v.findViewById(R.id.group_phoneNo_list);
        ImageView imgGlistitem=(ImageView)v.findViewById(R.id.list_group_image);

        txtGname.setText(mDatabaseColumnList.get(position).getClass_name());
        txtGlocation.setText(mDatabaseColumnList.get(position).getClass_location());
        txtGphone.setText(mDatabaseColumnList.get(position).getClass_phone_no());



        imgGlistitem.setImageResource(R.drawable.ic_more_vert_black);

        imgGlistitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,"click item "+mDatabaseColumnList.get(position).getPhone_no(),Toast.LENGTH_SHORT).show();

                PopupMenu popup = new PopupMenu(mContext, v);
                popup.getMenuInflater().inflate(R.menu.group_option_menu,
                        popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.Student_option_group:

                              mainActivity.ShowFragment(8,mDatabaseColumnList.get(position).getClass_Id(),null);
                               // Toast.makeText(mContext, "Add to Wish List Clicked at position " + " : " + mDatabaseColumnList.get(position).getName(), Toast.LENGTH_LONG).show();

                                break;
                            case R.id.Attemdence_option_group:

                                mainActivity.ShowFragment(11,null,null);
                                // Toast.makeText(mContext, "Add to Wish List Clicked at position " + " : " + mDatabaseColumnList.get(position).getName(), Toast.LENGTH_LONG).show();

                                break;
                            case R.id.Update_option_group:
                                mainActivity.ShowFragment(4,mDatabaseColumnList.get(position).getClass_Id(),null);
                               // Toast.makeText(mContext, "Add to Wish List Clicked at position " + " : " + mDatabaseColumnList.get(position).getName(), Toast.LENGTH_LONG).show();

                                break;

                            case R.id.Delete_option_group:
                                mainActivity.ShowFragment(6,mDatabaseColumnList.get(position).getClass_Id(),null);
                               // Toast.makeText(mContext, "Add to Wish List Clicked at position " + " : " + mDatabaseColumnList.get(position).getName(), Toast.LENGTH_LONG).show();

                                break;
                            case R.id.Student_add_option_group:

                                mainActivity.ShowFragment(1,null,mDatabaseColumnList.get(position));
                                // Toast.makeText(mContext, "Add to Wish List Clicked at position " + " : " + mDatabaseColumnList.get(position).getName(), Toast.LENGTH_LONG).show();

                                break;
                            case R.id.Test_marks_option_group:
                                mainActivity.ShowFragment(12,null,null);
                                // Toast.makeText(mContext, "Add to Wish List Clicked at position " + " : " + mDatabaseColumnList.get(position).getName(), Toast.LENGTH_LONG).show();

                                break;
                            case R.id.QR_option_group:
                                mainActivity.ShowFragment(14,mDatabaseColumnList.get(position).getClass_Id(),null);
                               // Toast.makeText(mContext, "Add to Wish List Clicked at position " + " : " + mDatabaseColumnList.get(position).getGroupId(), Toast.LENGTH_LONG).show();

                                break;

                            default:
                                break;
                        }


                        return false;
                    }
                });



            }
        });

        v.setTag(mDatabaseColumnList.get(position).getClass_Id());
        return v;
    }
}
