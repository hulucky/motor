package com.motor.Tools;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.motor.Listener.ISensorInf;
import com.motor.administrator.DATAbase.greendao.MotorBean;
import com.motor.app.MyApp;
import com.motor.bean.MotorrBean;
import com.motor.test.TestActivity;
import com.xzkydz.bean.ComBean;
import com.xzkydz.helper.SerialHelper;

import java.util.LinkedList;
import java.util.Queue;

import static com.motor.app.MyApp.motorBean;

public class SerialControl extends SerialHelper {
//    public Queue<ComBean> QueueList = new LinkedList<ComBean>();

    public SerialControl(Context context, int mDataType) {
        super(context, mDataType);
    }

    public SerialControl(Context context, String sPort, String sBaudRate, int mDataType) {
        super(context, sPort, sBaudRate, mDataType);
    }

    @Override
    protected void onDataReceived(ComBean comBean) {
        // 处理接收到的数据（转换、计算）
//        AddQueue(ComRecData);
        Log.d("qas", "onDataReceived: " + comBean.recDataType);
        switch (comBean.recDataType) {
            case -128://功率箱
                MyApp.comBeanMotor = comBean;
                MyApp.isConnected = true;
                if (comBean.recData.length == 104) {//电压电流 1.6秒发一次
                    MyApp.comBeanUI = comBean;
                    //解析传感器串口数据包，并更新motorBean的属性
                    if (motorBean == null) {
                        motorBean = new MotorrBean(comBean.recData);
                    } else {
                        motorBean.caculate(comBean.recData);
                    }
                }
                if (comBean.recData.length == 82) {//谐波 时刻在发送
                    MyApp.comBeanHAM = comBean;
                }

                //设置回调接口
                if (receivedSensorData != null && motorBean != null) {
                    receivedSensorData.getData(motorBean);
                }
                break;
        }
    }

    private OnReceivedSensorData receivedSensorData;

    public void setOnReceivedSensorListener(@Nullable OnReceivedSensorData l) {
        this.receivedSensorData = l;
    }

    public interface OnReceivedSensorData {
        void getData(ISensorInf sensorInf);
    }

//    public synchronized void AddQueue(ComBean ComData) {
//        QueueList.add(ComData);
//    }
}
