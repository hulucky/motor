package com.motor.test.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.greendao.manager.motorData;
import com.motor.Adapter.TestShowPagerAdapter;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.app.MyApp;
import com.motor.test.TestActivity;
import com.motor.test.fragment.childfragment.TestCurveHamonicfragment;
import com.motor.test.fragment.childfragment.TestCurveWavefragment;
import com.motor.test.fragment.childfragment.TestCurveVectorfragment;
import com.motor.test.fragment.childfragment.TestDatafragment;
import com.motor.test.fragment.childfragment.TestOtherFragment;
import com.motor.test.fragment.childfragment.TestStateAnalysefragment;
import com.motor.view.MyNoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public class TestFragment extends Fragment {

    @BindView(R.id.fr_main)
    MyNoScrollViewPager flmain;
    @BindView(R.id.giv_gif)
    pl.droidsonroids.gif.GifImageView gifImageView;

    @BindView(R.id.cb_test_lock)
    CheckBox ckLock;
    TaskEntity mTask;
    MyApp myApp;
    Unbinder unbinder;
    public static TestDatafragment mdatafragment;
    static TestStateAnalysefragment manalysefragment;
    static TestCurveVectorfragment mcurvevectorfragment;
    public TestCurveWavefragment mcurvewavefragment;
    public TestOtherFragment motherfragment;
    TestCurveHamonicfragment mcurvehamonicfragment;
    List<Fragment> list_fragments;
    TestActivity mActivity;


    static Button btnTestSave;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_fragments = new ArrayList<Fragment>();

        //把两个子fragment实例化然后装到集合里
        mTask = myApp.getTaskEnity();
        mdatafragment = new TestDatafragment();
        list_fragments.add(mdatafragment);//0
        manalysefragment = new TestStateAnalysefragment();
        list_fragments.add(manalysefragment);//1
        mcurvevectorfragment = new TestCurveVectorfragment();
        list_fragments.add(mcurvevectorfragment);//2
        mcurvewavefragment = new TestCurveWavefragment();
        list_fragments.add(mcurvewavefragment);//3
        mcurvehamonicfragment = new TestCurveHamonicfragment();
        list_fragments.add(mcurvehamonicfragment);//4
        motherfragment = new TestOtherFragment();
        list_fragments.add(motherfragment);//5


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);
        myApp = MyApp.getInstance();
        mTask = myApp.getTaskEnity();
        unbinder = ButterKnife.bind(this, view);
        btnTestSave = (Button) view.findViewById(R.id.btn_test_save);
        mActivity = (TestActivity) getActivity();
        flmain.setNoScroll(true);
        if (list_fragments != null) {
            TestShowPagerAdapter msgAdapter = new TestShowPagerAdapter(getChildFragmentManager(), list_fragments);
            flmain.setAdapter(msgAdapter);
            flmain.setCurrentItem(0);
        }
        switch (mTask.getCsff()) {
            case "单瓦法":
                gifImageView.setBackgroundResource(R.drawable.gif_fb);
                break;
            case "双瓦法":
                gifImageView.setBackgroundResource(R.drawable.gif_cy);
                break;
            case "三瓦法":
                gifImageView.setBackgroundResource(R.drawable.gif_jyc);
                break;
        }


        ckLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mActivity.setShuaXin(!isChecked);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void refresh() {
//        mshowfragment.refresh();
        manalysefragment.refresh();
        mdatafragment.refresh();
        motherfragment.refresh();
        motorData mData = mActivity.mdata.getMotordata();
        switch (flmain.getCurrentItem()) {
            case 2:
                mcurvevectorfragment.Draw(mData);
                break;
            case 3:
                mcurvewavefragment.Draw(mData);
                break;
            case 4:
                mcurvehamonicfragment.Draw(mData);
                break;

        }


    }

    @OnClick({R.id.rbn_test_cgq, R.id.rbn_test_dj, R.id.rbn_test_fs, R.id.rbn_test_gk, R.id.rbn_test_ylcj, R.id.btn_test_save, R.id.rbn_test_other})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {

                case R.id.rbn_test_dj://运行判断
                    flmain.setCurrentItem(1, false);

                    break;
                case R.id.rbn_test_cgq://主界面
                    flmain.setCurrentItem(0, false);
                    break;
                case R.id.rbn_test_fs://三相 波形曲线

                    flmain.setCurrentItem(3, false);

                    break;

                case R.id.rbn_test_ylcj://矢量曲线

                    flmain.setCurrentItem(2, false);

                    break;
                case R.id.rbn_test_gk://谐波曲线
                    flmain.setCurrentItem(4, false);
                    break;
                case R.id.btn_test_save:
                    btnTestSave.setEnabled(false);
                    CountDownTimer timer = new CountDownTimer(3000 + 500, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (getActivity() != null && !getActivity().isFinishing()) {
                                btnTestSave.setEnabled(false);
                                btnTestSave.setText(String.valueOf(millisUntilFinished / 1000));
                            }
                        }

                        @Override
                        public void onFinish() {
                            //非空判断
                            if (getActivity() != null && !getActivity().isFinishing()) {
                                btnTestSave.setEnabled(true);
                                btnTestSave.setText("保存");
                            }
                        }
                    }.start();
                    if (mActivity.SaveData()) {
                        Toasty.success(mActivity, "已保存一组数据！", Toast.LENGTH_SHORT, true).show();
                    } else {
                        Toasty.error(mActivity, "数据保存失败！", Toast.LENGTH_SHORT, true).show();
                    }
                    break;
                case R.id.rbn_test_other: {
                    flmain.setCurrentItem(5, false);
                }
                break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }


    public static void setCaijiFalse() {
        btnTestSave.setEnabled(false);
    }

    public static void setCaijiText(String s) {
        btnTestSave.setText(s);
    }

    public static void setCaijiTrue() {
        btnTestSave.setText("保 存");
        btnTestSave.setEnabled(true);
    }

    public void setSensor(int mpower, int msingal) {
        mdatafragment.SetSensor("功率", mpower, msingal, 1);
    }

    public void refreshdisconnect() {
        manalysefragment.refreshdis();
        mdatafragment.refreshdis();
    }
}
