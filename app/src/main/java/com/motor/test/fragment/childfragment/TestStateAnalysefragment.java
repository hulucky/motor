package com.motor.test.fragment.childfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greendao.manager.MotorData;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.test.TestActivity;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//测试界面的  运行状态  fragment
public class TestStateAnalysefragment extends Fragment {


    @BindView(R.id.et_detail_ygglsh)
    TextView etDetailYgglsh;
    @BindView(R.id.et_detail_zhglsh)
    TextView etDetailZhglsh;
    @BindView(R.id.et_detail_zhxhgl)
    TextView etDetailZhxhgl;
    @BindView(R.id.et_detail_edzhxl1)
    TextView etDetailEdzhxl1;
    @BindView(R.id.et_detail_edzhxhgl)
    TextView etDetailEdzhxhgl;
    @BindView(R.id.et_detail_fzxs)
    TextView etDetailFzxs;
    @BindView(R.id.et_detail_edzhglsh)
    TextView etDetailEdzhglsh;
    @BindView(R.id.et_detail_edzhxl)
    TextView etDetailEdzhxl;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.et_detail_zhxl)
    TextView etDetailZhxl;
    @BindView(R.id.et_detail_djyxzt)
    TextView etDetailDjyxzt;
    @BindView(R.id.et_detail_dypc)
    TextView etDetailDypc;
    @BindView(R.id.et_detail_dypchl)
    TextView etDetailDypchl;
    @BindView(R.id.et_detail_sxbphd)
    TextView etDetailSxbphd;//三相电压不平衡度
    @BindView(R.id.et_detail_bphdhl)
    TextView etDetailBphdhl;//不平衡度
    @BindView(R.id.et_detail_wgbcrl)
    TextView etDetailWgbcrl;
    @BindView(R.id.et_detail_wgbcdrl)
    TextView etDetailWgbcdrl;
    @BindView(R.id.et_detail_mbglys)
    TextView etDetailMbglys;
    private TestActivity mActivity;
    Unbinder unbinder;
    public int fragflag;
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    DecimalFormat df1 = new DecimalFormat("####0.0");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();
        fragflag = 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_state, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = (TestActivity) getParentFragment().getActivity();


    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity = (TestActivity) getParentFragment().getActivity();
    }

    private TaskEntity getTaskEntity() {
        TaskEntity TaskEntity = mActivity.myApp.getInstance().getTaskEnity();

        return TaskEntity;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void refresh() {

        try {
            MotorData mData = mActivity.mdata.getMotordata();
            //查看数据
            Log.i("aaa", "mData=="+mData.toString());

//            etDetailYgglsh.setText(df2.format(mData.getYgglsh() / 1000));
            etDetailYgglsh.setText(df2.format(mData.getYgglsh()));
//            etDetailZhglsh.setText(df2.format(mData.getZhglsh() / 1000));
            etDetailZhglsh.setText(df2.format(mData.getZhglsh()));
//            etDetailZhxhgl.setText(df2.format(mData.getZhxhgl() / 1000));
            etDetailZhxhgl.setText(df2.format(mData.getZhxhgl()));

            etDetailEdzhxl1.setText(df2.format(mData.getEdzhxl()));
            etDetailEdzhxhgl.setText(df2.format(mData.getEdzhxhgl()));
//            etDetailFzxs.setText(df2.format(mData.getFzxs() / 1000));//负载系数
            etDetailFzxs.setText(df3.format(mData.getFzxs()));//负载系数
            etDetailEdzhglsh.setText(df2.format(mData.getEdzhglsh()));
            etDetailEdzhxl.setText(df2.format(mData.getEdzhxl()));
            etDetailZhxl.setText(df2.format(mData.getZhxl()));
            etDetailDjyxzt.setText(mData.getstrDjyxzt());
//            etDetailDypc.setText(df2.format(mData.getDypc()));//电源电压与额定电压偏差
            etDetailDypc.setText(df2.format(Math.abs(mData.getPjxdy()-mData.getEddy())));//电源电压与额定电压偏差
            // etDetailDypchl.setText(df2.format(mData.getDypchl()));
            etDetailSxbphd.setText(df2.format(mData.getSxbphd()));
            //  etDetailBphdhl.setText(df2.format(mData.getBphdhl()));
            double pjxdy = (mData.getPjdy()) * Math.sqrt(3);

//        etdypc.setText(df2.format(Math.abs(pjxdy - Double.parseDouble(mData.getEddy()))) + "");

            if ((pjxdy / (mData.getEddy())) > 0.9) {
                etDetailDypchl.setText("合理");
            } else {
                etDetailDypchl.setText("不合理");
            }
//        etsxbphd.setText(df2.format((mData.getSxbphd())) + "");
            if ((mData.getSxbphd()) > 1.5) {
                etDetailBphdhl.setText("不合理");
            } else {
                etDetailBphdhl.setText("合理");
            }
            etDetailWgbcrl.setText(df2.format(mData.getWgbcrl()));
            etDetailWgbcdrl.setText(df2.format(mData.getWgbcdrl()));
            etDetailMbglys.setText(df3.format(mData.getMbglys()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void refreshdis() {
        try {
            etDetailYgglsh.setText("--");
            etDetailZhglsh.setText("--");
            etDetailZhxhgl.setText("--");
            etDetailEdzhxl1.setText("--");
            etDetailEdzhxhgl.setText("--");
            etDetailFzxs.setText("--");
            etDetailEdzhglsh.setText("--");
            etDetailEdzhxl.setText("--");
            etDetailZhxl.setText("--");
            etDetailDjyxzt.setText("--");
            etDetailDypc.setText("--");
            etDetailSxbphd.setText("--");
            etDetailDypchl.setText("--");
            etDetailBphdhl.setText("--");
            etDetailWgbcrl.setText("--");
            etDetailWgbcdrl.setText("--");
            etDetailMbglys.setText("--");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
