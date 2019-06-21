package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Waterfall;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity16 extends AppCompatActivity {
    private AnyChartView waterfallChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart16);
        waterfallChart = (AnyChartView)findViewById(R.id.waterfallChart);
        waterfallChart.setProgressBar(findViewById(R.id.progress_bar));
        Waterfall waterfall = AnyChart.waterfall();
        waterfall.title("Inducesmile corp. Revenue Flow 2018");
        waterfall.yScale().minimum(0d);
        waterfall.yAxis(0).labels().format("${%Value}{scale:(1000000)(1)|(mln)}");
        waterfall.labels().enabled(true);
        waterfall.labels().format(
                "function() {\n" +
                        "      if (this['isTotal']) {\n" +
                        "        return anychart.format.number(this.absolute, {\n" +
                        "          scale: true\n" +
                        "        })\n" +
                        "      }\n" +
                        "\n" +
                        "      return anychart.format.number(this.value, {\n" +
                        "        scale: true\n" +
                        "      })\n" +
                        "    }");
        waterfall.data(getData());
        waterfallChart.setChart(waterfall);
    }

    private ArrayList getData() {
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new ValueDataEntry("Start", 23000000));
        entries.add(new ValueDataEntry("Jan", 2200000));
        entries.add(new ValueDataEntry("Feb", -4600000));
        entries.add(new ValueDataEntry("Mar", -9100000));
        entries.add(new ValueDataEntry("Apr", 3700000));
        entries.add(new ValueDataEntry("May", -2100000));
        entries.add(new ValueDataEntry("Jun", 5300000));
        entries.add(new ValueDataEntry("Jul", 3100000));
        entries.add(new ValueDataEntry("Aug", -1500000));
        entries.add(new ValueDataEntry("Sep", 4200000));
        entries.add(new ValueDataEntry("Oct", 5300000));
        entries.add(new ValueDataEntry("Nov", -1500000));
        entries.add(new ValueDataEntry("Dec", 5100000));
        DataEntry end = new DataEntry();
        end.setValue("x", "End");
        end.setValue("isTotal", true);
        entries.add(end);
        return entries;
    }
}
