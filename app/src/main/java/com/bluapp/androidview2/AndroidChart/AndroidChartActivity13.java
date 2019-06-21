package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.NameValueDataEntry;
import com.anychart.charts.Venn;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity13 extends AppCompatActivity {
    private AnyChartView vennDiagramChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart13);
        vennDiagramChart = (AnyChartView)findViewById(R.id.vennDiagramChart);
        vennDiagramChart.setProgressBar(findViewById(R.id.progress_bar));
        Venn venn = AnyChart.venn();
        venn.data(getData());
        venn.stroke("2 #FFFFFF");
        venn.labels().format("{%Name}");
        venn.intersections().hovered().fill("black", 0.25d);
        venn.intersections().labels().fontWeight("bold");
        venn.intersections().labels().format("{%Name}");
        venn.tooltip().titleFormat("{%Name}");
        vennDiagramChart.setChart(venn);
    }


    private ArrayList getData(){
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new NameValueDataEntry("A", "English", 100));
        entries.add(new NameValueDataEntry("B", "Computer", 25));
        entries.add(new NameValueDataEntry("C", "Math", 25));
        entries.add(new NameValueDataEntry("D", "Geography", 25));
        entries.add(new NameValueDataEntry("A&B", "Computer", 50));
        entries.add(new NameValueDataEntry("A&C", "Math", 50));
        entries.add(new NameValueDataEntry("A&D", "Geography", 50));
        entries.add(new NameValueDataEntry("B&C", "History", 5));
        entries.add(new NameValueDataEntry("C&D", "Biology", 5));
        entries.add(new NameValueDataEntry("D&B", "Chemistry", 5));
        entries.add(new NameValueDataEntry("B&C&D", "Physics", 5));
        return entries;
    }
}
