package com.motor.test.fragment.childfragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.greendao.manager.motorData;
import com.motor.Tools.DrawViewThi;
import com.motor.administrator.DATAbase.R;
import com.motor.test.TestActivity;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestCurveHamonicfragment extends Fragment {

    @BindView(R.id.rg_ham)
    RadioGroup rgham;

    @BindView(R.id.hamonic)
    LinearLayout hamonic;
    @BindViews({R.id.et_hamonic_0, R.id.et_hamonic_1, R.id.et_hamonic_2, R.id.et_hamonic_3, R.id.et_hamonic_4, R.id.et_hamonic_5, R.id.et_hamonic_6, R.id.et_hamonic_7, R.id.et_hamonic_8, R.id.et_hamonic_9, R.id.et_hamonic_10, R.id.et_hamonic_11, R.id.et_hamonic_12, R.id.et_hamonic_13, R.id.et_hamonic_14, R.id.et_hamonic_15, R.id.et_hamonic_16, R.id.et_hamonic_17, R.id.et_hamonic_18, R.id.et_hamonic_19, R.id.et_hamonic_20, R.id.et_hamonic_21, R.id.et_hamonic_22, R.id.et_hamonic_23, R.id.et_hamonic_24, R.id.et_hamonic_25, R.id.et_hamonic_26, R.id.et_hamonic_27, R.id.et_hamonic_28, R.id.et_hamonic_29, R.id.et_hamonic_30, R.id.et_hamonic_31})
    List<TextView> tvHamnic;
    private Integer Harno;
    TestActivity mActivity;
    Unbinder unbinder;

    DecimalFormat df1 = new DecimalFormat("####0.0");
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();
        Harno = 0;

    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_curve_homonic, null);
        unbinder = ButterKnife.bind(this, view);
        mActivity = (TestActivity) getParentFragment().getActivity();
        rgham.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) view.findViewById(checkedId);
                if (rb != null && !rb.getText().equals("忽略")) {
                    switch (rb.getText().toString()) {
                        case "UA":
                            Harno = 0;
                            break;
                        case "UB":
                            Harno = 1;
                            break;
                        case "UC":
                            Harno = 2;
                            break;
                        case "IA":
                            Harno = 3;
                            break;
                        case "IB":
                            Harno = 4;
                            break;
                        case "IC":
                            Harno = 5;
                            break;
                    }

                } else {
                    Harno = 0;
                }

            }
        });
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
            LinearLayout layout3 = (LinearLayout) view.findViewById(R.id.hamonic);
            layout3.removeAllViews();
            final DrawViewThi view3 = new DrawViewThi(this.getActivity());
            view3.setMinimumHeight(400);
            view3.setMinimumWidth(300);
            view3.setCurveType(3);
            view3.setData(mdata);
            view3.setHamNo(Harno);
            //通知view组件重绘

            view3.invalidate();
            layout3.addView(view3);

            double[] tmphamnic = new double[32];
            switch (Harno) {
                case 0:
                    tmphamnic = mdata.getHarmUA();
                    break;
                case 1:
                    tmphamnic = mdata.getHarmUB();
                    break;
                case 2:
                    tmphamnic = mdata.getHarmUC();
                    break;
                case 3:
                    tmphamnic = mdata.getHarmIA();
                    break;
                case 4:
                    tmphamnic = mdata.getHarmIB();
                    break;
                case 5:
                    tmphamnic = mdata.getHarmIC();
                    break;
            }
            for (int i = 0; i < 32; i++) {
                tvHamnic.get(i).setText(df2.format(tmphamnic[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
