package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity9 extends AppCompatActivity {
    private AnyChartView lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart9);
        lineChart = (AnyChartView)findViewById(R.id.lineChart);
        lineChart.setProgressBar(findViewById(R.id.progress_bar));
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.title("Inducesmile");
        cartesian.yAxis(0).title("Number of Food Sold");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        Set set = Set.instantiate();
        set.data(getData());
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");
        Line series1 = cartesian.line(series1Mapping);
        series1.name("Pizza");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        Line series2 = cartesian.line(series2Mapping);
        series2.name("Hotdog");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        Line series3 = cartesian.line(series3Mapping);
        series3.name("Icecream");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        lineChart.setChart(cartesian);
    }

    private ArrayList getData(){
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new CustomDataEntry("2012", 3.6, 2.3, 2.8));
        entries.add(new CustomDataEntry("2013", 7.1, 4.0, 4.1));
        entries.add(new CustomDataEntry("2014", 8.5, 6.2, 5.1));
        entries.add(new CustomDataEntry("2015", 9.2, 11.8, 6.5));
        entries.add(new CustomDataEntry("2016", 10.1, 13.0, 12.5));
        entries.add(new CustomDataEntry("2017", 11.6, 13.9, 18.0));
        entries.add(new CustomDataEntry("2018", 16.4, 18.0, 21.0));
        entries.add(new CustomDataEntry("2019", 18.0, 23.3, 20.3));
        return entries;
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
    }
}
