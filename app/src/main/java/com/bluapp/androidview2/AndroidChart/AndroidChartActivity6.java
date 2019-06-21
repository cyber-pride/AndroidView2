package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AndroidChartActivity6 extends AppCompatActivity {
    private CandleStickChart candleStickChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart6);
        candleStickChart = (CandleStickChart)findViewById(R.id.candleStickChart);
        CandleDataSet candleDataSet = new CandleDataSet(getData(), "Inducesmile");
        candleDataSet.setColor(Color.rgb(80, 80, 80));
        candleDataSet.setShadowColor(getResources().getColor(R.color.colorPrimaryDark));
        candleDataSet.setShadowWidth(0.8f);
        candleDataSet.setDecreasingColor(getResources().getColor(R.color.colorPrimary));
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setIncreasingColor(getResources().getColor(R.color.colorAccent));
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setNeutralColor(Color.LTGRAY);
        candleDataSet.setDrawValues(false);
        CandleData candleData = new CandleData(candleDataSet);
        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        candleStickChart.setData(candleData);
        candleStickChart.animateXY(5000, 5000);
        candleStickChart.invalidate();
    }

    private ArrayList getData(){
        ArrayList<CandleEntry> entries = new ArrayList<>();
        entries.add(new CandleEntry(0f, 225.0f, 219.84f, 224.94f, 221.07f));
        entries.add(new CandleEntry(1f, 228.35f, 222.57f, 223.52f, 226.41f));
        entries.add(new CandleEntry(2f, 226.84f, 222.52f, 225.75f, 223.07f));
        entries.add(new CandleEntry(3f, 222.95f, 217.27f, 222.15f, 217.88f));
        return entries;
    }
}
