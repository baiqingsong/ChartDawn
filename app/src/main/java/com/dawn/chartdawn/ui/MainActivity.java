package com.dawn.chartdawn.ui;

import android.view.View;

import com.dawn.chartdawn.R;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    public void jumpToLineChart(View view){
        jumpTo(LineChartActivity.class);
    }
}
