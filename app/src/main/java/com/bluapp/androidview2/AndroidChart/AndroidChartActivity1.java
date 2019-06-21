package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bluapp.androidview2.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class AndroidChartActivity1 extends AppCompatActivity {
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart1);
        lineChart = (LineChart)findViewById(R.id.lineChart);
        LineDataSet lineDataSet = new LineDataSet(getData(), "Inducesmile");
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr"};
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return months[(int) value];
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.invalidate();

    }


    private ArrayList getData(){
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 4f));
        entries.add(new Entry(1f, 1f));
        entries.add(new Entry(2f, 2f));
        entries.add(new Entry(3f, 4f));
        return entries;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();inflater.inflate(R.menu.androidchart_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_androidchartactivity2:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity2.class));
                return true;

            case R.id.action_androidchartactivity3:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity3.class));
                return true;

            case R.id.action_androidchartactivity4:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity4.class));
                return true;

            case R.id.action_androidchartactivity5:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity5.class));
                return true;

            case R.id.action_androidchartactivity6:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity6.class));
                return true;

            case R.id.action_androidchartactivity7:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity7.class));
                return true;

            case R.id.action_androidchartactivity8:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity8.class));
                return true;

            case R.id.action_androidchartactivity9:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity9.class));
                return true;

            case R.id.action_androidchartactivity10:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity10.class));
                return true;

            case R.id.action_androidchartactivity11:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity11.class));
                return true;

            case R.id.action_androidchartactivity12:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity12.class));
                return true;

            case R.id.action_androidchartactivity13:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity13.class));
                return true;

            case R.id.action_androidchartactivity14:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity14.class));
                return true;

            case R.id.action_androidchartactivity15:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity15.class));
                return true;

            case R.id.action_androidchartactivity16:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity16.class));
                return true;

            case R.id.action_androidchartactivity17:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity17.class));
                return true;

            case R.id.action_androidchartactivity18:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity18.class));
                return true;

            case R.id.action_androidchartactivity19:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity19.class));
                return true;

            case R.id.action_androidchartactivity20:
                startActivity(new Intent(AndroidChartActivity1.this, AndroidChartActivity20.class));
                return true;
        }

        return true;
    }
}
