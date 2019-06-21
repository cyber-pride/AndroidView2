package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.axes.Linear;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LabelsOverlapMode;
import com.anychart.enums.Orientation;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.bluapp.androidview2.R;

import java.util.ArrayList;
import java.util.List;

public class AndroidChartActivity10 extends AppCompatActivity {
    private AnyChartView barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart10);
        barChart = (AnyChartView)findViewById(R.id.barChart);
        barChart.setProgressBar(findViewById(R.id.progress_bar));
        Cartesian cartesian = AnyChart.bar();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.yScale().stackMode(ScaleStackMode.VALUE);

        cartesian.yAxis(0).labels().format(
                "function() {\n" +
                        "    return Math.abs(this.value).toLocaleString();\n" +
                        "  }");

        cartesian.yAxis(0d).title("Revenue in Naira");

        cartesian.xAxis(0d).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);

        Linear xAxis1 = cartesian.xAxis(1d);
        xAxis1.enabled(true);
        xAxis1.orientation(Orientation.RIGHT);
        xAxis1.overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);

        cartesian.title("Cosmetic Sales by Gender");

        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.tooltip()
                .title(false)
                .separator(false)
                .displayMode(TooltipDisplayMode.SEPARATED)
                .positionMode(TooltipPositionMode.POINT)
                .useHtml(true)
                .fontSize(12d)
                .offsetX(5d)
                .offsetY(0d)
                .format(
                        "function() {\n" +
                                "      return '<span style=\"color: #D9D9D9\">$</span>' + Math.abs(this.value).toLocaleString();\n" +
                                "    }");

        Set set = Set.instantiate();
        set.data(getData());
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");

        Bar series1 = cartesian.bar(series1Data);
        series1.name("Females")
                .color("HotPink");
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER);
        Bar series2 = cartesian.bar(series2Data);
        series2.name("Males");
        series2.tooltip()
                .position("left")
                .anchor(Anchor.RIGHT_CENTER);
        cartesian.legend().enabled(true);
        cartesian.legend().inverted(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 20d, 0d);

        barChart.setChart(cartesian);
    }

    private ArrayList getData(){
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new CustomDataEntry("Soap", 8998, -5308));
        entries.add(new CustomDataEntry("Bag", 9321, -432));
        entries.add(new CustomDataEntry("Nail polish", 8342, -1701));
        entries.add(new CustomDataEntry("LipStick", 7598, -5808));
        entries.add(new CustomDataEntry("Wristband", 6098, -3987));
        entries.add(new CustomDataEntry("Cream", 6998, -847));
        entries.add(new CustomDataEntry("Mascara", 5304, -4008));
        entries.add(new CustomDataEntry("Deodorant", 9261, -712));
        return entries;
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }
}
