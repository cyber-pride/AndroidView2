package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AndroidChartActivity2 extends AppCompatActivity {
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart2);
        pieChart = (PieChart)findViewById(R.id.pieChart);
        PieDataSet pieDataSet = new PieDataSet(getData(),"Inducesmile");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.animateXY(5000, 5000);
        pieChart.invalidate();
    }

    private ArrayList getData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(945f, "Ayo"));
        entries.add(new PieEntry(1030f, "Adekola"));
        entries.add(new PieEntry(1143f, "Henry"));
        entries.add(new PieEntry(1250f, "Mark"));
        return entries;
    }
}
