package com.bluapp.androidview2.AndroidChart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.CategoryValueDataEntry;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.TagCloud;
import com.anychart.scales.OrdinalColor;
import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class AndroidChartActivity15 extends AppCompatActivity {
    private AnyChartView tagcloudChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chart15);
        tagcloudChart = (AnyChartView)findViewById(R.id.tagcloudChart);
        tagcloudChart.setProgressBar(findViewById(R.id.progress_bar));
        TagCloud tagCloud = AnyChart.tagCloud();
        tagCloud.title("World Population");
        OrdinalColor ordinalColor = OrdinalColor.instantiate();
        ordinalColor.colors(new String[] {
                "#26959f", "#f18126", "#3b8ad8", "#60727b", "#e24b26"
        });
        tagCloud.colorScale(ordinalColor);
        tagCloud.angles(new Double[] {-90d, 0d, 90d});
        tagCloud.colorRange().enabled(true);
        tagCloud.colorRange().colorLineSize(15d);
        tagCloud.data(getData());
        tagcloudChart.setChart(tagCloud);
    }

    private ArrayList getData(){
        ArrayList<DataEntry> entries = new ArrayList<>();
        entries.add(new CategoryValueDataEntry("China", "asia", 1383220000));
        entries.add(new CategoryValueDataEntry("India", "asia", 1316000000));
        entries.add(new CategoryValueDataEntry("United States", "america", 324982000));
        entries.add(new CategoryValueDataEntry("Indonesia", "asia", 263510000));
        entries.add(new CategoryValueDataEntry("Brazil", "america", 207505000));
        entries.add(new CategoryValueDataEntry("Pakistan", "asia", 196459000));
        entries.add(new CategoryValueDataEntry("Nigeria", "africa", 191836000));
        entries.add(new CategoryValueDataEntry("Bangladesh", "asia", 162459000));
        entries.add(new CategoryValueDataEntry("Russia", "europe", 146804372));
        entries.add(new CategoryValueDataEntry("Japan", "asia", 126790000));
        entries.add(new CategoryValueDataEntry("Mexico", "america", 123518000));
        entries.add(new CategoryValueDataEntry("Ethiopia", "africa", 104345000));
        entries.add(new CategoryValueDataEntry("Philippines", "asia", 104037000));
        entries.add(new CategoryValueDataEntry("Egypt", "africa", 93013300));
        entries.add(new CategoryValueDataEntry("Vietnam", "asia", 92700000));
        entries.add(new CategoryValueDataEntry("Germany", "europe", 82800000));
        entries.add(new CategoryValueDataEntry("Democratic Republic of the Congo", "africa", 82243000));
        entries.add(new CategoryValueDataEntry("Iran", "asia", 80135400));
        entries.add(new CategoryValueDataEntry("Turkey", "asia", 79814871));
        entries.add(new CategoryValueDataEntry("Thailand", "asia", 68298000));
        entries.add(new CategoryValueDataEntry("France", "europe", 67013000));
        entries.add(new CategoryValueDataEntry("United Kingdom", "europe", 65110000));
        entries.add(new CategoryValueDataEntry("Italy", "europe", 60599936));
        entries.add(new CategoryValueDataEntry("Tanzania", "africa", 56878000));
        entries.add(new CategoryValueDataEntry("South Africa", "africa", 55908000));
        entries.add(new CategoryValueDataEntry("Myanmar", "asia", 54836000));
        entries.add(new CategoryValueDataEntry("South Korea", "asia", 51446201));
        entries.add(new CategoryValueDataEntry("Colombia", "america", 49224700));
        entries.add(new CategoryValueDataEntry("Kenya", "africa", 48467000));
        entries.add(new CategoryValueDataEntry("Spain", "europe", 46812000));
        entries.add(new CategoryValueDataEntry("Argentina", "america", 43850000));
        entries.add(new CategoryValueDataEntry("Ukraine", "europe", 42541633));
        entries.add(new CategoryValueDataEntry("Sudan", "africa", 42176000));
        entries.add(new CategoryValueDataEntry("Uganda", "africa", 41653000));
        entries.add(new CategoryValueDataEntry("Algeria", "africa", 41064000));
        entries.add(new CategoryValueDataEntry("Poland", "europe", 38424000));
        entries.add(new CategoryValueDataEntry("Iraq", "asia", 37883543));
        entries.add(new CategoryValueDataEntry("Canada", "america", 36541000));
        entries.add(new CategoryValueDataEntry("Morocco", "africa", 34317500));
        entries.add(new CategoryValueDataEntry("Saudi Arabia", "asia", 33710021));
        entries.add(new CategoryValueDataEntry("Uzbekistan", "asia", 32121000));
        entries.add(new CategoryValueDataEntry("Malaysia", "asia", 32063200));
        entries.add(new CategoryValueDataEntry("Peru", "america", 31826018));
        entries.add(new CategoryValueDataEntry("Venezuela", "america", 31431164));
        entries.add(new CategoryValueDataEntry("Nepal", "asia", 28825709));
        entries.add(new CategoryValueDataEntry("Angola", "africa", 28359634));
        entries.add(new CategoryValueDataEntry("Ghana", "africa", 28308301));
        entries.add(new CategoryValueDataEntry("Yemen", "asia", 28120000));
        entries.add(new CategoryValueDataEntry("Afghanistan", "asia", 27657145));
        entries.add(new CategoryValueDataEntry("Mozambique", "africa", 27128530));
        entries.add(new CategoryValueDataEntry("Australia", "australia", 24460900));
        entries.add(new CategoryValueDataEntry("North Korea", "asia", 24213510));
        entries.add(new CategoryValueDataEntry("Taiwan", "asia", 23545680));
        entries.add(new CategoryValueDataEntry("Cameroon", "africa", 23248044));
        entries.add(new CategoryValueDataEntry("Ivory Coast", "africa", 22671331));
        entries.add(new CategoryValueDataEntry("Madagascar", "africa", 22434363));
        entries.add(new CategoryValueDataEntry("Niger", "africa", 21564000));
        entries.add(new CategoryValueDataEntry("Sri Lanka", "asia", 21203000));
        entries.add(new CategoryValueDataEntry("Romania", "europe", 19760000));
        entries.add(new CategoryValueDataEntry("Burkina Faso", "africa", 19632147));
        entries.add(new CategoryValueDataEntry("Syria", "asia", 18907000));
        entries.add(new CategoryValueDataEntry("Mali", "africa", 18875000));
        entries.add(new CategoryValueDataEntry("Malawi", "africa", 18299000));
        entries.add(new CategoryValueDataEntry("Chile", "america", 18191900));
        entries.add(new CategoryValueDataEntry("Kazakhstan", "asia", 17975800));
        entries.add(new CategoryValueDataEntry("Netherlands", "europe", 17121900));
        entries.add(new CategoryValueDataEntry("Ecuador", "america", 16737700));
        entries.add(new CategoryValueDataEntry("Guatemala", "america", 16176133));
        entries.add(new CategoryValueDataEntry("Zambia", "africa", 15933883));
        entries.add(new CategoryValueDataEntry("Cambodia", "asia", 15626444));
        entries.add(new CategoryValueDataEntry("Senegal", "africa", 15256346));
        entries.add(new CategoryValueDataEntry("Chad", "africa", 14965000));
        entries.add(new CategoryValueDataEntry("Zimbabwe", "africa", 14542235));
        entries.add(new CategoryValueDataEntry("Guinea", "africa", 13291000));
        entries.add(new CategoryValueDataEntry("South Sudan", "africa", 12131000));
        entries.add(new CategoryValueDataEntry("Rwanda", "africa", 11553188));
        entries.add(new CategoryValueDataEntry("Belgium", "europe", 11356191));
        entries.add(new CategoryValueDataEntry("Tunisia", "africa", 11299400));
        entries.add(new CategoryValueDataEntry("Cuba", "america", 11239004));
        entries.add(new CategoryValueDataEntry("Bolivia", "america", 11145770));
        entries.add(new CategoryValueDataEntry("Somalia", "africa", 11079000));
        entries.add(new CategoryValueDataEntry("Haiti", "america", 11078033));
        entries.add(new CategoryValueDataEntry("Greece", "europe", 10783748));
        entries.add(new CategoryValueDataEntry("Benin", "africa", 10653654));
        entries.add(new CategoryValueDataEntry("Czech Republic", "europe", 10578820));
        entries.add(new CategoryValueDataEntry("Portugal", "europe", 10341330));
        entries.add(new CategoryValueDataEntry("Burundi", "africa", 10114505));
        entries.add(new CategoryValueDataEntry("Dominican Republic", "america", 10075045));
        entries.add(new CategoryValueDataEntry("Sweden", "europe", 10054100));
        entries.add(new CategoryValueDataEntry("United Arab Emirates", "asia", 10003223));
        entries.add(new CategoryValueDataEntry("Jordan", "asia", 9889270));
        entries.add(new CategoryValueDataEntry("Azerbaijan", "asia", 9823667));
        entries.add(new CategoryValueDataEntry("Hungary", "europe", 9799000));
        entries.add(new CategoryValueDataEntry("Belarus", "europe", 9498600));
        entries.add(new CategoryValueDataEntry("Honduras", "america", 8866351));
        entries.add(new CategoryValueDataEntry("Austria", "europe", 8773686));
        entries.add(new CategoryValueDataEntry("Tajikistan", "asia", 8742000));
        entries.add(new CategoryValueDataEntry("Israel", "asia", 8690220));
        entries.add(new CategoryValueDataEntry("Switzerland", "europe", 8417700));
        entries.add(new CategoryValueDataEntry("Papua New Guinea", "australia", 8151300));
        return entries;
    }
}
