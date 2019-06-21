package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AndroidChartActivity5 extends AppCompatActivity {
    private ScatterChart scatterChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart5);
        scatterChart = (ScatterChart)findViewById(R.id.scatterChart);
        ScatterDataSet scatterDataSet = new ScatterDataSet(getData(), "Inducesmile");
        scatterDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        ScatterData scatterData = new ScatterData(scatterDataSet);
        XAxis xAxis = scatterChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        scatterChart.setData(scatterData);
        scatterChart.animateXY(5000, 5000);
        scatterChart.invalidate();
    }


    private ArrayList getData(){
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 30f));
        entries.add(new Entry(1f, 80f));
        entries.add(new Entry(2f, 60f));
        entries.add(new Entry(3f, 50f));
        entries.add(new Entry(4f, 70f));
        entries.add(new Entry(5f, 60f));
        return entries;
    }

}
