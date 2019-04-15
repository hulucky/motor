package com.motor.test.fragment.DataChildFragment;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.greendao.manager.DataTFJ;
import com.greendao.manager.TaskResEnityDao;
import com.greendao.manager.motorData;
import com.motor.Adapter.TestDataDetailAdapter;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.administrator.DATAbase.greendao.TaskResEnity;
import com.motor.app.MyApp;
import com.motor.test.fragment.DataFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public class DataDetailfragment extends Fragment implements TestDataDetailAdapter.Callback {


    @BindView(R.id.sc_datadetail)
    ScrollView scdetail;
    @BindView(R.id.et_datatest_eddy)
    TextView etDatatestEddy;
    @BindView(R.id.et_datatest_eddl)
    TextView etDatatestEddl;
    @BindView(R.id.et_datatest_edxl)
    TextView etDatatestEdxl;
    @BindView(R.id.et_datatest_edgl)
    TextView etDatatestEdgl;
    @BindView(R.id.et_datatest_kzgl)
    TextView etDatatestKzgl;
    @BindView(R.id.et_datatest_kzdl)
    TextView etDatatestKzdl;
    @BindView(R.id.et_datatest_js)
    TextView etDatatestJs;
    @BindView(R.id.et_datatest_wgjjdl)
    TextView etDatatestWgjjdl;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.et_datatest_pjdy)
    TextView etDatatestPjdy;
    @BindView(R.id.et_datatest_yggl)
    TextView etDatatestYggl;
    @BindView(R.id.et_datatest_pjdl)
    TextView etDatatestPjdl;
    @BindView(R.id.et_datatest_wggl)
    TextView etDatatestWggl;
    @BindView(R.id.et_datatest_glys)
    TextView etDatatestGlys;
    @BindView(R.id.et_datatest_szgl)
    TextView etDatatestSzgl;
    @BindView(R.id.et_datatest_dwpl)
    TextView etDatatestDwpl;
    @BindView(R.id.et_datatest_scgl)
    TextView etDatatestScgl;
    @BindView(R.id.et_datatest_fzxs1)
    TextView etDatatestFzxs1;
    @BindView(R.id.et_datatest_djxl)
    TextView etDatatestDjxl;
    @BindView(R.id.et_datatest_zhxl1)
    TextView etDatatestZhxl1;
    @BindView(R.id.et_datatest_ljdl)
    TextView etDatatestLjdl;
    @BindView(R.id.et_datatest_ygglsh)
    TextView etDatatestYgglsh;
    @BindView(R.id.et_datatest_zhglsh)
    TextView etDatatestZhglsh;
    @BindView(R.id.et_datatest_edzhxhgl)
    TextView etDatatestEdzhxhgl;
    @BindView(R.id.et_datatest_edzhglsh)
    TextView etDatatestEdzhglsh;
    @BindView(R.id.et_datatest_zhxhgl)
    TextView etDatatestZhxhgl;
    @BindView(R.id.et_datatest_fzxs)
    TextView etDatatestFzxs;
    @BindView(R.id.et_datatest_edzhxl1)
    TextView etDatatestEdzhxl1;
    @BindView(R.id.et_datatest_edzhxl)
    TextView etDatatestEdzhxl;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.et_datatest_zhxl)
    TextView etDatatestZhxl;
    @BindView(R.id.et_datatest_djyxzt)
    TextView etDatatestDjyxzt;
    @BindView(R.id.et_datatest_dypc)
    TextView etDatatestDypc;
    @BindView(R.id.et_datatest_dypchl)
    TextView etDatatestDypchl;
    @BindView(R.id.et_datatest_sxbphd)
    TextView etDatatestSxbphd;
    @BindView(R.id.et_datatest_bphdhl)
    TextView etDatatestBphdhl;
    @BindView(R.id.et_datatest_wgbcrl)
    TextView etDatatestWgbcrl;
    @BindView(R.id.et_datatest_wgbcdrl)
    TextView etDatatestWgbcdrl;
    @BindView(R.id.et_datatest_mbglys)
    TextView etDatatestMbglys;
    @BindView(R.id.et_detail_kzwggl)
    TextView etDetailKzwggl;
    @BindView(R.id.et_detail_edfzwggl)
    TextView etDetailEdfzwggl;
    @BindView(R.id.et_detail_ygdn)
    TextView etDetailYgdn;
    @BindView(R.id.et_detail_wgdl)
    TextView etDetailWgdl;
    @BindView(R.id.et_detail_thdUA)
    TextView etDetailThdUA;
    @BindView(R.id.et_detail_kua)
    TextView etDetailKua;
    @BindView(R.id.et_detail_thdUB)
    TextView etDetailThdUB;
    @BindView(R.id.et_detail_kub)
    TextView etDetailKub;
    @BindView(R.id.et_detail_thdUC)
    TextView etDetailThdUC;
    @BindView(R.id.et_detail_kuc)
    TextView etDetailKuc;
    @BindView(R.id.et_detail_thdIA)
    TextView etDetailThdIA;
    @BindView(R.id.et_detail_kia)
    TextView etDetailKia;
    @BindView(R.id.et_detail_thdIB)
    TextView etDetailThdIB;
    @BindView(R.id.et_detail_kib)
    TextView etDetailKib;
    @BindView(R.id.et_detail_thdIC)
    TextView etDetailThdIC;
    @BindView(R.id.et_detail_kic)
    TextView etDetailKic;
    private Activity mActivity;
    Unbinder unbinder;
    MyApp myApp;
    TaskEntity mtask;
    TaskResEnity mres;
    DataTFJ mdata;
    DataFragment mDataFragment;
    DecimalFormat df2 = new DecimalFormat("####0.00");

    DecimalFormat df3 = new DecimalFormat("####0.000");

    DecimalFormat df1 = new DecimalFormat("####0.0");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getParentFragment().getActivity();
        mDataFragment = (DataFragment) getParentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_datadetail, null);
        mActivity = getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        mdata = new DataTFJ();

        try {
            myApp = MyApp.getInstance();
            mtask = myApp.getTaskEnity();
            mres = myApp.getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.TaskId.eq(mtask.getId())).list().get(0);
