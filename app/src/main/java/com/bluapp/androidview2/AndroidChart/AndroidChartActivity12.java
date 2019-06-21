package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity12 extends AppCompatActivity {
    private AnyChartView columnChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart12);
        columnChart = (AnyChartView)findViewById(R.id.columnChart);
        columnChart.setProgressBar(findViewById(R.id.progress_bar));
        Cartesian cartesian = AnyChart.column();
        Column column = cartesian.column(getData());
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");
        cartesian.animation(true);
        cartesian.title("10 Cosmetic Products by Revenue");
        cartesian.yScale().minimum(0d);
        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);
        cartesian.xAxis(0).title("Product");
        cartesian.yAxis(0).title("Revenue");
        columnChart.setChart(cartesian);
    }

    private ArrayList getData(){
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new ValueDataEntry("Rouge", 85671));
        entries.add(new ValueDataEntry("Foundation", 93140));
        entries.add(new ValueDataEntry("Mascara", 124630));
        entries.add(new ValueDataEntry("Lip gloss", 110430));
        entries.add(new ValueDataEntry("Lipstick", 128000));
        entries.add(new ValueDataEntry("Nail polish", 143550));
        entries.add(new ValueDataEntry("Eyebrow pencil", 120660));
        entries.add(new ValueDataEntry("Eyeliner", 215610));
        entries.add(new ValueDataEntry("Eyeshadows", 239450));
        return entries;
    }
}
