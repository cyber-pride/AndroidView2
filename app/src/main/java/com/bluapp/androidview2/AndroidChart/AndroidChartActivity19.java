package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Polar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.PolarSeriesType;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.ScaleTypes;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.scales.Linear;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity19 extends AppCompatActivity {
    private AnyChartView polarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart19);
        polarChart = (AnyChartView)findViewById(R.id.polarChart);
        polarChart.setProgressBar(findViewById(R.id.progress_bar));
        Polar polar = AnyChart.polar();
        Set set = Set.instantiate();
        set.data(getData());
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Data = set.mapAs("{ x: 'x', value: 'value3' }");
        polar.column(series1Data);
        polar.column(series2Data);
        polar.column(series3Data);
        polar.title("Company Profit Dynamic in Regions by Year");
        polar.sortPointsByX(true)
                .defaultSeriesType(PolarSeriesType.COLUMN)
                .yAxis(false)
                .xScale(ScaleTypes.ORDINAL);
        polar.title().margin().bottom(20d);
        ((Linear) polar.yScale(Linear.class)).stackMode(ScaleStackMode.VALUE);
        polar.tooltip()
                .valuePrefix("$")
                .displayMode(TooltipDisplayMode.UNION);
        polarChart.setChart(polar);
    }

    private ArrayList getData() {
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new CustomDataEntry("Nail polish", 12814, 4376, 4229));
        entries.add(new CustomDataEntry("Eyebrow pencil", 13012, 3987, 3932));
        entries.add(new CustomDataEntry("Rouge", 11624, 3574, 5221));
        entries.add(new CustomDataEntry("Pomade", 8814, 4376, 9256));
        entries.add(new CustomDataEntry("Eyeshadows", 12998, 4572, 3308));
        entries.add(new CustomDataEntry("Eyeliner", 12321, 3417, 5432));
        entries.add(new CustomDataEntry("Foundation", 10342, 5231, 13701));
        entries.add(new CustomDataEntry("Lip gloss", 22998, 4572, 4008));
        entries.add(new CustomDataEntry("Mascara", 11261, 6134, 18712));
        entries.add(new CustomDataEntry("Powder", 10261, 5134, 25712));
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
