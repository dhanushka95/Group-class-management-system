package com.example.user.gcmapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Student_marks_fragment extends Fragment {

    private static DatabaseColumn MdatabaseColumn;
    public static Student_marks_fragment getnewinstance(DatabaseColumn databaseColumn){

        Student_marks_fragment student_marks_fragment=new Student_marks_fragment();

        MdatabaseColumn=databaseColumn;

        return student_marks_fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_marks,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BarChart chart = (BarChart) view.findViewById(R.id.bar_chart);

        ArrayList<BarEntry> BarEntry = new ArrayList<>();
      /*  BarEntry.add(new BarEntry(1, 100));
        BarEntry.add(new BarEntry(2, 90));
        BarEntry.add(new BarEntry(3, 95));
        BarEntry.add(new BarEntry(4, 70));
        BarEntry.add(new BarEntry(5, 60));
        BarEntry.add(new BarEntry(6, 90));

        BarDataSet dataSet = new BarDataSet(BarEntry, "Projects");

        ArrayList<String> labels = new ArrayList<>();

        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");*/

      SQLitedatabase sqLitedatabase=new SQLitedatabase(getContext());

      BarEntry=sqLitedatabase.getMarkForChart(MdatabaseColumn);
      BarDataSet dataSet = new BarDataSet(BarEntry, "Test number");

      BarData data = new BarData(dataSet);
      dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
      chart.setData(data);

        chart.setDescription("Student Marks");
    }
}
