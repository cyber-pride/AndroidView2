package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AndroidChartActivity7 extends AppCompatActivity {
    private BubbleChart bubbleChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart7);
        bubbleChart = (BubbleChart)findViewById(R.id.bubbleChart);
        BubbleDataSet bubbleDataSet = new BubbleDataSet(getData(), "Inducesmile");
        bubbleDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BubbleData scatterData = new BubbleData(bubbleDataSet);
        XAxis xAxis = bubbleChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        bubbleChart.setData(scatterData);
        bubbleChart.animateXY(5000, 5000);
        bubbleChart.invalidate();
    }

    private ArrayList getData(){
        ArrayList<BubbleEntry> entries = new ArrayList<>();
        entries.add(new BubbleEntry(0f, 1f, 0.21f));
        entries.add(new BubbleEntry(1f, 2f, 0.12f));
        entries.add(new BubbleEntry(2f, 3f, 0.20f));
        entries.add(new BubbleEntry(3f, 4f, 0.52f));
        entries.add(new BubbleEntry(4f, 5f, 0.29f));
        entries.add(new BubbleEntry(5f, 6f, 0.62f));
        return entries;
    }
}
