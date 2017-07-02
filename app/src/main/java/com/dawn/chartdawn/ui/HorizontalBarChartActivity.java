package com.dawn.chartdawn.ui;

import com.dawn.chartdawn.R;
import com.dawn.chartdawn.custom.BarAxisValueFormatter;
import com.dawn.chartdawn.custom.BarMarkerView;
import com.dawn.chartdawn.model.BarModel;
import com.dawn.chartdawn.model.Model;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2017/7/2.
 */

public class HorizontalBarChartActivity extends BaseActivity {
    private HorizontalBarChart horizontalBarChart;
    private List<BarEntry> barEntries;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_horizontal_bar_chart;
    }

    @Override
    protected void initData() {
        barEntries = new ArrayList<>();
        List<Model> models = getAssetData(BarModel.class);
        for(int i = 0; i < models.size(); i ++){
            Model model = models.get(i);
            barEntries.add(new BarEntry(i, model.getNum(), getResources().getDrawable(R.mipmap.ic_launcher)));
        }
    }

    @Override
    protected void initView() {
        horizontalBarChart = (HorizontalBarChart) findViewById(R.id.horizontal_bar_chart);
    }

    @Override
    protected void initListener() {
        setHorizontalBarChartListener();
        if (horizontalBarChart.getData() != null &&
                horizontalBarChart.getData().getDataSetCount() > 0) {
            refreshHorizontalBarChart(barEntries);
        }else{
            BarDataSet dataSet = setHorizontalBarChartStyle(barEntries);
            List<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(dataSet);
            BarData barData = new BarData(dataSets);
            barData.setValueTextSize(10f);//设置文字大小
            barData.setBarWidth(0.9f);//设置圆柱宽度
            horizontalBarChart.setData(barData);
        }
    }
    /**
     * 图表事件设置
     */
    private void setHorizontalBarChartListener(){
        horizontalBarChart.setDrawBarShadow(false);//除了圆柱外其他部分是否填充阴影
        horizontalBarChart.setDrawValueAboveBar(true);//是否将值写到圆柱上方
        horizontalBarChart.getDescription().setEnabled(false);//右下角的description是否显示
//        horizontalBarChart.setMaxVisibleValueCount(60);//设置最大显示圆柱数量
//        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.animateX(1000);//动画效果

        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
        xAxis.setLabelCount(6);//x坐标label数量最大
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new BarAxisValueFormatter().new XAxisValueFormatter());

        YAxis leftAxis = horizontalBarChart.getAxisLeft();
        leftAxis.setValueFormatter(new BarAxisValueFormatter().new YAxisValueFormatter());
        YAxis rightAxis = horizontalBarChart.getAxisRight();
        rightAxis.setValueFormatter(new BarAxisValueFormatter().new YAxisValueFormatter());

        Legend legend = horizontalBarChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);//设置legend在图形下方
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//设置legend和图形左对齐
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//设置legend和图形的位置关系
        legend.setDrawInside(false);//legend和图形是否显示一起
        legend.setForm(Legend.LegendForm.SQUARE);//legend图形样式
        legend.setFormSize(9f);//设置legend的方格大小
        legend.setTextSize(11f);//设置legend的文字大小
//        legend.setXEntrySpace(4f);

        BarMarkerView barMarkerView = new BarMarkerView(this, new BarAxisValueFormatter().new XAxisValueFormatter());
        barMarkerView.setChartView(horizontalBarChart);
        horizontalBarChart.setMarker(barMarkerView);

        horizontalBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight h) {
                toastUI(entry.getY() + "");
            }

            @Override
            public void onNothingSelected() {
                toastUI("nothing");
            }
        });
    }
    /**
     * 图表样式设置
     * @param barEntries
     */
    private BarDataSet setHorizontalBarChartStyle(List<BarEntry> barEntries){
        //图表样式的设置
        BarDataSet dataSet = new BarDataSet(barEntries, "bar chart");
        dataSet.setDrawIcons(false);//是否采用Entry里面的图片
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);//设置圆柱的颜色
        return dataSet;
    }
    /**
     * 线性图表的刷新
     * @param barEntries
     */
    private void refreshHorizontalBarChart(List<BarEntry> barEntries){
        BarDataSet dataSet = (BarDataSet)horizontalBarChart.getData().getDataSetByIndex(0);
        dataSet.setValues(barEntries);
        horizontalBarChart.getData().notifyDataChanged();
        horizontalBarChart.notifyDataSetChanged();
    }
}
