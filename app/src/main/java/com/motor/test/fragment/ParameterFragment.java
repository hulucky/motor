package com.motor.test.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motor.Adapter.GridAdapter;
import com.motor.administrator.DATAbase.R;
import com.motor.administrator.DATAbase.greendao.TaskEntity;
import com.motor.app.MyApp;
import com.motor.test.TestActivity;
import com.motor.view.MyListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ParameterFragment extends Fragment implements GridAdapter.Callback{

    @BindView(R.id.tv_tittle_dj)
    TextView tvTittleDj;
    @BindView(R.id.lv_task_inf)
    MyListView lvTaskInf;
    @BindView(R.id.lv_dj_inf)
    MyListView lvDjInf;

    @BindView(R.id.lv_input_inf)
    MyListView lvInputInf;
    Unbinder unbinder;
    private TestActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_parameter, null);
        unbinder = ButterKnife.bind(this, view);
        mActivity = (TestActivity) getActivity();
        initListView();
        return view;
    }
    private TaskEntity getTaskEntity(){
        TaskEntity TaskEntity = mActivity.myApp.getInstance().getTaskEnity();

        return TaskEntity;
    }

    /*
     * description:初始化界面
     */
    private void initListView(){
        GridAdapter taskInfAdapter = new GridAdapter(mActivity ,getTaskEntity(),0,this);
        GridAdapter djInfAdapter = new GridAdapter(mActivity,getTaskEntity(),1,this);

        GridAdapter inputInfAdapter = new GridAdapter(mActivity,getTaskEntity(),3,this);

        lvTaskInf.setAdapter(taskInfAdapter);

        lvDjInf.setAdapter(djInfAdapter);

        lvInputInf.setAdapter(inputInfAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void click() {
        TaskEntity mtask= MyApp.getTaskEnity();
        mActivity.mdata.SetHisTask(mtask);
        mActivity.refresh();
    }
}
