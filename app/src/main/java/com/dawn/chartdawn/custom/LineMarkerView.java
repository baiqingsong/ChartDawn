package com.dawn.chartdawn.custom;

import android.content.Context;
import android.widget.TextView;

import com.dawn.chartdawn.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

/**
 * Created by 90449 on 2017/7/2.
 */

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
