package com.motor.Tools;

import android.content.Context;

import com.motor.app.MyApp;
import com.motor.test.TestActivity;
import com.xzkydz.bean.ComBean;
import com.xzkydz.helper.SerialHelper;

import java.util.LinkedList;
import java.util.Queue;

public class SerialControlMy extends SerialHelper {
    public Queue<ComBean> QueueList = new LinkedList<ComBean>();

    public SerialControlMy(Context context, int mDataType) {
        super(context, mDataType);
    }

    public SerialControlMy(Context context, String sPort, String sBaudRate, int mDataType) {
        super(context, sPort, sBaudRate, mDataType);
    }

    @Override
    protected void onDataReceived(ComBean comBean) {
        // 处理接收到的数据（转换、计算）
        AddQueue(comBean);
    }


    public synchronized void AddQueue(ComBean ComData) {
        QueueList.add(ComData);
    }
}
