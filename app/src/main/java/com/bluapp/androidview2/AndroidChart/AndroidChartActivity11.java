package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.bluapp.androidview2.R;

import java.util.ArrayList;
import java.util.List;

public class AndroidChartActivity11 extends AppCompatActivity {
    private AnyChartView pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart11);
        pieChart = (AnyChartView)findViewById(R.id.pieChart);
        pieChart.setProgressBar(findViewById(R.id.progress_bar));
        Pie pie = AnyChart.pie();
        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(AndroidChartActivity11.this, event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });
        pie.data(getData());
        pie.title("Phone imported in 2019");
        pie.labels().position("outside");
        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Retail channels")
                .padding(0d, 0d, 10d, 0d);
        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);
        pieChart.setChart(pie);
    }

    private ArrayList getData(){
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new ValueDataEntry("Samsung", 6573564));
        entries.add(new ValueDataEntry("Tecno", 785082));
        entries.add(new ValueDataEntry("Iphone", 7516371));
        entries.add(new ValueDataEntry("Sony", 1236421));
        entries.add(new ValueDataEntry("Itel", 1000000));
        return entries;
    }

}
