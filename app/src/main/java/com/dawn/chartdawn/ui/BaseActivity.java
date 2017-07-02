package com.dawn.chartdawn.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dawn.chartdawn.model.BaseModel;
import com.dawn.chartdawn.model.Model;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2017/7/1.
 */

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(getLayoutRes());
        initView();
        initListener();
    }

    protected abstract @LayoutRes int getLayoutRes();
    protected abstract void initData();
    protected abstract void initView();
    protected abstract void initListener();
    public void jumpTo(Class<?> mClass){
        Intent intent = new Intent(this, mClass);
        startActivity(intent);
    }
    public List<Model> getAssetData(Class<? extends BaseModel> mClass){
        List<Model> models = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("data.json");
            String str = inputStreamTOString(inputStream, "utf8");
            BaseModel baseModel = new GsonBuilder().create().fromJson(str, mClass);
            models = baseModel.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }
    /**
     * 将InputStream转换成某种字符编码的String
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String inputStreamTOString(InputStream in, String encoding) throws Exception{

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024 * 4];
        int count = -1;
        while((count = in.read(data,0,1024 * 4)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(),encoding);
    }
    protected void toastUI(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, (msg == null || msg.trim().length() == 0) ? "" : msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
