package com.motor.test.fragment.DataChildFragment;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.greendao.manager.DaoMaster;
import com.greendao.manager.DaoSession;
import com.greendao.manager.DataTFJ;
import com.greendao.manager.TaskEntityDao;
import com.greendao.manager.TaskResEnityDao;
import com.motor.Tools.DrawView;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.administrator.DATAbase.greendao.TaskResEnity;
import com.motor.app.MyApp;
import com.motor.service.FileService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DataCurvefragment extends Fragment {


    @BindView(R.id.root)
    LinearLayout root;
    private Activity mActivity;
    Unbinder unbinder;
    String TaskName;
    String bianHao;
    Boolean isFlowTask;

    private FileService fileService;
    long SystemTime = 0;
    private List<DataTFJ> mData;
    private String resultString;
    private List<TaskResEnity> mResList;
    private List<TaskEntity> mHisTasksList;
    String ChartUrl = "javascript:refreshView(";
    DecimalFormat df4 = new DecimalFormat("####00.00");
    DecimalFormat df2 = new DecimalFormat("####00");
    DecimalFormat df3 = new DecimalFormat("####00.0");
    Boolean IsRight = true;
    List<Long> ResIdLists;
    DrawView view;
    Bitmap bmp;
    private float[] SourceData1;
    private float[] SourceData2;
    private float[] SourceData3;
    MyApp myApp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getParentFragment().getActivity();
        fileService = new FileService(mActivity);
        myApp = MyApp.getInstance();
        ResIdLists = new ArrayList<Long>();
        view = new DrawView(mActivity);

        view.setMinimumHeight(500);
        view.setMinimumWidth(300);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdatacurve, null);
        mActivity = getParentFragment().getActivity();
        unbinder = ButterKnife.bind(this, view);
        database();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getParentFragment().getActivity();


    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setResIds(List<Long> mresid) {
        ResIdLists.clear();
        for (Long mid : mresid
                ) {
            ResIdLists.add(mid);
        }
        database();
        formatData();

        Draw();
    }

    private void database() {
        try {
            // TODO Auto-generated method stub

            mData = new ArrayList<DataTFJ>();
            TaskEntity mHisTask = null;
            for (int i = 0; i < ResIdLists.size(); i++) {
                long resId = ResIdLists.get(i);
                TaskResEnity mRes = null;
                mRes = MyApp.getDaoInstant().getTaskResEnityDao().queryBuilder().where(TaskResEnityDao.Properties.Id.eq(resId)).list().get(0);
                DataTFJ md = new DataTFJ();
                md.SetResOnly(mRes);
//                md.Refresh();
                mData.add(md);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }


    private void formatData() {
        IsRight = true;
        try {
            // if (mData.size() == 0) {
            // database();
            // }
            List<DataTFJ> mData1 = new ArrayList<DataTFJ>();
            List<Double> mAllData = new ArrayList<Double>();
            for (int i = 0; i < mData.size(); i++) {
                if (!mAllData.contains(mData.get(i).getMgFl())) {
                    mAllData.add(mData.get(i).getMgFl());
                }
            }
            Collections.sort(mAllData); // ����

            for (int i = 0; i < mAllData.size(); i++) {
                for (int j = 0; j < mData.size(); j++) {
                    if (mData.get(j).getMgFl() == (mAllData.get(i))) {
                        mData1.add(mData.get(j));
                    }
                }
            }
            SourceData1 = new float[mData1.size() * 2];
            SourceData2 = new float[mData1.size() * 2];
            SourceData3 = new float[mData1.size() * 2];
            for (int i = 0; i < mData1.size(); i++) {
                DataTFJ mDataT = mData1.get(i);
                if (IsRight && mDataT.getMgFl() > 0 && mDataT.getmJy() > 0
                        && mDataT.getMgJyxl() > 0
                        && (mDataT.getMgZgl1() + mDataT.getMgZgl2()) > 0) {

                    SourceData1[i * 2] = Float.parseFloat(df3.format(mDataT
                            .getMgFl()));
                    SourceData2[i * 2] = Float.parseFloat(df3.format(mDataT
                            .getMgFl()));
                    SourceData3[i * 2] = Float.parseFloat(df3.format(mDataT
                            .getMgFl()));
                    SourceData1[i * 2 + 1] = Float.parseFloat(df2.format(mDataT
                            .getMgFjjy()));
                    SourceData2[i * 2 + 1] = Float.parseFloat(df3.format(mDataT
                            .getMgZgl1() + mDataT.getMgZgl2()));
                    SourceData3[i * 2 + 1] = Float.parseFloat(df4.format(mDataT
                            .getMgJyxl()));

                } else {
                    IsRight = false;
                }
            }
        } catch (Exception e) {
        }
    }

    private void Draw() {
        view.SetResourceDataY(SourceData1);
        view.SetResourceDataU(SourceData2);
        view.SetResourceDataD(SourceData3);
        view.SetPoint(50, 50);
        view.setYUColor( Resources.getSystem().getColor(android.R.color.holo_green_dark));
        view.invalidate();
        root.removeAllViews();
        root.addView(view);

        if (root.willNotCacheDrawing()) {
            root.setWillNotCacheDrawing(false);
        }
        root.setDrawingCacheEnabled(true);
        root.buildDrawingCache(true);
        Bitmap a = convertViewToBitmap(view, 500, 500);
        Bitmap b = convertViewToBitmap(root, 500, 500);

//			bmp = Bitmap.createBitmap(root.getDrawingCache(), 0, 0, 500, 500);
        myApp.setSnapName();

        String fileName = fileService.saveBitmapToSDCard("" + myApp.getSnapName()
                + ".png", a);
    }

    public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));

        return bitmap;
    }

}
