# chart 的使用

* [chart引用](#chart引用)
* [LineChart使用](#linechart使用)
    * [LineChart事件的相关设置](#linechart事件的相关设置)
        * [setTouchEnabled](#settouchenabled)
        * [setScaleEnabled](#setscaleenabled)
        * [getDescription](#getdescription)
        * [animateX](#animateX)
        * [getLegend](#getlegend)
        * [getAxisRight](#getaxisright)
        * [getXAxis](#getxaxis)
        * [getAxisLeft](#getaxisleft)
        * [setMarker](#setmarker)
        * [MarkerView](#markerview)
        * [setOnChartValueSelectedListener](#setonchartvalueselectedlistener)
    * [LineChart展示的相关设置](#linechart展示的相关设置)
        * [LineChart控件](#linechart控件)
        * [Entry](#entry)
        * [LineDataSet](#linedataset)
        * [ArrayList<ILineDataSet>](#arraylist<ilinedataset>)
        * [LineData](#linedata)
        * [LineChart的数据添加](#linechart的数据添加)
        * [线性图表的刷新](#线性图表的刷新)
* [BarChart使用](#barchart使用)
    * [BarChart事件的相关设置](#barchart事件的相关设置)
        * [setDrawBarShadow](#setdrawbarshadow)
        * [setDrawValueAboveBar](#setdrawvalueabovebar)
        * [getDescription](#getdescription)
        * [animateX](#animatex)
        * [getXAxis](#getxaxis)
        * [IAxisValueFormatter](#iaxisvalueformatter)
        * [getAxisLeft](#getaxisleft)
        * [getLegend](#getlegend)
        * [setMarker](#setmarker)
        * [MarkerView](#markerview)
        * [setOnChartValueSelectedListener](#setonchartvalueselectedlistener)
    * [BarChart展示的相关设置](#barchart展示的相关设置)
        * [BarChart控件](#BarChart控件)
        * [BarEntry](#barentry)
        * [BarDataSet](#BarDataSet)
        * [List<IBarDataSet>](#list<ibardataset>)
        * [BarData](#bardata)
    


## chart引用

Add the following to your project level build.gradle:
```
allprojects {
	repositories {
		maven { url "https://jitpack.io" }
	}
}
```

Add this to your app build.gradle:
```
dependencies {
	compile 'com.github.PhilJay:MPAndroidChart:v3.0.2'
}
```

## LineChart使用

![line_chart](app/src/main/assets/line_chart.jpg "线性图表截图")

### LineChart事件的相关设置

#### setTouchEnabled
是否可以触摸
```
lineChart.setTouchEnabled(true);//图表是否可以触摸
```

#### setScaleEnabled
是否可以缩放
```
lineChart.setScaleEnabled(true);//图表是否可以放大缩小
```

#### getDescription
下方的lebel是否显示
```
lineChart.getDescription().setEnabled(false);//右下角的label是否显示
```

#### animateX
图表显示的动画
```
lineChart.animateX(500);
```

#### getLegend
下方线legend的样式
```
Legend legend = lineChart.getLegend();
legend.setForm(Legend.LegendForm.LINE);//设置下方legend显示样式
```

#### getAxisRight
右侧y轴数字是否显示
```
lineChart.getAxisRight().setEnabled(false);//右侧y轴不显示
```

#### getXAxis
设置x轴对应的线的样式
```
XAxis xAxis = lineChart.getXAxis();
xAxis.enableGridDashedLine(10f, 10f, 0f);//设置x轴对应的线的样式
```

#### getAxisLeft
设置y轴对应的线的样式
```
YAxis leftAxis = lineChart.getAxisLeft();
leftAxis.enableGridDashedLine(10f, 10f, 0f);//设置y轴对应的线的样式
```

#### setMarker
点击时弹窗显示的标识
```
LineMarkerView lineMarkerView = new LineMarkerView(this, R.layout.custom_marker_view);
lineMarkerView.setChartView(lineChart);
lineChart.setMarker(lineMarkerView);//点击时弹窗显示
```

#### MarkerView
MarkerView对应的LineMarkerView的代码
```
public class LineMarkerView extends MarkerView {
    private TextView tvContent;
    public LineMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
//            tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
            tvContent.setText("" + ce.getHigh());
        } else {
//            tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true));
            tvContent.setText("" + e.getY());
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
```

#### setOnChartValueSelectedListener
图表点的点击事件
```
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
```


### LineChart展示的相关设置

#### LineChart控件
Line Chart的xml中引用
```
<com.github.mikephil.charting.charts.LineChart
    android:id="@+id/line_chart"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

#### Entry
图表的数据（纯数据）
```
new Entry(i, model.getNum(), getResources().getDrawable(R.drawable.star));
```
Entry里面分别对应x轴数据，y轴数据，和点的图片

#### LineDataSet
图表的设置，包括数据，样式等。
```
LineDataSet dataSet = new LineDataSet(entries, "LineChart 1");
```
样式设置，第一个参数是Entry的集合，第二个参数是曲线对应的意思  
线的设置还有很多，其中包括
```
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
```

#### ArrayList<ILineDataSet>
对图表的设置的集合
```
ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
dataSets.add(dataSet);
``` 
所有线的属性的集合，参数是LineDataSet

#### LineData
图表的所有的数据（包括样式）
```
LineData lineData = new LineData(dataSets);
```
控件对应的最直接的数据，包括所有线的设置和所有数据，参数ArrayList<ILineDataSet>

#### LineChart的数据添加
```
lineChart.setData(lineData);
```
线性图表的数据添加，参数是LineData

#### 线性图表的刷新
```
LineDataSet dataSet = (LineDataSet)lineChart.getData().getDataSetByIndex(0);
dataSet.setValues(entries);
lineChart.getData().notifyDataChanged();
lineChart.notifyDataSetChanged();
```



## BarChart使用

![bar_chart](app/src/main/assets/bar_chart.jpg "线性图表截图")

### BarChart事件的相关设置

#### setDrawBarShadow
设置圆柱部分外是否阴影显示
```
barChart.setDrawBarShadow(false);//除了圆柱外其他部分是否填充阴影
```

#### setDrawValueAboveBar
是否将值写到圆柱的上方
```
barChart.setDrawValueAboveBar(true);//是否将值写到圆柱上方
```

#### getDescription
设置右下角的description是否显示
```
barChart.getDescription().setEnabled(false);//右下角的description是否显示
```

#### animateX
动画设置
```
arChart.animateX(1000);//动画效果
```

#### getXAxis
获取x轴的设置
```
XAxis xAxis = barChart.getXAxis();
xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
xAxis.setLabelCount(6);//x坐标label数量最大
xAxis.setDrawGridLines(false);
xAxis.setValueFormatter(new BarAxisValueFormatter().new XAxisValueFormatter());
```

#### IAxisValueFormatter
设置x轴和y轴对应的数据
```
public class BarAxisValueFormatter {
    public class XAxisValueFormatter implements IAxisValueFormatter {
    
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return ((int) value + 1) + "月";
        }
    }
    public class YAxisValueFormatter implements IAxisValueFormatter {
    
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return ((int) value) + "元";
        }
    }
}
```

#### getAxisLeft
获取左侧y轴的设置
```
YAxis leftAxis = barChart.getAxisLeft();
leftAxis.setValueFormatter(new BarAxisValueFormatter().new YAxisValueFormatter());
```

#### getLegend
获取下方legend，并且进行设置
```
Legend legend = barChart.getLegend();
legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);//设置legend在图形下方
legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//设置legend和图形左对齐
legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//设置legend和图形的位置关系
legend.setDrawInside(false);//legend和图形是否显示一起
legend.setForm(Legend.LegendForm.SQUARE);//legend图形样式
legend.setFormSize(9f);//设置legend的方格大小
legend.setTextSize(11f);//设置legend的文字大小
//        legend.setXEntrySpace(4f);
```

#### setMarker
设置点击图表某项时的效果
```
BarMarkerView barMarkerView = new BarMarkerView(this, new BarAxisValueFormatter().new XAxisValueFormatter());
barMarkerView.setChartView(barChart);
barChart.setMarker(barMarkerView);
```

#### MarkerView
点击图表某项时设置的MarkerView
```
public class BarMarkerView extends MarkerView {

    private TextView tvContent;
    private IAxisValueFormatter xAxisValueFormatter;

    private DecimalFormat format;

    public BarMarkerView(Context context, IAxisValueFormatter xAxisValueFormatter) {
        super(context, R.layout.custom_marker_view);

        this.xAxisValueFormatter = xAxisValueFormatter;
        tvContent = (TextView) findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        tvContent.setText("x: " + xAxisValueFormatter.getFormattedValue(e.getX(), null) + ", y: " + format.format(e.getY()));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
```

#### setOnChartValueSelectedListener
圆柱的点击事件
```
barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
    @Override
    public void onValueSelected(Entry entry, Highlight h) {
        toastUI(entry.getY() + "");
    }

    @Override
    public void onNothingSelected() {
        toastUI("nothing");
    }
});
```

### BarChart展示的相关设置

#### BarChart控件
BarChart 在xml中引用
```
<com.github.mikephil.charting.charts.BarChart
    android:id="@+id/bar_chart"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

#### BarEntry
BarChart 对应的图表的数据（只有数据和图片,最小设置数据单位）
```
new BarEntry(i, model.getNum(), getResources().getDrawable(R.mipmap.ic_launcher))
```

#### BarDataSet
圆柱图表的单个圆柱设置
```
BarDataSet dataSet = new BarDataSet(barEntries, "bar chart");
dataSet.setDrawIcons(false);//是否采用Entry里面的图片
dataSet.setColors(ColorTemplate.MATERIAL_COLORS);//设置圆柱的颜色
```

#### List<IBarDataSet>
每个圆柱设置的集合
```
List<IBarDataSet> dataSets = new ArrayList<>();
dataSets.add(dataSet);
```

#### BarData
BarChart对应的数据，包含所有的相关设置信息
```
BarData barData = new BarData(dataSets);
barData.setValueTextSize(10f);//设置文字大小
barData.setBarWidth(0.9f);//设置圆柱宽度
barChart.setData(barData);
```

