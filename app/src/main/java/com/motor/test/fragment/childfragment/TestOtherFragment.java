package com.motor.test.fragment.childfragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greendao.manager.MotorData;
import com.motor.administrator.DATAbase.R;
import com.motor.test.TestActivity;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//更多参数
public class TestOtherFragment extends Fragment {
    @BindView(R.id.et_other_kzwggl)
    TextView etOtherKzwggl;
    @BindView(R.id.et_other_edfzwggl)
    TextView etOtherEdfzwggl;
    @BindView(R.id.et_other_ygdn)
    TextView etOtherYgdn;
    @BindView(R.id.et_other_wgdl)
    TextView etOtherWgdl;
    @BindView(R.id.et_other_thdUA)
    TextView etOtherThdUA;
    @BindView(R.id.et_other_kua)
    TextView etOtherKua;
    @BindView(R.id.et_other_thdUB)
    TextView etOtherThdUB;
    @BindView(R.id.et_other_kub)
    TextView etOtherKub;
    @BindView(R.id.et_other_thdUC)
    TextView etOtherThdUC;
    @BindView(R.id.et_other_kuc)
    TextView etOtherKuc;
    @BindView(R.id.et_other_thdIA)
    TextView etOtherThdIA;
    @BindView(R.id.et_other_kia)
    TextView etOtherKia;
    @BindView(R.id.et_other_thdIB)
    TextView etOtherThdIB;
    @BindView(R.id.et_other_kib)
    TextView etOtherKib;
    @BindView(R.id.et_other_thdIC)
    TextView etOtherThdIC;
    @BindView(R.id.et_other_kic)
    TextView etOtherKic;
    private TestActivity mActivity;
    Unbinder unbinder;
    public int fragflag;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_other, null);
        mActivity = (TestActivity) getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_other_clear)
    public void onViewClicked() {
        mActivity.clearelec();
    }

    public void refresh() {
        try {
            MotorData mData = mActivity.mdata.getMotordata();

//            etOtherEdfzwggl.setText(df2.format(mData.getEdfzwggl() / 1000));
            etOtherEdfzwggl.setText(df2.format(mData.getEdfzwggl()));
//            etOtherKzwggl.setText(df2.format(mData.getKzwggl() / 1000));
            etOtherKzwggl.setText(df2.format(mData.getKzwggl()));

            etOtherYgdn.setText(df2.format(mData.getYgdn()));
            etOtherWgdl.setText(df2.format(mData.getWgdn()));
            etOtherKia.setText(df2.format(mData.getKIA()));
            etOtherKib.setText(df2.format(mData.getKIB()));
            etOtherKic.setText(df2.format(mData.getKIC()));
            etOtherKua.setText(df2.format(mData.getKUA()));
            etOtherKub.setText(df2.format(mData.getKUB()));
            etOtherKuc.setText(df2.format(mData.getKUC()));
            etOtherThdIA.setText(df2.format(mData.getHCIA()));
            etOtherThdIB.setText(df2.format(mData.getHCIB()));
            etOtherThdIC.setText(df2.format(mData.getHCIC()));
            etOtherThdUB.setText(df2.format(mData.getHCUB()));
            etOtherThdUA.setText(df2.format(mData.getHCUA()));
            etOtherThdUC.setText(df2.format(mData.getHCUC()));

        } catch (Exception e) {
        }
    }
}
