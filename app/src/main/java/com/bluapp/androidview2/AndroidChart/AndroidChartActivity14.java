package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Radar;
import com.anychart.core.radar.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.MarkerType;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity14 extends AppCompatActivity {
    private AnyChartView radarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart14);
        radarChart = (AnyChartView)findViewById(R.id.radarChart);
        radarChart.setProgressBar(findViewById(R.id.progress_bar));
        Radar radar = AnyChart.radar();
        radar.title("WoW base stats comparison radar chart: Cj vs Ryder vs BigSmoke");
        radar.yScale().minimum(0d);
        radar.yScale().minimumGap(0d);
        radar.yScale().ticks().interval(50d);
        radar.xAxis().labels().padding(5d, 5d, 5d, 5d);
        radar.legend()
                .align(Align.CENTER)
                .enabled(true);
        Set set = Set.instantiate();
        set.data(getData());
        Mapping shamanData = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping warriorData = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping priestData = set.mapAs("{ x: 'x', value: 'value3' }");
        Line shamanLine = radar.line(shamanData);
        shamanLine.name("Cj");
        shamanLine.markers()
                .enabled(true)
                .type(MarkerType.CIRCLE)
                .size(3d);
        Line warriorLine = radar.line(warriorData);
        warriorLine.name("Ryder");
        warriorLine.markers()
                .enabled(true)
                .type(MarkerType.CIRCLE)
                .size(3d);
        Line priestLine = radar.line(priestData);
        priestLine.name("BigSmoke");
        priestLine.markers()
                .enabled(true)
                .type(MarkerType.CIRCLE)
                .size(3d);
        radar.tooltip().format("Value: {%Value}");
        radarChart.setChart(radar);
    }

    private ArrayList getData(){
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new CustomDataEntry("Strength", 136, 199, 43));
        entries.add(new CustomDataEntry("Agility", 79, 125, 56));
        entries.add(new CustomDataEntry("Stamina", 149, 173, 101));
        entries.add(new CustomDataEntry("Intellect", 135, 33, 202));
        entries.add(new CustomDataEntry("Spirit", 158, 64, 196));
        return entries;
    }

    private class CustomDataEntry extends ValueDataEntry {
        public CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
    }
}
