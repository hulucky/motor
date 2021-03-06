package com.motor.test.fragment.childfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greendao.manager.motorData;
import com.motor.Adapter.TestShowAdapter;
import com.motor.administrator.DATAbase.R;
import com.motor.test.TestActivity;
import com.sensor.SensorData;
import com.sensor.view.SensorView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestDatafragment extends Fragment {


    @BindView(R.id.gl1_test)
    SensorView sgl1;

    TestShowAdapter adp1;
    TestActivity mActivity;
    Unbinder unbinder;
    @BindView(R.id.et_show_abdy)
    TextView etShowAbdy;
    @BindView(R.id.et_show_adl)
    TextView etShowAdl;
    @BindView(R.id.et_show_bcdy)
    TextView etShowBcdy;
    @BindView(R.id.et_show_bdl)
    TextView etShowBdl;
    @BindView(R.id.et_show_cady)
    TextView etShowCady;
    @BindView(R.id.et_show_cdl)
    TextView etShowCdl;
    @BindView(R.id.tx_xdy)
    TextView txXdy;
    @BindView(R.id.et_show_pjdy)
    TextView etShowPjdy;
    @BindView(R.id.tx_xdl)
    TextView txXdl;
    @BindView(R.id.et_show_pjdl)
    TextView etShowPjdl;
    @BindView(R.id.et_show_yggl)
    TextView etShowYggl;
    @BindView(R.id.et_show_fzxs)
    TextView etShowFzxs;
    @BindView(R.id.et_show_wggl)
    TextView etShowWggl;
    @BindView(R.id.et_show_djxl)
    TextView etShowDjxl;
    @BindView(R.id.et_show_scgl)
    TextView etShowScgl;
    @BindView(R.id.et_show_zhxl1)
    TextView etShowZhxl1;
    @BindView(R.id.et_show_szgl)
    TextView etShowSzgl;
    @BindView(R.id.et_show_dwpl)
    TextView etShowDwpl;
    @BindView(R.id.et_show_glys)
    TextView etShowGlys;
    @BindView(R.id.et_show_yxzt)
    TextView etShowYxzt;
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    DecimalFormat df1 = new DecimalFormat("####0.0");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_data, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        adp1 = new TestShowAdapter(mActivity, mActivity.mdata, 0);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void SetSensorState(SensorView msv, float mpower, float msignal, int minf) {
        SensorData svData = new SensorData();
        // 第二步：设置 SensorData 属性
        svData.setStatus(minf)
                .setPower(mpower)
                .setSignal(msignal);

        // 第三步：给SensorView 赋值
        msv.setData(svData);
    }

    public void SetSensor(String str, float mpower, float msignal, int minf) {
        try {

            SetSensorState(sgl1, mpower, msignal, minf);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void refresh() {
        try {
            motorData mData = mActivity.mdata.getMotordata();
            etShowAbdy.setText(df2.format(mData.getUAB()));
            etShowAdl.setText(df2.format(mData.getIA()));
//        dj2List.add(getTSGridItem("BC线电压",  mdata.getDj2csff().equals("单瓦法")?"-- V":df1.format(mdata.getUbc2())+ " V", "B相电流", !mdata.getDj2csff().equals("三瓦法")?"-- A":df1.format(mdata.getIb2())+ " A"));
//        dj2List.add(getTSGridItem("CA线电压", mdata.getDj2csff().equals("单瓦法")?"-- V":df1.format(mdata.getUca2())+ " V", "C相电流", mdata.getDj2csff().equals("单瓦法")?"-- A":df1.format(mdata.getIc2())+ " A"));
            etShowBcdy.setText(mActivity.mdata.getMff().equals("单瓦法") ? "--" : df2.format(mData.getUBC()));
            etShowBdl.setText(!mActivity.mdata.getMff().equals("三瓦法") ? "--" : df2.format(mData.getIB()));
            etShowCady.setText(mActivity.mdata.getMff().equals("单瓦法") ? "--" : df2.format(mData.getUCA()));
            etShowCdl.setText(mActivity.mdata.getMff().equals("单瓦法") ? "--" : df2.format(mData.getIC()));

            etShowPjdy.setText(df2.format(mData.getPjxdy()));

            etShowPjdl.setText(df2.format(mData.getPjdl()));
            etShowYggl.setText(df2.format(mData.getYggl()));
            etShowFzxs.setText(df3.format(mData.getfzxs()));
            etShowWggl.setText(df2.format(mData.getWggl()));
            etShowDjxl.setText(df2.format(mData.getXl()));
            etShowScgl.setText(df2.format(mData.getScgl()));
            etShowZhxl1.setText(df2.format(mData.getZhxl()));
            etShowSzgl.setText(df2.format(mData.getSzgl()));
            etShowDwpl.setText(df2.format(mData.getDwpl()));
            etShowGlys.setText(df3.format(mData.getGlys()));
            etShowYxzt.setText((mData.getstrDjyxzt()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void refreshdis() {
        try {
            etShowAbdy.setText("--");
            etShowAdl.setText("--");
            etShowBcdy.setText("--");
            etShowBdl.setText("--");
            etShowCady.setText("--");
            etShowCdl.setText("--");
            etShowPjdy.setText("--");
            etShowPjdl.setText("--");
            etShowYggl.setText("--");
            etShowFzxs.setText("--");
            etShowWggl.setText("--");
            etShowDjxl.setText("--");
            etShowScgl.setText("--");
            etShowZhxl1.setText("--");
            etShowSzgl.setText("--");
            etShowDwpl.setText("--");
            etShowGlys.setText("--");
            etShowYxzt.setText("断开");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
