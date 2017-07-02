package com.dawn.chartdawn.ui;

import android.graphics.Color;

import com.dawn.chartdawn.R;
import com.dawn.chartdawn.custom.RadarMarkerView;
import com.dawn.chartdawn.model.Model;
import com.dawn.chartdawn.model.RadarModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2017/7/2.
 */

public class RadarChartActivity extends BaseActivity {
    private RadarChart radarChart;
    private List<RadarEntry> radarEntries;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_radar_chart;
    }

    @Override
    protected void initData() {
        radarEntries = new ArrayList<>();
        List<Model> models = getAssetData(RadarModel.class);
        for(int i = 0; i < models.size(); i ++){
            Model model = models.get(i);
            radarEntries.add(new RadarEntry(model.getNum()));
        }
    }

    @Override
    protected void initView() {
        radarChart = (RadarChart) findViewById(R.id.radar_chart);
    }

    @Override
    protected void initListener() {
        setRadarChartListener();
        if (radarChart.getData() != null &&
                radarChart.getData().getDataSetCount() > 0) {
            refreshRadarChart(radarEntries);
        }else{
            RadarDataSet dataSet = setRadarChartStyle(radarEntries);
            List<IRadarDataSet> dataSets = new ArrayList<>();
            dataSets.add(dataSet);
            RadarData radarData = new RadarData(dataSets);
            radarData.setValueTextSize(8f);//设置中间线对应的文字大小
            radarData.setDrawValues(false);//设置中间线对应的文字是否显示
            radarData.setValueTextColor(Color.BLUE);//设置中间线对应的文字的颜色
            radarChart.setData(radarData);
        }
    }
    /**
     * 图表事件设置
     */
    private void setRadarChartListener(){
        radarChart.setBackgroundColor(Color.rgb(60, 65, 82));//设置图表的背景颜色
        radarChart.getDescription().setEnabled(false);//设置右下角的description不显示
        radarChart.setWebLineWidth(1f);//设置每个角到中心的线的宽度
        radarChart.setWebColor(Color.LTGRAY);//设置每个角到中心的线的颜色
        radarChart.setWebAlpha(100);//设置外圈的线的透明度（包括每个角到中心的线和外圈的线）
        radarChart.setWebLineWidthInner(1f);//设置外圈的线的宽度
        radarChart.setWebColorInner(Color.LTGRAY);//设置外圈的线的颜色

        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);//设置点击时的markerView
        mv.setChartView(radarChart);
        radarChart.setMarker(mv);

        radarChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);//设置动画

        XAxis xAxis = radarChart.getXAxis();//设置外层的文字等相关属性
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"Burger", "Steak", "Salad", "Pasta", "Pizza"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = radarChart.getYAxis();//设置外层点到中间点的文字属性
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend legend = radarChart.getLegend();//设置lebel
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);
        legend.setTextColor(Color.WHITE);

    }
    /**
     * 图表样式设置
     * @param radarEntries
     */
    private RadarDataSet setRadarChartStyle(List<RadarEntry> radarEntries){
        //图表样式的设置
        RadarDataSet dataSet = new RadarDataSet(radarEntries, "radar chart");
        dataSet.setColor(Color.RED);//雷达状图表线的颜色
        dataSet.setFillColor(Color.YELLOW);//中间填充的颜色，需要设置setDrawFilled
        dataSet.setDrawFilled(true);//是否设置中间填充
        dataSet.setFillAlpha(180);//设置填充的透明度
        dataSet.setLineWidth(2f);//设置中间线的宽度
//        dataSet.setDrawHighlightCircleEnabled(true);
//        dataSet.setDrawHighlightIndicators(false);

        return dataSet;
    }
    /**
     * 线性图表的刷新
     * @param radarEntries
     */
    private void refreshRadarChart(List<RadarEntry> radarEntries){
    }
}