//            mdata.SetHisTask(mtask);
            mdata.SetResOnly(mres);
            mdata.Refresh();
            refresh(mdata);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @OnClick({R.id.et_datatest_pjdl, R.id.et_datatest_pjdy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_datatest_pjdl:
                try {
                    dialog("平均电流", df2.format(mdata.getMotordata().getPjdl()), "A", 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.et_datatest_pjdy:
                try {
                    dialog("平均电压", df2.format(mdata.getMotordata().getPjxdy()), "V", 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getParentFragment().getActivity();


    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity = getParentFragment().getActivity();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void RefreshByRes(TaskResEnity mres) {
        try {
            myApp = MyApp.getInstance();

            mdata.setMotordata(new motorData());
            mdata.SetResOnly(mres);
            mdata.Refresh();
            refresh(mdata);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 接口方法，响应ListView按钮点击事件
     */
    @Override
    public void click(DataTFJ mmdata) {
        try {
            mmdata.Refresh();
            refresh(mmdata);
            TaskResEnity mmres = mmdata.GetRes();
            myApp.getDaoInstant().getTaskResEnityDao().update(mmres);
            mDataFragment.RefreshList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh(DataTFJ mmdata) {

        motorData mData = mmdata.getMotordata();
        try {
            etDatatestEddy.setText(df2.format(mData.getEddy()));
            etDatatestEddl.setText(df2.format(mData.getEddl()));
            etDatatestEdgl.setText(df2.format(mData.getEdgl()));
            etDatatestEdxl.setText(df2.format(mData.getEdxl()));
            etDatatestKzdl.setText(df2.format(mData.getKzdl()));
            etDatatestKzgl.setText(df2.format(mData.getKzgl()));
            etDatatestJs.setText(df2.format(mData.getJs()));
            etDatatestWgjjdl.setText(df2.format(mData.getWgjjdl()));


            etDatatestPjdy.setText(df2.format(mData.getPjxdy()));

            etDatatestPjdl.setText(df2.format(mData.getPjdl()));
            etDatatestYggl.setText(df2.format(mData.getYggl()));
            etDatatestFzxs1.setText(df3.format(mData.getfzxs()));
            etDatatestWggl.setText(df2.format(mData.getWggl()));
            etDatatestDjxl.setText(df2.format(mData.getXl()));
            etDatatestScgl.setText(df2.format(mData.getScgl()));
            etDatatestZhxl1.setText(df2.format(mData.getZhxl()));
            etDatatestSzgl.setText(df2.format(mData.getSzgl()));
            etDatatestDwpl.setText(df2.format(mData.getDwpl()));
            etDatatestGlys.setText(df3.format(mData.getGlys()));
            etDatatestLjdl.setText(df2.format(mData.getLxdl()));
            etDatatestYgglsh.setText(df2.format(mData.getYgglsh()));
            etDatatestZhglsh.setText(df2.format(mData.getZhglsh()));
            etDatatestZhxhgl.setText(df2.format(mData.getZhxhgl()));
            etDatatestEdzhxl1.setText(df2.format(mData.getEdzhxl()));
            etDatatestEdzhxhgl.setText(df2.format(mData.getEdzhxhgl()));
            etDatatestFzxs.setText(df3.format(mData.getFzxs()));
            etDatatestEdzhglsh.setText(df2.format(mData.getEdzhglsh()));
            etDatatestEdzhxl.setText(df2.format(mData.getEdzhxl()));
            etDatatestZhxl.setText(df2.format(mData.getZhxl()));
            etDatatestDjyxzt.setText(mData.getstrDjyxzt());
            double pjxdy = (mData.getPjdy()) * Math.sqrt(3);
            etDatatestDypc.setText(df2.format(Math.abs(pjxdy - (mData.getEddy()))));
            etDatatestKzgl.setText(df2.format(mData.getKzgl()));
            // etDatatestDypchl.setText(df2.format(mData.getDypchl()));
            etDatatestSxbphd.setText(df2.format(mData.getSxbphd()));
            //  etDatatestBphdhl.setText(df2.format(mData.getBphdhl()));


//        etdypc.setText(df2.format(Math.abs(pjxdy - Double.parseDouble(mData.getEddy()))) + "");

            if ((pjxdy / (mData.getEddy())) > 0.9) {
                etDatatestDypchl.setText("合理");
            } else {
                etDatatestDypchl.setText("不合理");
            }
//        etsxbphd.setText(df2.format((mData.getSxbphd())) + "");
            if ((mData.getSxbphd()) > 1.5) {
                etDatatestBphdhl.setText("不合理");
            } else {
                etDatatestBphdhl.setText("合理");
            }
            etDatatestWgbcrl.setText(df2.format(mData.getWgbcrl()));
            etDatatestWgbcdrl.setText(df2.format(mData.getWgbcdrl()));
            etDatatestMbglys.setText(df2.format(mData.getMbglys()));

            etDetailEdfzwggl.setText(df2.format(mData.getEdfzwggl() / 1000));
            etDetailKzwggl.setText(df2.format(mData.getKzwggl() / 1000));
            etDetailYgdn.setText(df2.format(mData.getYgdn()));
            etDetailWgdl.setText(df2.format(mData.getWgdn()));
            etDetailKia.setText(df2.format(mData.getKIA()));
            etDetailKib.setText(df2.format(mData.getKIB()));
            etDetailKic.setText(df2.format(mData.getKIC()));
            etDetailKua.setText(df2.format(mData.getKUA()));
            etDetailKub.setText(df2.format(mData.getKUB()));
            etDetailKuc.setText(df2.format(mData.getKUC()));
            etDetailThdIA.setText(df2.format(mData.getHCIA()));
            etDetailThdIB.setText(df2.format(mData.getHCIB()));
            etDetailThdIC.setText(df2.format(mData.getHCIC()));
            etDetailThdUB.setText(df2.format(mData.getHCUB()));
            etDetailThdUA.setText(df2.format(mData.getHCUA()));
            etDetailThdUC.setText(df2.format(mData.getHCUC()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param parNameStr
     * @param parStr
     * @param unitStr
     * @param parType
     */
    private void dialog(String parNameStr, String parStr, String unitStr, final int parType) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(mActivity);
        final View dialogView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_one_edittext, null);
        TextView nameTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_name);
        final EditText inputEdittext = (EditText) dialogView.findViewById(R.id.et_dialog_input);
        TextView unitTextView = (TextView) dialogView.findViewById(R.id.tv_dialog_unit);
        nameTextView.setText(parNameStr + ":");
        inputEdittext.setText(parStr);
        unitTextView.setText(unitStr);
        customizeDialog.setTitle("参数修改");
        customizeDialog.setView(dialogView);
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        String inputStr = inputEdittext.getText().toString().trim();
                        boolean notNull = inputStr.length() > 0 ? true : false;
                        float inputData = notNull ? Float.parseFloat(inputStr) : -100f;
                        switch (parType) {
                            case 1://电机1平均电压
                                if (notNull && inputData >= 0 && inputData <= 10000) {
                                    double pre = mdata.getMotordata().getPjxdy();
                                    double now = Float.parseFloat(inputStr);
                                    ReCalMotor(0, (float) (now - pre));
                                } else {
                                    Toasty.error(mActivity, "输入了错误的参数" + "\n" + "0 ～ 10000 V", 5).show();
                                }
                                break;
                            case 2://电机1平均电流
                                if (notNull && inputData >= 0 && inputData <= 6000) {
                                    double pre = mdata.getMotordata().getPjdl();

                                    double now = Float.parseFloat(inputStr);
                                    ReCalMotor((float) (now - pre), 0);
                                } else {
                                    Toasty.error(mActivity, "输入了错误的参数" + "\n" + "0 ～ 1000 A", 5).show();
                                }
                                break;
                        }

                        refreshlist();

                    }
                });
        customizeDialog.show();
    }

    private void refreshlist() {
        TaskResEnity mmres = mdata.GetRes();
        myApp.getDaoInstant().getTaskResEnityDao().update(mmres);
        refresh(mdata);
        mDataFragment.RefreshList();
    }


    private void ReCalMotor(float changei, float changeu) {
        motorData mmotordata = mdata.getMotordata();
        changeu = (float) (changeu / (Math.sqrt(3)));
        float Ua = (float) (mmotordata.getUA() + changeu);
        float Ia = (float) mmotordata.getIA() + changei;
        float Ub = (float) mmotordata.getUB() + changeu;
        float Ib = (float) mmotordata.getIB() + changei;
        float Uc = (float) mmotordata.getUC() + changeu;
        float Ic = (float) mmotordata.getIC() + changei;
        float Cos = (float) mmotordata.getGlys();


        float cs = (float) Math.sqrt(3);
        float u0 = 0f;
        float i0 = 0f;

        if (mmotordata.getMethod() == 2) {
            u0 = (Ua + Ub + Uc) / 3;
            i0 = (Ia + Ib + Ic) / 3;
        } else if (mmotordata.getMethod() == 1) {
            u0 = (Ua + Ub + Uc) / 3;
            i0 = (Ia + Ic) / 2;
        } else if (mmotordata.getMethod() == 0) {
            u0 = Ua;
            i0 = Ia;
        }
        float S = u0 * i0 / 1000 * 3;
        float P = S * Cos;
        float Q = (float) Math.sqrt(Math.pow(S, 2) - Math.pow(P, 2));


        mmotordata.setUAB(cs * Ua);
        mmotordata.setIA(Ia);
        mmotordata.setUCA(cs * Uc);
        mmotordata.setIC(Ic);
        mmotordata.setUBC(cs * Ub);
        mmotordata.setIB(Ib);
        mmotordata.setPjdl((i0));
        mmotordata.setPjdy(cs * u0);
        mmotordata.setGlys(Cos);
        mmotordata.setYggl(P);
        mmotordata.setUA(Ua);
        mmotordata.setUB(Ub);
        mmotordata.setUC(Uc);
        mmotordata.setWggl(Q);
        mmotordata.setSzgl(S);

        mmotordata.Calculate();
        mdata.setMotordata(mmotordata);
        mdata.setUab(cs * Ua);
        mdata.setIa(Ia);
        mdata.setUca(cs * Uc);
        mdata.setIc(Ic);
        mdata.setUbc(cs * Ub);
        mdata.setIb(Ib);
        mdata.setPjI((i0));
        mdata.setPjU(cs * u0);
        mdata.setGlys(Cos);


        mdata.setDjgl(P);


        mdata.setUa(Ua);
        mdata.setUb(Ub);
        mdata.setUc(Uc);


    }
}
