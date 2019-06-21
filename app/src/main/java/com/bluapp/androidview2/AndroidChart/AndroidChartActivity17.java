package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Scatter;
import com.anychart.core.scatter.series.Line;
import com.anychart.core.scatter.series.Marker;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.graphics.vector.GradientKey;
import com.anychart.graphics.vector.LinearGradientStroke;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity17 extends AppCompatActivity {
    private AnyChartView scatterChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart17);
        scatterChart = (AnyChartView)findViewById(R.id.scatterChart);
        scatterChart.setProgressBar(findViewById(R.id.progress_bar));
        Scatter scatter = AnyChart.scatter();
        scatter.animation(true);
        scatter.title("System interruptions");
        scatter.xScale()
                .minimum(1.5d)
                .maximum(5.5d);
        scatter.yScale()
                .minimum(40d)
                .maximum(100d);
        scatter.yAxis(0).title("Waiting time between interruptions (Min)");
        scatter.xAxis(0)
                .title("Interruption duration (Min)")
                .drawFirstLabel(false)
                .drawLastLabel(false);
        scatter.interactivity()
                .hoverMode(HoverMode.BY_SPOT)
                .spotRadius(30d);
        scatter.tooltip().displayMode(TooltipDisplayMode.UNION);
        Marker marker = scatter.marker(getMarkerData());
        marker.type(MarkerType.TRIANGLE_UP)
                .size(4d);
        marker.hovered()
                .size(7d)
                .fill(new SolidFill("gold", 1d))
                .stroke("anychart.color.darken(gold)");
        marker.tooltip()
                .hAlign(HAlign.START)
                .format("Waiting time: ${%Value} min.\\nDuration: ${%X} min.");

        Line scatterSeriesLine = scatter.line(getLineData());
        GradientKey gradientKey[] = new GradientKey[] {
                new GradientKey("#abcabc", 0d, 1d),
                new GradientKey("#cbacba", 40d, 1d)
        };
        LinearGradientStroke linearGradientStroke = new LinearGradientStroke(0d, null, gradientKey, null, null, true, 1d, 2d);
        scatterSeriesLine.stroke(linearGradientStroke, 3d, null, (String) null, (String) null);
        scatterChart.setChart(scatter);
    }

    private ArrayList getLineData() {
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new ValueDataEntry(1.7, 54.310454158527));
        entries.add(new ValueDataEntry(1.8, 55.2005091829704));
        entries.add(new ValueDataEntry(1.9, 56.0905642074139));
        entries.add(new ValueDataEntry(2, 56.9806192318574));
        entries.add(new ValueDataEntry(2.1, 57.8706742563008));
        entries.add(new ValueDataEntry(2.2, 58.7607292807443));
        entries.add(new ValueDataEntry(2.3, 59.6507843051877));
        entries.add(new ValueDataEntry(2.5, 61.4308943540747));
        entries.add(new ValueDataEntry(2.6, 62.3209493785181));
        entries.add(new ValueDataEntry(2.7, 63.2110044029616));
        entries.add(new ValueDataEntry(2.9, 64.9911144518485));
        entries.add(new ValueDataEntry(3, 65.881169476292));
        entries.add(new ValueDataEntry(3.1, 66.7712245007354));
        entries.add(new ValueDataEntry(3.2, 67.6612795251789));
        entries.add(new ValueDataEntry(3.3, 68.5513345496223));
        entries.add(new ValueDataEntry(3.4, 69.4413895740658));
        entries.add(new ValueDataEntry(3.5, 70.3314445985093));
        entries.add(new ValueDataEntry(3.6, 71.2214996229527));
        entries.add(new ValueDataEntry(3.7, 72.1115546473962));
        entries.add(new ValueDataEntry(3.8, 73.0016096718396));
        entries.add(new ValueDataEntry(3.9, 73.8916646962831));
        entries.add(new ValueDataEntry(4, 74.7817197207266));
        entries.add(new ValueDataEntry(4.1, 75.67177474517));
        entries.add(new ValueDataEntry(4.2, 76.5618297696135));
        entries.add(new ValueDataEntry(4.3, 77.4518847940569));
        entries.add(new ValueDataEntry(4.4, 78.3419398185004));
        entries.add(new ValueDataEntry(4.5, 79.2319948429438));
        entries.add(new ValueDataEntry(4.6, 80.1220498673873));
        entries.add(new ValueDataEntry(4.7, 81.0121048918308));
        entries.add(new ValueDataEntry(4.8, 81.9021599162742));
        entries.add(new ValueDataEntry(4.9, 82.7922149407177));
        entries.add(new ValueDataEntry(5, 83.6822699651611));
        entries.add(new ValueDataEntry(5.1, 84.5723249896046));
        entries.add(new ValueDataEntry(5.2, 85.4623800140481));
        return entries;
    }

    private ArrayList getMarkerData() {
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new ValueDataEntry(4.4, 78));
        entries.add(new ValueDataEntry(3.9, 74));
        entries.add(new ValueDataEntry(4, 68));
        entries.add(new ValueDataEntry(4, 76));
        entries.add(new ValueDataEntry(3.5, 80));
        entries.add(new ValueDataEntry(4.1, 84));
        entries.add(new ValueDataEntry(2.3, 50));
        entries.add(new ValueDataEntry(4.7, 93));
        entries.add(new ValueDataEntry(1.7, 55));
        entries.add(new ValueDataEntry(4.9, 76));
        entries.add(new ValueDataEntry(1.7, 58));
        entries.add(new ValueDataEntry(4.6, 74));
        entries.add(new ValueDataEntry(3.4, 75));
        entries.add(new ValueDataEntry(4.3, 80));
        entries.add(new ValueDataEntry(1.7, 56));
        entries.add(new ValueDataEntry(3.9, 80));
        entries.add(new ValueDataEntry(3.7, 69));
        entries.add(new ValueDataEntry(3.1, 57));
        entries.add(new ValueDataEntry(4, 90));
        entries.add(new ValueDataEntry(1.8, 42));
        entries.add(new ValueDataEntry(4.1, 91));
        entries.add(new ValueDataEntry(1.8, 51));
        entries.add(new ValueDataEntry(3.2, 79));
        entries.add(new ValueDataEntry(1.9, 53));
        entries.add(new ValueDataEntry(4.6, 82));
        entries.add(new ValueDataEntry(2, 51));
        entries.add(new ValueDataEntry(4.5, 76));
        entries.add(new ValueDataEntry(3.9, 82));
        entries.add(new ValueDataEntry(4.3, 84));
        entries.add(new ValueDataEntry(2.3, 53));
        entries.add(new ValueDataEntry(3.8, 86));
        entries.add(new ValueDataEntry(1.9, 51));
        entries.add(new ValueDataEntry(4.6, 85));
        entries.add(new ValueDataEntry(1.8, 45));
        entries.add(new ValueDataEntry(4.7, 88));
        entries.add(new ValueDataEntry(1.8, 51));
        entries.add(new ValueDataEntry(4.6, 80));
        entries.add(new ValueDataEntry(1.9, 49));
        entries.add(new ValueDataEntry(3.5, 82));
        entries.add(new ValueDataEntry(4, 75));
        entries.add(new ValueDataEntry(3.7, 73));
        entries.add(new ValueDataEntry(3.7, 67));
        entries.add(new ValueDataEntry(4.3, 68));
        entries.add(new ValueDataEntry(3.6, 86));
        entries.add(new ValueDataEntry(3.8, 72));
        entries.add(new ValueDataEntry(3.8, 75));
        entries.add(new ValueDataEntry(3.8, 75));
        entries.add(new ValueDataEntry(2.5, 66));
        entries.add(new ValueDataEntry(4.5, 84));
        entries.add(new ValueDataEntry(4.1, 70));
        entries.add(new ValueDataEntry(3.7, 79));
        entries.add(new ValueDataEntry(3.8, 60));
        entries.add(new ValueDataEntry(3.4, 86));
        entries.add(new ValueDataEntry(4, 71));
        entries.add(new ValueDataEntry(2.3, 67));
        entries.add(new ValueDataEntry(4.4, 81));
        entries.add(new ValueDataEntry(4.1, 76));
        entries.add(new ValueDataEntry(4.3, 83));
        entries.add(new ValueDataEntry(3.3, 76));
        entries.add(new ValueDataEntry(2, 55));
        entries.add(new ValueDataEntry(4.3, 73));
        entries.add(new ValueDataEntry(2.9, 56));
        entries.add(new ValueDataEntry(4.6, 83));
        entries.add(new ValueDataEntry(1.9, 57));
        entries.add(new ValueDataEntry(3.6, 71));
        entries.add(new ValueDataEntry(3.7, 72));
        entries.add(new ValueDataEntry(3.7, 77));
        entries.add(new ValueDataEntry(1.8, 55));
        entries.add(new ValueDataEntry(4.6, 75));
        entries.add(new ValueDataEntry(3.5, 73));
        entries.add(new ValueDataEntry(4, 70));
        entries.add(new ValueDataEntry(3.7, 83));
        entries.add(new ValueDataEntry(1.7, 50));
        entries.add(new ValueDataEntry(4.6, 95));
        entries.add(new ValueDataEntry(1.7, 51));
        entries.add(new ValueDataEntry(4, 82));
        entries.add(new ValueDataEntry(1.8, 54));
        entries.add(new ValueDataEntry(4.4, 83));
        entries.add(new ValueDataEntry(1.9, 51));
        entries.add(new ValueDataEntry(4.6, 80));
        entries.add(new ValueDataEntry(2.9, 78));
        entries.add(new ValueDataEntry(3.5, 81));
        entries.add(new ValueDataEntry(2, 53));
        entries.add(new ValueDataEntry(4.3, 89));
        entries.add(new ValueDataEntry(1.8, 44));
        entries.add(new ValueDataEntry(4.1, 78));
        entries.add(new ValueDataEntry(1.8, 61));
        entries.add(new ValueDataEntry(4.7, 73));
        entries.add(new ValueDataEntry(4.2, 75));
        entries.add(new ValueDataEntry(3.9, 73));
        entries.add(new ValueDataEntry(4.3, 76));
        entries.add(new ValueDataEntry(1.8, 55));
        entries.add(new ValueDataEntry(4.5, 86));
        entries.add(new ValueDataEntry(2, 48));
        entries.add(new ValueDataEntry(4.2, 77));
        entries.add(new ValueDataEntry(4.4, 73));
        entries.add(new ValueDataEntry(4.1, 70));
        entries.add(new ValueDataEntry(4.1, 88));
        entries.add(new ValueDataEntry(4, 75));
        entries.add(new ValueDataEntry(4.1, 83));
        entries.add(new ValueDataEntry(2.7, 61));
        entries.add(new ValueDataEntry(4.6, 78));
        entries.add(new ValueDataEntry(1.9, 61));
        entries.add(new ValueDataEntry(4.5, 81));
        entries.add(new ValueDataEntry(2, 51));
        entries.add(new ValueDataEntry(4.8, 80));
        entries.add(new ValueDataEntry(4.1, 79));
        entries.add(new ValueDataEntry(4.1, 82));
        entries.add(new ValueDataEntry(4.2, 80));
        entries.add(new ValueDataEntry(4.5, 76));
        entries.add(new ValueDataEntry(1.9, 56));
        entries.add(new ValueDataEntry(4.7, 82));
        entries.add(new ValueDataEntry(2, 47));
        entries.add(new ValueDataEntry(4.7, 76));
        entries.add(new ValueDataEntry(2.5, 61));
        entries.add(new ValueDataEntry(4.3, 75));
        entries.add(new ValueDataEntry(4.4, 72));
        entries.add(new ValueDataEntry(4.4, 74));
        entries.add(new ValueDataEntry(4.3, 69));
        entries.add(new ValueDataEntry(4.6, 78));
        entries.add(new ValueDataEntry(2.1, 52));
        entries.add(new ValueDataEntry(4.8, 91));
        entries.add(new ValueDataEntry(4.1, 66));
        entries.add(new ValueDataEntry(4, 71));
        entries.add(new ValueDataEntry(4, 75));
        entries.add(new ValueDataEntry(4.4, 81));
        entries.add(new ValueDataEntry(4.1, 77));
        entries.add(new ValueDataEntry(4.3, 74));
        entries.add(new ValueDataEntry(4, 70));
        entries.add(new ValueDataEntry(3.9, 83));
        entries.add(new ValueDataEntry(3.2, 53));
        entries.add(new ValueDataEntry(4.5, 82));
        entries.add(new ValueDataEntry(2.2, 62));
        entries.add(new ValueDataEntry(4.7, 73));
        entries.add(new ValueDataEntry(4.6, 84));
        entries.add(new ValueDataEntry(2.2, 58));
        entries.add(new ValueDataEntry(4.8, 82));
        entries.add(new ValueDataEntry(4.3, 77));
        entries.add(new ValueDataEntry(3.8, 75));
        entries.add(new ValueDataEntry(4, 77));
        entries.add(new ValueDataEntry(4.1, 77));
        entries.add(new ValueDataEntry(1.8, 53));
        entries.add(new ValueDataEntry(4.4, 75));
        entries.add(new ValueDataEntry(4, 78));
        entries.add(new ValueDataEntry(2.2, 51));
        entries.add(new ValueDataEntry(5.1, 81));
        entries.add(new ValueDataEntry(1.9, 52));
        entries.add(new ValueDataEntry(5, 76));
        entries.add(new ValueDataEntry(4.4, 73));
        entries.add(new ValueDataEntry(4.5, 84));
        entries.add(new ValueDataEntry(3.8, 72));
        entries.add(new ValueDataEntry(4.3, 89));
        entries.add(new ValueDataEntry(4.4, 75));
        entries.add(new ValueDataEntry(2.2, 57));
        entries.add(new ValueDataEntry(4.8, 81));
        entries.add(new ValueDataEntry(1.9, 49));
        entries.add(new ValueDataEntry(4.7, 87));
        entries.add(new ValueDataEntry(1.8, 43));
        entries.add(new ValueDataEntry(4.8, 94));
        entries.add(new ValueDataEntry(2, 45));
        entries.add(new ValueDataEntry(4.4, 81));
        entries.add(new ValueDataEntry(2.5, 59));
        entries.add(new ValueDataEntry(4.3, 82));
        entries.add(new ValueDataEntry(4.4, 80));
        entries.add(new ValueDataEntry(1.9, 54));
        entries.add(new ValueDataEntry(4.7, 75));
        entries.add(new ValueDataEntry(4.3, 73));
        entries.add(new ValueDataEntry(2.2, 57));
        entries.add(new ValueDataEntry(4.7, 80));
        entries.add(new ValueDataEntry(2.3, 51));
        entries.add(new ValueDataEntry(4.6, 77));
        entries.add(new ValueDataEntry(3.3, 66));
        entries.add(new ValueDataEntry(4.2, 77));
        entries.add(new ValueDataEntry(2.9, 60));
        entries.add(new ValueDataEntry(4.6, 86));
        entries.add(new ValueDataEntry(3.3, 62));
        entries.add(new ValueDataEntry(4.2, 75));
        entries.add(new ValueDataEntry(2.6, 67));
        entries.add(new ValueDataEntry(4.6, 69));
        entries.add(new ValueDataEntry(3.7, 84));
        entries.add(new ValueDataEntry(1.8, 58));
        entries.add(new ValueDataEntry(4.7, 90));
        entries.add(new ValueDataEntry(4.5, 82));
        entries.add(new ValueDataEntry(4.5, 71));
        entries.add(new ValueDataEntry(4.8, 80));
        entries.add(new ValueDataEntry(2, 51));
        entries.add(new ValueDataEntry(4.8, 80));
        entries.add(new ValueDataEntry(1.9, 62));
        entries.add(new ValueDataEntry(4.7, 84));
        entries.add(new ValueDataEntry(2, 51));
        entries.add(new ValueDataEntry(5.1, 81));
        entries.add(new ValueDataEntry(4.3, 83));
        entries.add(new ValueDataEntry(4.8, 84));
        entries.add(new ValueDataEntry(3, 72));
        entries.add(new ValueDataEntry(2.1, 54));
        entries.add(new ValueDataEntry(4.6, 75));
        entries.add(new ValueDataEntry(4, 74));
        entries.add(new ValueDataEntry(2.2, 51));
        entries.add(new ValueDataEntry(5.1, 91));
        entries.add(new ValueDataEntry(2.9, 60));
        entries.add(new ValueDataEntry(4.3, 80));
        entries.add(new ValueDataEntry(2.1, 54));
        entries.add(new ValueDataEntry(4.7, 80));
        entries.add(new ValueDataEntry(4.5, 70));
        entries.add(new ValueDataEntry(1.7, 60));
        entries.add(new ValueDataEntry(4.2, 86));
        entries.add(new ValueDataEntry(4.3, 78));
        entries.add(new ValueDataEntry(1.7, 51));
        entries.add(new ValueDataEntry(4.4, 83));
        entries.add(new ValueDataEntry(4.2, 76));
        entries.add(new ValueDataEntry(2.2, 51));
        entries.add(new ValueDataEntry(4.7, 90));
        entries.add(new ValueDataEntry(4, 71));
        entries.add(new ValueDataEntry(1.8, 49));
        entries.add(new ValueDataEntry(4.7, 88));
        entries.add(new ValueDataEntry(1.8, 52));
        entries.add(new ValueDataEntry(4.5, 79));
        entries.add(new ValueDataEntry(2.1, 61));
        entries.add(new ValueDataEntry(4.2, 81));
        entries.add(new ValueDataEntry(2.1, 48));
        entries.add(new ValueDataEntry(5.2, 84));
        entries.add(new ValueDataEntry(2, 63));
        return entries;
    }
}
