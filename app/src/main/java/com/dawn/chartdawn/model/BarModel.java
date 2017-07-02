package com.dawn.chartdawn.model;

import java.util.List;

/**
 * Created by 90449 on 2017/7/2.
 */

public class BarModel extends BaseModel {
    private List<Model> data_bar_chart;
    @Override
    public List<Model> getData() {
        return data_bar_chart;
    }
}
