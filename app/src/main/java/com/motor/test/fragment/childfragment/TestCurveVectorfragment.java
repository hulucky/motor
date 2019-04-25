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
import com.motor.administrator.DATAbase.R;
import com.motor.test.TestActivity;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.Unbinder;
//矢量曲线
public class TestCurveVectorfragment extends Fragment {



    TestActivity mActivity;
    Unbinder unbinder;
    View view;

    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_test_curve_vertic, null);
        unbinder = ButterKnife.bind(this, view);
        mActivity = (TestActivity) getParentFragment().getActivity();
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void Draw(motorData mdata) {

        try {



            LinearLayout layout2 = (LinearLayout) view.findViewById(R.id.vector);
            layout2.removeAllViews();
            final DrawViewThi view2 = new DrawViewThi(this.getActivity());
            view2.setMinimumHeight(400);
            view2.setMinimumWidth(300);
            view2.setCurveType(2);
            view2.setData(mdata);
            //通知view组件重绘

            view2.invalidate();
            layout2.addView(view2);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
