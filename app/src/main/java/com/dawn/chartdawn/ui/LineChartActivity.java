package com.dawn.chartdawn.ui;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.dawn.chartdawn.R;
import com.dawn.chartdawn.custom.LineMarkerView;
import com.dawn.chartdawn.model.LineModel;
import com.dawn.chartdawn.model.LineModel2;
import com.dawn.chartdawn.model.Model;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2017/7/1.
 */

public class LineChartActivity extends BaseActivity {

    private LineChart lineChart;
    private ArrayList<Entry> entries;
    private ArrayList<Entry> entries2;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_line_chart;
    }

    @Override
    protected void initData() {
        entries = new ArrayList<>();
        List<Model> models = getAssetData(LineModel.class);
        for(int i = 0; i < models.size(); i ++){
            Model model = models.get(i);
            entries.add(new Entry(i, model.getNum(), getResources().getDrawable(R.mipmap.ic_launcher)));
        }
        entries2 = new ArrayList<>();
        List<Model> models2 = getAssetData(LineModel2.class);
        for(int i = 0; i < models2.size(); i ++){
            Model model = models2.get(i);
            entries2.add(new Entry(i, model.getNum(), null));
        }

    }

    @Override
    protected void initView() {
        lineChart = (LineChart) findViewById(R.id.line_chart);
    }

    @Override
    protected void initListener() {
        setLineChartListener();
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            refreshLineChart(entries, entries2);
        }else{
            LineDataSet dataSet = setLineChartStyle(entries);
            LineDataSet dataSet2 = setLineChartStyle2(entries2);
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(dataSet);
            dataSets.add(dataSet2);
            LineData lineData = new LineData(dataSets);
            lineChart.setData(lineData);
        }

    }
    /**
     * 图表事件设置
     */
    private void setLineChartListener(){
        lineChart.setTouchEnabled(true);//图表是否可以触摸
//        lineChart.setDragEnabled(true);//图表是否可以拖动
        lineChart.setScaleEnabled(true);//图表是否可以放大缩小
        // lineChart.setScaleXEnabled(true);
        // lineChart.setScaleYEnabled(true);
//        lineChart.setPinchZoom(false);
        lineChart.getDescription().setEnabled(false);//右下角的label是否显示
//        lineChart.setDrawGridBackground(false);
        lineChart.animateX(500);
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);//设置下方legend显示样式
        lineChart.getAxisRight().setEnabled(false);//右侧y轴不显示
        XAxis xAxis = lineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);//设置x轴对应的线的样式
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.enableGridDashedLine(10f, 10f, 0f);//设置y轴对应的线的样式
        LineMarkerView lineMarkerView = new LineMarkerView(this, R.layout.custom_marker_view);
        lineMarkerView.setChartView(lineChart);
        lineChart.setMarker(lineMarkerView);//点击时弹窗显示
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
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
     * @param entries
     */
    private LineDataSet setLineChartStyle(ArrayList<Entry> entries){
        //图表样式的设置
        LineDataSet dataSet = new LineDataSet(entries, "DataSet 1");
        dataSet.setDrawIcons(false);//是否采用Entry里面的图片
        dataSet.enableDashedLine(10f, 5f, 0f);//两点之间线的设置，每段线长，间隔长
        dataSet.enableDashedHighlightLine(10f, 5f, 0f);
        dataSet.setColor(Color.BLACK);//线的颜色
        dataSet.setCircleColor(Color.BLACK);//点的颜色
        dataSet.setLineWidth(1f);//线的宽度
        dataSet.setCircleRadius(5f);//点的弧度，半径
        dataSet.setDrawCircleHole(false);//点是否有空
        dataSet.setValueTextSize(15f);//每个点的文字大小
        dataSet.setDrawFilled(true);//每段下方是否填充颜色
        //下面的属性需要设置lineChart的legend
        dataSet.setFormLineWidth(1f);//下方线区分中的线的宽度
        dataSet.setFormSize(20f);//下方线的区分中线的大小
        dataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));//下方线区分中线的样式
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
        dataSet.setFillDrawable(drawable);//设置点下方颜色的填充
        return dataSet;
    }
    /**
     * 图表样式设置
     * @param entries
     */
    private LineDataSet setLineChartStyle2(ArrayList<Entry> entries){
        //图表样式的设置
        LineDataSet dataSet = new LineDataSet(entries, "DataSet 2");
        dataSet.setDrawIcons(false);//是否采用Entry里面的图片
        dataSet.enableDashedLine(10f, 5f, 0f);//两点之间线的设置，每段线长，间隔长
        dataSet.enableDashedHighlightLine(10f, 5f, 0f);
        dataSet.setColor(Color.YELLOW);//线的颜色
        dataSet.setCircleColor(Color.YELLOW);//点的颜色
        dataSet.setLineWidth(1f);//线的宽度
        dataSet.setCircleRadius(5f);//点的弧度，半径
        dataSet.setDrawCircleHole(false);//点是否有空
        dataSet.setValueTextSize(15f);//每个点的文字大小
        dataSet.setDrawFilled(true);//每段下方是否填充颜色
        //下面的属性需要设置lineChart的legend
        dataSet.setFormLineWidth(1f);//下方线区分中的线的宽度
        dataSet.setFormSize(20f);//下方线的区分中线的大小
        dataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));//下方线区分中线的样式
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_yellow);
        dataSet.setFillDrawable(drawable);//设置点下方颜色的填充
        return dataSet;
    }
    /**
     * 线性图表的刷新
     * @param entries
     */
    private void refreshLineChart(List<Entry> entries, List<Entry> entries2){
        LineDataSet dataSet = (LineDataSet)lineChart.getData().getDataSetByIndex(0);
        dataSet.setValues(entries);
        LineDataSet dataSet2 = (LineDataSet)lineChart.getData().getDataSetByIndex(1);
        dataSet2.setValues(entries2);
        lineChart.getData().notifyDataChanged();
        lineChart.notifyDataSetChanged();
    }
}
