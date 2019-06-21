package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AndroidChartActivity8 extends AppCompatActivity {
    private RadarChart radarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart8);
        radarChart = (RadarChart) findViewById(R.id.radarChart);
        RadarDataSet radarDataSet = new RadarDataSet(getData(), "Inducesmile");
        radarDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        RadarData radarData = new RadarData(radarDataSet);
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        radarChart.setData(radarData);
        radarChart.animateXY(5000, 5000);
        radarChart.invalidate();
    }

    private ArrayList getData(){
        ArrayList<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(0f, 0.21f));
        entries.add(new RadarEntry(1f, 0.12f));
        entries.add(new RadarEntry(2f, 0.20f));
        entries.add(new RadarEntry(3f, 0.52f));
        entries.add(new RadarEntry(4f, 0.29f));
        entries.add(new RadarEntry(5f, 0.62f));
        return entries;
    }
}
