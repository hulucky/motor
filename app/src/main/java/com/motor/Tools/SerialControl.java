package com.motor.Tools;

import android.content.Context;

import com.xzkydz.bean.ComBean;
import com.xzkydz.helper.SerialHelper;

import java.util.LinkedList;
import java.util.Queue;

public class SerialControl extends SerialHelper {
    public Queue<ComBean> QueueList = new LinkedList<ComBean>();

    public SerialControl(Context context, int mDataType) {
        super(context, mDataType);
    }

    public SerialControl(Context context, String sPort, String sBaudRate, int mDataType) {
        super(context, sPort, sBaudRate, mDataType);
    }

    @Override
    protected void onDataReceived(ComBean ComRecData) {
        // 处理接收到的数据（转换、计算）
        AddQueue(ComRecData);
    }

    public synchronized void AddQueue(ComBean ComData) {
        QueueList.add(ComData);
    }
}
