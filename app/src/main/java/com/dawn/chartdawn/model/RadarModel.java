package com.dawn.chartdawn.model;

import java.util.List;

/**
 * Created by 90449 on 2017/7/2.
 */

public class RadarModel extends BaseModel {
    private List<Model> data_radar_chart;
    @Override
    public List<Model> getData() {
        return data_radar_chart;
    }
}
