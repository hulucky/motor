package com.motor.test.fragment.childfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.greendao.manager.motorData;
import com.motor.Tools.DrawViewThi;
import com.sensor.SensorData;
import com.sensor.view.SensorView;
import com.motor.administrator.DATAbase.R;
import com.motor.test.TestActivity;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestCurveWavefragment extends Fragment {




    DecimalFormat df4 = new DecimalFormat("####0.0");
    TestActivity mActivity;
    Unbinder unbinder;

    View mview;
    boolean showfb=true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_test_curve_wave, null);
        mview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, mview);
        return mview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void SetWindCupState(SensorView msv, float mpower, float msignal, int minf) {
        SensorData svData = new SensorData();
        // 第二步：设置 SensorData 属性
        svData.setStatus(minf)
                .setPower(mpower)
                .setSignal(msignal);
        // 第三步：给SensorView 赋值
        msv.setData(svData);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void Draw(motorData mdata) {

        try {

            LinearLayout layout = (LinearLayout) mview.findViewById(R.id.three);
            layout.removeAllViews();
            final DrawViewThi view = new DrawViewThi(this.getActivity());
            view.setMinimumHeight(400);
            view.setMinimumWidth(300);
            view.setCurveType(0);
            view.setData(mdata);

            //通知view组件重绘
            view.invalidate();

            layout.addView(view);
            LinearLayout layout1 = (LinearLayout) mview.findViewById(R.id.single);
            final DrawViewThi view1 = new DrawViewThi(this.getActivity());
            view1.setMinimumHeight(400);
            view1.setMinimumWidth(300);
            view1.setCurveType(1);
            view1.setData(mdata);
            //通知view组件重绘
            view1.invalidate();

            layout1.addView(view1);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
