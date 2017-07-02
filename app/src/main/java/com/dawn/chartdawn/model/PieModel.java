package com.dawn.chartdawn.model;

import java.util.List;

/**
 * Created by 90449 on 2017/7/2.
 */

public class PieModel extends BaseModel {
    private List<Model> data_pie_chart;
    @Override
    public List<Model> getData() {
        return data_pie_chart;
    }
}
