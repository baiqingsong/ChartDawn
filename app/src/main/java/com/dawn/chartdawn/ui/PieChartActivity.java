package com.dawn.chartdawn.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.dawn.chartdawn.R;
import com.dawn.chartdawn.model.Model;
import com.dawn.chartdawn.model.PieModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2017/7/2.
 */

public class PieChartActivity extends BaseActivity {
    private PieChart pieChart;
    private List<PieEntry> pieEntries;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pie_chart;
    }

    @Override
    protected void initData() {
        pieEntries = new ArrayList<>();
        List<Model> models = getAssetData(PieModel.class);
        for(int i = 0; i < models.size(); i ++){
            Model model = models.get(i);
            pieEntries.add(new PieEntry(model.getNum(), model.getIntro(), getResources().getDrawable(R.mipmap.ic_launcher)));
        }
    }

    @Override
    protected void initView() {
        pieChart = (PieChart) findViewById(R.id.pie_chart);
    }

    @Override
    protected void initListener() {
        setPieChartListener();
        PieDataSet dataSet = setPieChartStyle(pieEntries);
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(11f);//值的字体大小
        pieData.setValueTextColor(Color.WHITE);//值的字体颜色
        pieData.setValueFormatter(new PercentFormatter());//值添加百分比
        pieChart.setData(pieData);
    }
    /**
     * 图表事件设置
     */
    private void setPieChartListener(){
        pieChart.setUsePercentValues(true);//当前值显示成百分比，如果不设置这个属性pieData.setValueFormatter设置显示的是错误的
        pieChart.getDescription().setEnabled(false);//右下角description不显示
//        pieChart.setExtraOffsets(5, 10, 5, 5);
//        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterText(generateCenterSpannableText());//设置中间文字
        pieChart.setDrawHoleEnabled(true);//是否显示成同心圆的形式
        pieChart.setHoleColor(Color.WHITE);//同心圆的圆心颜色
        pieChart.setTransparentCircleColor(Color.WHITE);//设置同心圆的中心圆和外圈之间的颜色
        pieChart.setTransparentCircleAlpha(110);//设置同心圆的中心圆和外圈之间的颜色透明度
//        pieChart.setHoleRadius(58f);
//        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawCenterText(true);//是否显示中间的文字
        pieChart.setRotationAngle(0);//旋转的角度
//        pieChart.setRotationEnabled(true);
//        pieChart.setHighlightPerTapEnabled(true);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);//动画

        Legend legend = pieChart.getLegend();//legend的设置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
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
     * @param pieEntries
     */
    private PieDataSet setPieChartStyle(List<PieEntry> pieEntries){
        //图表样式的设置
        PieDataSet dataSet = new PieDataSet(pieEntries, "pie chart");
        dataSet.setDrawIcons(false);//不显示实体类中的图片

        dataSet.setSliceSpace(3f);//每一个部分分割空白宽度
//        dataSet.setIconsOffset(new MPPointF(0, 40));
//        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        dataSet.setColors(colors);//设置颜色
        return dataSet;
    }
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("ChartDawn\npie chart");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 9, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 9, s.length() - 10, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 9, s.length() - 10, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 9, s.length() - 10, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 9, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 9, s.length(), 0);
        return s;
    }

}
