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


## LineChart事件的相关设置

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

#### MarkerView
点击时弹窗显示的标识
```
LineMarkerView lineMarkerView = new LineMarkerView(this, R.layout.custom_marker_view);
lineMarkerView.setChartView(lineChart);
lineChart.setMarker(lineMarkerView);//点击时弹窗显示
```
LineMarkerView的代码
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


## LineChart展示的相关设置

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

