package com.dawn.chartdawn.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by 90449 on 2017/7/2.
 */

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
